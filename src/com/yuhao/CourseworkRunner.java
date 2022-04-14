package com.yuhao;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Memeplex;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;
import com.yuhao.utils.MyFileReader;
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
 * <code>[Initialisation] Create a population of random individuals; apply a random hill climber to each individual
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

    // TODO: delta evaluation

    public static void main(String[] args) {
        MultimemeComponent algorithm = new MultimemeComponent();

        testIndividualGenerationAndObjectiveValue(algorithm); // TODO: comment this out

        while (!algorithm.terminationCirteriaMet()) {
            for (int i = 0; i < POPULATION_SIZE; i += 2) {
                int idParent1 = algorithm.applyTournamentSelection();
                int idParent2 = algorithm.applyTournamentSelection();
                int idChild1 = i;
                int idChild2 = i + 1;

                // make sure the objectiveValue of parent1 is greater than (or equal to) parent2,
                // i.e. parent1 has a better fitness
                if (algorithm.getObjectiveValue(true, idParent1) < algorithm.getObjectiveValue(true, idParent2)) {
                    int temp = idParent1;
                    idParent1 = idParent2;
                    idParent2 = temp;
                }

                try {
                    algorithm.applyCrossoverWithIoM(idParent1, idParent2, idChild1, idChild2);

                    algorithm.applyMemeticSimpleInheritance(idParent1, idChild1, idChild2);

                    algorithm.applyMutationOrRuinRecreateWithIoM(idChild1);
                    algorithm.applyMutationOrRuinRecreateWithIoM(idChild2);

                    algorithm.applyMutationOfMemeplex(idChild1);
                    algorithm.applyMutationOfMemeplex(idChild2);

                    algorithm.applyLocalSearchWithDoS(idChild1);
                    algorithm.applyLocalSearchWithDoS(idChild2);
                } catch (ExecutionControl.NotImplementedException e) {
                    e.printStackTrace();
                }
            }
            algorithm.applyPopulationReplacement();
        }

        testIndividualGenerationAndObjectiveValue(algorithm); // TODO: comment this out
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
            System.out.print(i + " : ");
            for (int allele : populationParent.getIndividual(i)) {
                System.out.print(allele + " ");
            }
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
            System.out.print(i + " : ");
            for (int allele : populationChildren.getIndividual(i)) {
                System.out.print(allele + " ");
            }
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
