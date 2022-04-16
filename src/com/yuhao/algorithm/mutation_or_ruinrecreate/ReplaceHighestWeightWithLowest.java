package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Replaces the highest weight item in the knapsack with the lowest weight item out of knapsack
 * <p> only does replacement if there are at least 1 item in the knapsack and at least 1 item out of it
 */
public class ReplaceHighestWeightWithLowest extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int highestWeightInIndex = -1;
        double highestWeightIn = -Double.MAX_VALUE;
        int lowestWeightOutIndex = -1;
        double lowestWeightOut = Double.MAX_VALUE;

        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (chromosomeChild.get(i) == 1 && problem.getWeight(i) > highestWeightIn) {
                highestWeightIn = problem.getWeight(i);
                highestWeightInIndex = i;
            }
            if (chromosomeChild.get(i) == 0 && problem.getWeight(i) < lowestWeightOut) {
                lowestWeightOut = problem.getWeight(i);
                lowestWeightOutIndex = i;
            }
        }

        if (highestWeightInIndex != -1 && lowestWeightOutIndex != -1) {
            chromosomeChild.set(highestWeightInIndex, 0);
            chromosomeChild.set(lowestWeightOutIndex, 1);
        }
    }
}
