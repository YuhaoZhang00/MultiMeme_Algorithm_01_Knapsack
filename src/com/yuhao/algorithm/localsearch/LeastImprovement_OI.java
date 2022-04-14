package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Least Improvement Hill Climbing accepting Only Improving moves
 * <p> accepts the neighbor with the lowest objective value (which is greater than current objective value)
 */
public class LeastImprovement_OI extends LocalSearch {
    @Override
    public void applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                 MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);

        double currentObjectiveValue = algorithm.getObjectiveValue(false, idChild);
        int lowestObjectiveValue = Integer.MAX_VALUE;
        int bestIndex = -1;
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            chromosomeChild.set(i, (chromosomeChild.get(i) == 0) ? 1 : 0);
            double neighborObjectiveValue = algorithm.getObjectiveValue(false, idChild);
            if (neighborObjectiveValue > currentObjectiveValue && neighborObjectiveValue < lowestObjectiveValue) {
                bestIndex = i;
                currentObjectiveValue = neighborObjectiveValue;
            }
            chromosomeChild.set(i, (chromosomeChild.get(i) == 0) ? 1 : 0);
        }
        if (bestIndex != -1) {
            chromosomeChild.set(bestIndex, (chromosomeChild.get(bestIndex) == 0) ? 1 : 0);
        }
    }
}
