package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Replaces the lowest weight item in the knapsack with the lowest weight item out of knapsack
 * <p> only does replacement if there are at least 1 item in the knapsack and at least 1 item out of it
 */
public class ReplaceLowestWeightWithLowest extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int lowestWeightInIndex = -1;
        double lowestWeightIn = Double.MAX_VALUE;
        int lowestWeightOutIndex = -1;
        double lowestWeightOut = Double.MAX_VALUE;

        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (chromosomeChild.get(i) == 1 && problem.getWeight(i) < lowestWeightIn) {
                lowestWeightIn = problem.getWeight(i);
                lowestWeightInIndex = i;
            }
            if (chromosomeChild.get(i) == 0 && problem.getWeight(i) < lowestWeightOut) {
                lowestWeightOut = problem.getWeight(i);
                lowestWeightOutIndex = i;
            }
        }

        if (lowestWeightInIndex != -1 && lowestWeightOutIndex != -1) {
            chromosomeChild.set(lowestWeightInIndex, 0);
            chromosomeChild.set(lowestWeightOutIndex, 1);
            populationChildren.changeIndividualInfoExcludeItem(idChild, lowestWeightInIndex);
            populationChildren.changeIndividualInfoIncludeItem(idChild, lowestWeightOutIndex);
        }
    }
}
