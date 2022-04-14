package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Random Mutation Hill Climbing accepting Improving or Equal moves
 * <p> makes a random bit-flip and accepts it if the objective value is higher than or equal to the original one
 */
public class RMHC_IE extends LocalSearch {
    @Override
    public void applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                 MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int i = rnd.nextInt(problem.getNumOfItems());

        double currentObjectiveValue = algorithm.getObjectiveValue(false, idChild);

        chromosomeChild.set(i, (chromosomeChild.get(i) == 0) ? 1 : 0);
        double neighborObjectiveValue = algorithm.getObjectiveValue(false, idChild);
        if (neighborObjectiveValue >= currentObjectiveValue) {
            return;
        }
        chromosomeChild.set(i, (chromosomeChild.get(i) == 0) ? 1 : 0);
    }
}
