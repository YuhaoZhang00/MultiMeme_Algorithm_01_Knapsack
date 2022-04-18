package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Replaces a random item in the knapsack with the highest profit item out of knapsack
 * <p> only does replacement if there are at least 1 item in the knapsack and at least 1 item out of it
 * <br> idea: exploit on "item with the highest profits in the knapsack"
 */
public class ReplaceRandomWithHighestProfit extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        LinkedList<Integer> itemsInKnapsack = new LinkedList<>();

        int highestProfitOutIndex = -1;
        double highestProfitOut = -Double.MAX_VALUE;
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (chromosomeChild.get(i) == 1) {
                itemsInKnapsack.add(i);
            }
            if (chromosomeChild.get(i) == 0 && problem.getProfit(i) > highestProfitOut) {
                highestProfitOut = problem.getProfit(i);
                highestProfitOutIndex = i;
            }
        }

        if (itemsInKnapsack.size() != 0 && highestProfitOutIndex != -1) {
            int inIndex = itemsInKnapsack.get(rnd.nextInt(itemsInKnapsack.size()));
            chromosomeChild.set(inIndex, 0);
            chromosomeChild.set(highestProfitOutIndex, 1);
            populationChildren.changeIndividualInfoExcludeItem(idChild, inIndex);
            populationChildren.changeIndividualInfoIncludeItem(idChild, highestProfitOutIndex);
        }
    }
}
