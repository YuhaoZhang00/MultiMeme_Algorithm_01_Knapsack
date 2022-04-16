package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Replaces the lowest weight item in the knapsack with the highest weight item out of knapsack
 * <p> only does replacement if there are at least 1 item in the knapsack and at least 1 item out of it
 */
public class ReplaceLowestWeightWithHighest extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int lowestWeightInIndex = -1;
        double lowestWeightIn = Double.MAX_VALUE;
        int highestWeightOutIndex = -1;
        double highestWeightOut = -Double.MAX_VALUE;

        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (chromosomeChild.get(i) == 1 && problem.getWeight(i) < lowestWeightIn) {
                lowestWeightIn = problem.getWeight(i);
                lowestWeightInIndex = i;
            }
            if (chromosomeChild.get(i) == 0 && problem.getWeight(i) > highestWeightOut) {
                highestWeightOut = problem.getWeight(i);
                highestWeightOutIndex = i;
            }
        }

        if (lowestWeightInIndex != -1 && highestWeightOutIndex != -1) {
            chromosomeChild.set(lowestWeightInIndex, 0);
            chromosomeChild.set(highestWeightOutIndex, 1);
        }
    }
}
