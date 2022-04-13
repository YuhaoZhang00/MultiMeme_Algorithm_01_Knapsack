package com.yuhao.algorithm;

import com.yuhao.algorithm.crossover.Crossover;
import com.yuhao.algorithm.crossover.OnePTX;
import com.yuhao.algorithm.crossover.TwoPTX;
import com.yuhao.algorithm.crossover.UX;
import com.yuhao.algorithm.localsearch.*;
import com.yuhao.algorithm.mutation_or_ruinrecreate.BitFlip;
import com.yuhao.algorithm.mutation_or_ruinrecreate.MutationRuinRecreate;
import com.yuhao.data.Memeplex;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;
import jdk.jshell.spi.ExecutionControl;

import java.util.LinkedList;
import java.util.Random;

import static com.yuhao.config.Constant.*;

public class MultimemeComponent {
    private Random m_rnd;
    private Problem m_problem;
    private Population m_populationParent;
    private Population m_populationChildren;

    public MultimemeComponent(Random rnd, Problem problem, Population populationParent, Population populationChildren) {
        m_rnd = rnd;
        m_problem = problem;
        m_populationParent = populationParent;
        m_populationChildren = populationChildren;
    }

    /**
     * Returns the objective value of the given individual
     *
     * <p> Objective function :
     * <pre>
     * <code>f(s) = totalProfit, (if not exceed bin capacity)
     *      = min(profit) / (totalWeight - knapsackCapacity + 1), (otherwise)
     * </code>
     * </pre>
     */
    public int getObjectiveValue(boolean isParent, int individualId) {
        // objective function : f(s) = totalProfit
        LinkedList<Integer> individual;
        if (isParent) {
            individual = m_populationParent.getIndividual(individualId);
        } else {
            individual = m_populationChildren.getIndividual(individualId);
        }
        int totalWeight = 0;
        int totalProfit = 0;
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

    public int applyTournamentSelection() {
        int bestIndex = -1;
        int bestFitness = -1;
        LinkedList<Integer> selectedIndex = new LinkedList<>();
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int crtIndex;
            do {
                crtIndex = m_rnd.nextInt(POPULATION_SIZE);
            } while (selectedIndex.contains(crtIndex));
            selectedIndex.add(crtIndex);
            int fitness = getObjectiveValue(true, crtIndex);
            if (fitness > bestFitness) {
                bestFitness = fitness;
                bestIndex = crtIndex;
            }
        }
        return bestIndex;
    }

    public void applyCrossoverWithIoM(int idParent1, int idParent2, int idChild1, int idChild2)
            throws ExecutionControl.NotImplementedException {
        int idIoM = m_populationParent.getMemeplex(idParent1).getIoMOption();
        int intensityOfMutation = getIoMOnId(idIoM);
        int idCrossover = m_populationParent.getMemeplex(idParent1).getCrossoverOption();
        Crossover crossover = getCrossoverOnId(idCrossover);
        for (int i = 0; i < intensityOfMutation; i++) {
            crossover.applyCrossover(m_rnd, m_problem, m_populationParent, m_populationChildren, idParent1, idParent2,
                    idChild1, idChild2);
        }
    }

    public void applyMemeticSimpleInheritance(int idParent1, int idChild1, int idChild2) {
        assignParentMemeplexToChild(idParent1, idChild1);
        assignParentMemeplexToChild(idParent1, idChild2);
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
        System.out.println(idChild);
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

    public void applyPopulationReplacement() {
        
    }

    private Crossover getCrossoverOnId(int id) throws ExecutionControl.NotImplementedException {
        return switch (id) {
            case 0 -> new OnePTX();
            case 1 -> new TwoPTX();
            case 2 -> new UX(); // TODO: more crossover options (if possible)
            default -> throw new ExecutionControl.NotImplementedException("Invalid crossover id");
        };
    }

    private MutationRuinRecreate getMutationOrRuinRecreateOnId(int id) throws ExecutionControl.NotImplementedException {
        return switch (id) {
            case 0 -> new BitFlip();
            case 1 -> new BitFlip(); // TODO: more mutation or ruinrecreate options
            case 2 -> new BitFlip(); // TODO: more mutation or ruinrecreate options
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
            case 11 -> new LeastImprovement_IE(); // TODO: more local search options
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
