package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Replaces a random item in the knapsack with the highest 'profit per weight' item out of knapsack
 * <p> only does replacement if there are at least 1 item in the knapsack and at least 1 item out of it
 */
public class ReplaceRandomWithHighestVPW extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        LinkedList<Integer> itemsInKnapsack = new LinkedList<>();

        int highestVPWOutIndex = -1;
        double highestVPWOut = -Double.MAX_VALUE;
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (chromosomeChild.get(i) == 1) {
                itemsInKnapsack.add(i);
            }
            if (chromosomeChild.get(i) == 0 && problem.getProfit(i) / problem.getWeight(i) > highestVPWOut) {
                highestVPWOut = problem.getProfit(i) / problem.getWeight(i);
                highestVPWOutIndex = i;
            }
        }

        if (itemsInKnapsack.size() != 0 && highestVPWOutIndex != -1) {
            chromosomeChild.set(itemsInKnapsack.get(rnd.nextInt(itemsInKnapsack.size())), 0);
            chromosomeChild.set(highestVPWOutIndex, 1);
        }
    }
}