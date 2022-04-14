package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Steepest Ascent / Best Improvement Hill Climbing accepting Only Improving moves
 * <p> accepts the neighbor with the highest objective value (which is greater than current objective value)
 */
public class SAHC_OI extends LocalSearch {
    @Override
    public void applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                 MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);

        double highestObjectiveValue = algorithm.getObjectiveValue(false, idChild);
        int bestIndex = -1;
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            chromosomeChild.set(i, (chromosomeChild.get(i) == 0) ? 1 : 0);
            double neighborObjectiveValue = algorithm.getObjectiveValue(false, idChild);
            if (neighborObjectiveValue > highestObjectiveValue) {
                bestIndex = i;
                highestObjectiveValue = neighborObjectiveValue;
            }
            chromosomeChild.set(i, (chromosomeChild.get(i) == 0) ? 1 : 0);
        }
        if (bestIndex != -1) {
            chromosomeChild.set(bestIndex, (chromosomeChild.get(bestIndex) == 0) ? 1 : 0);
        }
    }
}
