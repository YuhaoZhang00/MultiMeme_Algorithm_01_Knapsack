package com.yuhao;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Memeplex;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;
import com.yuhao.utils.MyFileReader;
import com.yuhao.utils.MyFileWriter;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import static com.yuhao.config.Constant.*;

/**
 * The Multimeme Memetic Algorithm
 *
 * <pre>
 * <code>[Initialisation] Create a population of random individuals
 * [Main Loop] while (termination criteria not satisfied) do
 *     Parent1 <- ApplyTournamentSelection()
 *     Parent2 <- ApplyTournamentSelection()
 *     Child <- ApplyCrossover(ParentXCrossover, Parent1, Parent2)  // ParentX: the parent of a better fitness
 *     Copy the meme of ParentX to Child  // ParentX: the parent of a better fitness
 *     Child <- ApplyMutation(ChildMutation, ChildIoM, Child)
 *     Mutate meme of Child with a probability of InnovationRate
 *     Child <- ApplyLocalSearch(ChildHillClimber, ChildDoS, Child)
 *     Child replaces the worst individual in the population
 * end [Main Loop]
 * </code>
 * </pre>
 * Inspired by : Ender Ozcan et al., A Self-adaptive Multimeme Memetic Algorithm Co-evolving Utility Scores to Control
 * Genetic Operators and Their Parameter Settings, pp. 11-13 (Link : http://www.cs.nott.ac.uk/~pszeo/docs/publications/multimemeAlgorithmChesc.pdf)
 */
public class CourseworkRunner {

    public static void main(String[] args) {
        try {
            MultimemeComponent algorithm = new MultimemeComponent();

            // loop for multiple trials
            for (int l = 0; l < NUMBER_OF_TRIALS; l++) {

                // ----------------------------------------
                MyFileWriter fileWriter = new MyFileWriter(algorithm.getProblemForTestUse().getNumOfItems(),
                        (int) algorithm.getProblemForTestUse().getKnapsackCapacity(), l + 1);
                // ----------------------------------------

                // initialise
                if (l != 0) {
                    algorithm.reInitialise();
                }

                // ----------------------------------------
//                testIndividualGenerationAndObjectiveValue(algorithm); // for test use only
                // ----------------------------------------

                // main loop
                while (!algorithm.terminationCriteriaMet()) {
                    for (int i = 0; i < POPULATION_SIZE; i += 2) {
                        // apply tournament selection to parents
                        int idParent1 = algorithm.applyTournamentSelection();
                        int idParent2 = algorithm.applyTournamentSelection();
                        int idChild1 = i;
                        int idChild2 = i + 1;

                        // make sure the objectiveValue of parent1 is greater than (or equal to) parent2,
                        // i.e. parent1 has a better fitness
                        if (algorithm.getObjectiveValue(true, idParent1) <
                                algorithm.getObjectiveValue(true, idParent2)) {
                            int temp = idParent1;
                            idParent1 = idParent2;
                            idParent2 = temp;
                        }

                        // apply crossover
                        algorithm.applyCrossover(idParent1, idParent2, idChild1, idChild2);

                        // apply memetic simple inheritance
                        algorithm.applyMemeticSimpleInheritance(idParent1, idChild1, idChild2);

                        // apply mutation / ruin-recreate with IoM for children
                        algorithm.applyMutationOrRuinRecreateWithIoM(idChild1);
                        algorithm.applyMutationOrRuinRecreateWithIoM(idChild2);

                        // apply mutation of memeplex for children
                        algorithm.applyMutationOfMemeplex(idChild1);
                        algorithm.applyMutationOfMemeplex(idChild2);

                        // apply local search with DoS for children
                        algorithm.applyLocalSearchWithDoS(idChild1);
                        algorithm.applyLocalSearchWithDoS(idChild2);

                    }
                    // apply elitist population replacement
                    algorithm.applyPopulationReplacement();

                    // ----------------------------------------
                    double highestObjectiveValue = -Double.MAX_VALUE;
                    double lowestObjectiveValue = Double.MAX_VALUE;
                    for (int i = 0; i < POPULATION_SIZE; i++) {
                        double currentObjectiveValue = algorithm.getObjectiveValue(true, i);
                        if (currentObjectiveValue > highestObjectiveValue) {
                            highestObjectiveValue = currentObjectiveValue;
                        }
                        if (currentObjectiveValue < lowestObjectiveValue) {
                            lowestObjectiveValue = currentObjectiveValue;
                        }
                    }
                    fileWriter.writeToFile(highestObjectiveValue, lowestObjectiveValue);
                    // ----------------------------------------
                }

                // ----------------------------------------
//                testIndividualGenerationAndObjectiveValue(algorithm); // for test use only
                showBestSolutionPerTrial(l, algorithm);
                // ----------------------------------------

            }
        } catch (IOException | ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints the best solution and its encoding per trial to standard output
     */
    private static void showBestSolutionPerTrial(int l, MultimemeComponent algorithm) {
        System.out.println("Trial#" + (l + 1) + ":");
        double highestObjectiveValue = -Double.MAX_VALUE;
        LinkedList<Integer> chromosomeOfHighestObjectiveValue = null;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            double currentObjectiveValue = algorithm.getObjectiveValue(true, i);
            if (currentObjectiveValue > highestObjectiveValue) {
                highestObjectiveValue = currentObjectiveValue;
                chromosomeOfHighestObjectiveValue = algorithm.getPopulationParentForTestUse().getIndividual(i);
            }
        }
        System.out.println(highestObjectiveValue);
        for (int i = 0; i < algorithm.getProblemForTestUse().getNumOfItems(); i++) {
            System.out.print(chromosomeOfHighestObjectiveValue.get(i));
        }
        System.out.println();
    }

    /**
     * NOTE: for test use only
     */
    private static void testIndividualGenerationAndObjectiveValue(MultimemeComponent algorithm) {
        Problem problem = algorithm.getProblemForTestUse();
        Population populationParent = algorithm.getPopulationParentForTestUse();
        Population populationChildren = algorithm.getPopulationChildrenForTestUse();

        System.out.println("===testIndividualGenerationAndObjectiveValue===");
        System.out.println("---problem---");
        System.out.println("number of items : " + problem.getNumOfItems());
        System.out.println("knapsack capacity : " + problem.getKnapsackCapacity());
        System.out.println("min profit : " + problem.getMinProfit());
        System.out.println("---populationParent---");
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (i < 10) {
                System.out.print(" " + i + " : ");
            } else {
                System.out.print(i + " : ");
            }
            for (int allele : populationParent.getIndividual(i)) {
                System.out.print(allele);
            }
            System.out.print(" - ");
            System.out.print(populationParent.getIndividualTotalProfit(i) + " ");
            System.out.print(populationParent.getIndividualTotalWeight(i) + " ");
            System.out.print("- ");
            Memeplex m = populationParent.getMemeplex(i);
            System.out.print(m.getCrossoverOption() + " ");
            System.out.print(m.getMutationOption() + " ");
            System.out.print(m.getIoMOption() + " ");
            System.out.print(m.getLocalSearchOption() + " ");
            System.out.print(m.getDoSOption() + " ");
            System.out.println("- " + algorithm.getObjectiveValue(true, i));
        }
        System.out.println("---populationChildren---");
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (i < 10) {
                System.out.print(" " + i + " : ");
            } else {
                System.out.print(i + " : ");
            }
            for (int allele : populationChildren.getIndividual(i)) {
                System.out.print(allele);
            }
            System.out.print(" - ");
            System.out.print(populationChildren.getIndividualTotalProfit(i) + " ");
            System.out.print(populationChildren.getIndividualTotalWeight(i) + " ");
            System.out.print("- ");
            Memeplex m = populationChildren.getMemeplex(i);
            System.out.print(m.getCrossoverOption() + " ");
            System.out.print(m.getMutationOption() + " ");
            System.out.print(m.getIoMOption() + " ");
            System.out.print(m.getLocalSearchOption() + " ");
            System.out.print(m.getDoSOption() + " ");
            System.out.println("- " + algorithm.getObjectiveValue(false, i));
        }
        System.out.println("===end===");
    }
}
