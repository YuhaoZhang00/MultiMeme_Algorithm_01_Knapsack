package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Replaces the highest profit item in the knapsack with the lowest profit item out of knapsack
 * <p> only does replacement if there are at least 1 item in the knapsack and at least 1 item out of it
 */
public class ReplaceHighestProfitWithLowest extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int highestProfitInIndex = -1;
        double highestProfitIn = -Double.MAX_VALUE;
        int lowestProfitOutIndex = -1;
        double lowestProfitOut = Double.MAX_VALUE;

        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (chromosomeChild.get(i) == 1 && problem.getProfit(i) > highestProfitIn) {
                highestProfitIn = problem.getProfit(i);
                highestProfitInIndex = i;
            }
            if (chromosomeChild.get(i) == 0 && problem.getProfit(i) < lowestProfitOut) {
                lowestProfitOut = problem.getProfit(i);
                lowestProfitOutIndex = i;
            }
        }

        if (highestProfitInIndex != -1 && lowestProfitOutIndex != -1) {
            chromosomeChild.set(highestProfitInIndex, 0);
            chromosomeChild.set(lowestProfitOutIndex, 1);
        }
    }
}
