package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Replaces the lowest profit item in the knapsack with the highest profit item out of knapsack
 * <p> only does replacement if there are at least 1 item in the knapsack and at least 1 item out of it
 */
public class ReplaceLowestProfitWithHighest extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int lowestProfitInIndex = -1;
        double lowestProfitIn = Double.MAX_VALUE;
        int highestProfitOutIndex = -1;
        double highestProfitOut = -Double.MAX_VALUE;

        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (chromosomeChild.get(i) == 1 && problem.getProfit(i) < lowestProfitIn) {
                lowestProfitIn = problem.getProfit(i);
                lowestProfitInIndex = i;
            }
            if (chromosomeChild.get(i) == 0 && problem.getProfit(i) > highestProfitOut) {
                highestProfitOut = problem.getProfit(i);
                highestProfitOutIndex = i;
            }
        }

        if (lowestProfitInIndex != -1 && highestProfitOutIndex != -1) {
            chromosomeChild.set(lowestProfitInIndex, 0);
            chromosomeChild.set(highestProfitOutIndex, 1);
        }
    }
}

