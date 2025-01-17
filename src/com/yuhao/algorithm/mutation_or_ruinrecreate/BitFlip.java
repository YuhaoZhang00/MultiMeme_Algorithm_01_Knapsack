package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * A random bit-flip (i.e. include / exclude an item in the knapsack)
 */
public class BitFlip extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int i = rnd.nextInt(problem.getNumOfItems());
        if (chromosomeChild.get(i) == 0) {
            chromosomeChild.set(i, 1);
            populationChildren.changeIndividualInfoIncludeItem(idChild, i);
        } else {
            chromosomeChild.set(i, 0);
            populationChildren.changeIndividualInfoExcludeItem(idChild, i);
        }
    }
}
