package com.yuhao.algorithm;

import com.yuhao.algorithm.crossover.*;
import com.yuhao.algorithm.localsearch.*;
import com.yuhao.algorithm.mutation_or_ruinrecreate.*;
import com.yuhao.data.Memeplex;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;
import com.yuhao.utils.MyFileReader;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import static com.yuhao.config.Constant.*;

public class MultimemeComponent {
    private Random m_rnd;
    private Problem m_problem;
    private Population m_populationParent;
    private Population m_populationChildren;

    private int m_loopCount = 0;

    /**
     * NOTE: for test and printout use only
     */
    public Problem getProblemForTestUse() {
        return m_problem;
    }

    /**
     * NOTE: for test and printout use only
     */
    public Population getPopulationParentForTestUse() {
        return m_populationParent;
    }

    /**
     * NOTE: for test and printout use only
     */
    public Population getPopulationChildrenForTestUse() {
        return m_populationChildren;
    }

    public MultimemeComponent() {
        // set the random number
        if (IS_USE_SEED) {
            m_rnd = new Random(SEED);
        } else {
            m_rnd = new Random();
        }

        // read from file
        LinkedList<LinkedList<Double>> infoFromFile = new LinkedList<>();
        MyFileReader fileReader = new MyFileReader();
        try {
            infoFromFile = fileReader.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // initialise the problem by data from the test instance file
        m_problem = new Problem(infoFromFile.get(0).get(0).intValue(), infoFromFile.get(1).get(0), infoFromFile.get(2),
                infoFromFile.get(3));

        // parents are initialised with random chromosome and memeplex
        m_populationParent = new Population(m_rnd, infoFromFile.get(0).get(0).intValue(), true);

        // children are initialised with empty chromosome and memeplex
        m_populationChildren = new Population(m_rnd, infoFromFile.get(0).get(0).intValue(), false);

        m_loopCount = 0;
    }

    // TODO: comments for methods (注意细节：比如crossover用的是哪一个parent的option等，可以参考CourseworkRunner的伪代码)

    private void assignParentChromosomeToChild(int idParent, int idChild) {
        LinkedList<Integer> chromosomeParent = m_populationParent.getIndividual(idParent);
        LinkedList<Integer> chromosomeChild = m_populationChildren.getIndividual(idChild);
        for (int i = 0; i < m_problem.getNumOfItems(); i++) {
            chromosomeChild.set(i, chromosomeParent.get(i));
        }
    }

    private void assignChildChromosomeToParent(int idParent, int idChild) {
        LinkedList<Integer> chromosomeParent = m_populationParent.getIndividual(idParent);
        LinkedList<Integer> chromosomeChild = m_populationChildren.getIndividual(idChild);
        for (int i = 0; i < m_problem.getNumOfItems(); i++) {
            chromosomeParent.set(i, chromosomeChild.get(i));
        }
    }

    private void assignParentMemeplexToChild(int idParent, int idChild) {
        Memeplex memeplexParent = m_populationParent.getMemeplex(idParent);
        Memeplex memeplexChild = m_populationChildren.getMemeplex(idChild);

        memeplexChild.setCrossoverOption((memeplexParent.getCrossoverOption()));
        memeplexChild.setMutationOption((memeplexParent.getMutationOption()));
        memeplexChild.setIoMOption((memeplexParent.getIoMOption()));
        memeplexChild.setLocalSearchOption((memeplexParent.getLocalSearchOption()));
        memeplexChild.setDoSOption((memeplexParent.getDoSOption()));
    }

    private void assignChildMemeplexToParent(int idParent, int idChild) {
        Memeplex memeplexParent = m_populationParent.getMemeplex(idParent);
        Memeplex memeplexChild = m_populationChildren.getMemeplex(idChild);

        memeplexParent.setCrossoverOption((memeplexChild.getCrossoverOption()));
        memeplexParent.setMutationOption((memeplexChild.getMutationOption()));
        memeplexParent.setIoMOption((memeplexChild.getIoMOption()));
        memeplexParent.setLocalSearchOption((memeplexChild.getLocalSearchOption()));
        memeplexParent.setDoSOption((memeplexChild.getDoSOption()));
    }

    public void reInitialise() {
        m_populationParent.reInitialise(true);
        m_populationChildren.reInitialise(false);

        m_loopCount = 0;
    }

    /**
     * Returns the objective value of the given individual
     * <p> Objective function :
     * <pre>
     * <code>f(s) = totalProfit, (if not exceed bin capacity)
     *      = min(profit) / (totalWeight - knapsackCapacity + 1), (otherwise)
     * </code>
     * </pre>
     */
    public double getObjectiveValue(boolean isParent, int individualId) {
        // objective function : f(s) = totalProfit
        LinkedList<Integer> individual;
        if (isParent) {
            individual = m_populationParent.getIndividual(individualId);
        } else {
            individual = m_populationChildren.getIndividual(individualId);
        }
        double totalWeight = 0;
        double totalProfit = 0;
        for (int i = 0; i < m_problem.getNumOfItems(); i++) {
            if (individual.get(i) == 1) {
                totalWeight += m_problem.getWeight(i);
                totalProfit += m_problem.getProfit(i);
            }
        }
        if (totalWeight <= m_problem.getKnapsackCapacity()) {
            // if not exceed bin capacity : f(s) = totalProfit
            return totalProfit;
        } else {
            // if exceed bin capacity :
            // penalty : f (s)= min(profit) / (totalWeight - knapsackCapacity + 1)
            return (m_problem.getMinProfit() / (totalWeight - m_problem.getKnapsackCapacity() + 1));
        }
    }

    public boolean terminationCirteriaMet() {
        m_loopCount++;
        return m_loopCount > NUMBER_OF_LOOPS;
    }

    public int applyTournamentSelection() {
        int bestIndex = -1;
        double highestObjectiveValue = -Double.MAX_VALUE;
        LinkedList<Integer> selectedIndex = new LinkedList<>();
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int crtIndex;
            do {
                crtIndex = m_rnd.nextInt(POPULATION_SIZE);
            } while (selectedIndex.contains(crtIndex));
            selectedIndex.add(crtIndex);
            double currentObjectiveValue = getObjectiveValue(true, crtIndex);
            if (currentObjectiveValue > highestObjectiveValue) {
                highestObjectiveValue = currentObjectiveValue;
                bestIndex = crtIndex;
            }
        }
        return bestIndex;
    }

    public void applyCrossover(int idParent1, int idParent2, int idChild1, int idChild2)
            throws ExecutionControl.NotImplementedException {
        int idCrossover = m_populationParent.getMemeplex(idParent1).getCrossoverOption();
        Crossover crossover = getCrossoverOnId(idCrossover);
        crossover.applyCrossover(m_rnd, m_problem, m_populationParent, m_populationChildren, idParent1, idParent2,
                idChild1, idChild2);
    }

    public void applyMemeticSimpleInheritance(int idParent1, int idChild1, int idChild2) {
        assignParentMemeplexToChild(idParent1, idChild1);
        assignParentMemeplexToChild(idParent1, idChild2);
    }

    public void applyMutationOrRuinRecreateWithIoM(int idChild) throws ExecutionControl.NotImplementedException {
        int idIoM = m_populationChildren.getMemeplex(idChild).getIoMOption();
        int intensityOfMutation = getIoMOnId(idIoM);
        int idMutationOrRuinRecreate = m_populationChildren.getMemeplex(idChild).getMutationOption();
        MutationRuinRecreate mutationRuinRecreate = getMutationOrRuinRecreateOnId(idMutationOrRuinRecreate);
        for (int i = 0; i < intensityOfMutation; i++) {
            mutationRuinRecreate.applyMutationOrRuinRecreate(m_rnd, m_problem, m_populationChildren, idChild);
        }
    }

    public void applyMutationOfMemeplex(int idChild) {
        if (m_rnd.nextDouble() < INNOVATION_RATE) {
            m_populationChildren.getMemeplex(idChild).setAnotherRandomCrossoverOption();
        }
        if (m_rnd.nextDouble() < INNOVATION_RATE) {
            m_populationChildren.getMemeplex(idChild).setAnotherRandomMutationOption();
        }
        if (m_rnd.nextDouble() < INNOVATION_RATE) {
            m_populationChildren.getMemeplex(idChild).setAnotherRandomIoMOption();
        }
        if (m_rnd.nextDouble() < INNOVATION_RATE) {
            m_populationChildren.getMemeplex(idChild).setAnotherRandomLocalSearchOption();
        }
        if (m_rnd.nextDouble() < INNOVATION_RATE) {
            m_populationChildren.getMemeplex(idChild).setAnotherRandomDoSOption();
        }
    }

    public void applyLocalSearchWithDoS(int idChild) throws ExecutionControl.NotImplementedException {
        int idDoS = m_populationChildren.getMemeplex(idChild).getDoSOption();
        int depthOfSearch = getDoSOnId(idDoS);
        int idLocalSearch = m_populationChildren.getMemeplex(idChild).getLocalSearchOption();
        LocalSearch localSearch = getLocalSearchOnId(idLocalSearch);
        for (int i = 0; i < depthOfSearch; i++) {
            localSearch.applyLocalSearch(m_rnd, m_problem, m_populationChildren, this, idChild);
        }
    }

    /**
     * Population replacement - Elitist replacement
     * <p> Replaces the current population with the offspring and replaces the worst offspring with the best solution
     * if the best is not contained in the offspring
     */
    public void applyPopulationReplacement() {
        double highestObjectiveValueParent = -Double.MAX_VALUE;
        double highestObjectiveValueChildren = -Double.MAX_VALUE;
        double lowestObjectiveValueChildren = Double.MAX_VALUE;
        int bestIndexParent = -1;
        int worstIndexChildren = -1;

        // loop through the population and find the best parent, the best children, and the worst children
        for (int i = 0; i < POPULATION_SIZE; i++) {
            double currentObjectiveValueParent = getObjectiveValue(true, i);
            double currentObjectiveValueChildren = getObjectiveValue(false, i);

            if (currentObjectiveValueParent > highestObjectiveValueParent) {
                highestObjectiveValueParent = currentObjectiveValueParent;
                bestIndexParent = i;
            }
            if (currentObjectiveValueChildren > highestObjectiveValueChildren) {
                highestObjectiveValueChildren = currentObjectiveValueChildren;
            }
            if (currentObjectiveValueChildren < lowestObjectiveValueChildren) {
                lowestObjectiveValueChildren = currentObjectiveValueChildren;
                worstIndexChildren = i;
            }
        }

        // if the best solution is in parent population, replace the worst in children with it
        if (highestObjectiveValueParent > highestObjectiveValueChildren) {
            assignParentChromosomeToChild(bestIndexParent, worstIndexChildren);
            assignParentMemeplexToChild(bestIndexParent, worstIndexChildren);
        }

        // do the replacement, i.e. replace the parent with the children
        for (int i = 0; i < POPULATION_SIZE; i++) {
            assignChildChromosomeToParent(i, i);
            assignChildMemeplexToParent(i, i);
        }
    }

    private Crossover getCrossoverOnId(int id) throws ExecutionControl.NotImplementedException {
        return switch (id) {
            case 0 -> new OnePTX();
            case 1 -> new TwoPTX();
            case 2 -> new UX();
            case 3 -> new IntersectAndSame();
            case 4 -> new IntersectAndBoth();
            case 5 -> new SameAndBoth();
            default -> throw new ExecutionControl.NotImplementedException("Invalid crossover id");
        };
    }

    private MutationRuinRecreate getMutationOrRuinRecreateOnId(int id) throws ExecutionControl.NotImplementedException {
        return switch (id) {
            case 0 -> new BitFlip();
            case 1 -> new ReplaceHighestProfitWithLowest();
            case 2 -> new ReplaceHighestProfitWithHighest();
            case 3 -> new ReplaceLowestProfitWithLowest();
            case 4 -> new ReplaceLowestProfitWithHighest();
            case 5 -> new ReplaceRandomWithHighestProfit();
            case 6 -> new ReplaceRandomWithLowestProfit();
            case 7 -> new ReplaceHighestWeightWithLowest();
            case 8 -> new ReplaceHighestWeightWithHighest();
            case 9 -> new ReplaceLowestWeightWithLowest();
            case 10 -> new ReplaceLowestWeightWithHighest();
            case 11 -> new ReplaceRandomWithHighestWeight();
            case 12 -> new ReplaceRandomWithLowestWeight();
            // TODO: HINT: a swap of the largest selected item with a smallest item that was not selected
            // TODO: THINK OF: lowest weight / highest value per weight
            // TODO: more ruinrecreate options
            default -> throw new ExecutionControl.NotImplementedException("Invalid mutation id");
        };
    }

    private int getIoMOnId(int id) throws ExecutionControl.NotImplementedException {
        return switch (id) {
            case 0 -> 1;
            case 1 -> 2;
            case 2 -> 3;
            case 3 -> 4;
            case 4 -> 5;
            case 5 -> 6;
            default -> throw new ExecutionControl.NotImplementedException("Invalid IoM id");
        };
    }

    private LocalSearch getLocalSearchOnId(int id) throws ExecutionControl.NotImplementedException {
        return switch (id) {
            case 0 -> new RMHC_OI();
            case 1 -> new RMHC_IE();
            case 2 -> new DBHC_OI();
            case 3 -> new DBHC_IE();
            case 4 -> new SAHC_OI();
            case 5 -> new SAHC_IE();
            case 6 -> new GAHC_OI();
            case 7 -> new GAHC_IE();
            case 8 -> new GAHC_Advanced_OI();
            case 9 -> new GAHC_Advanced_IE();
            case 10 -> new LeastImprovement_OI();
            case 11 -> new LeastImprovement_IE();
            // TODO: more local search options
            // TODO: for DB/GA/SA/LI HC If there is no improvement in a pass, the algorithm should be terminated.
            // TODO: for RMHC number of bit flips can be set i.e. RMHC_1  RMHC_2
            default -> throw new ExecutionControl.NotImplementedException("Invalid local search id");
        };
    }

    private int getDoSOnId(int id) throws ExecutionControl.NotImplementedException {
        return switch (id) {
            case 0 -> 1;
            case 1 -> 2;
            case 2 -> 3;
            case 3 -> 4;
            case 4 -> 5;
            case 5 -> 6;
            default -> throw new ExecutionControl.NotImplementedException("Invalid DoS id");
        };
    }
}
