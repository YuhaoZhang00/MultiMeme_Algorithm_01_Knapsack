package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Least Improvement Hill Climbing accepting Improving or Equal moves
 * <p> accepts the neighbor with the lowest objective value (which is greater than or equal to current objective value)
 */
public class LeastImprovement_IE extends LocalSearch {
    @Override
    public boolean applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                    MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);

        double currentObjectiveValue = algorithm.getObjectiveValue(false, idChild);
        int lowestObjectiveValue = Integer.MAX_VALUE;
        int bestIndex = -1;
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (chromosomeChild.get(i) == 0) {
                chromosomeChild.set(i, 1);
                populationChildren.changeIndividualInfoIncludeItem(idChild, i);
            } else {
                chromosomeChild.set(i, 0);
                populationChildren.changeIndividualInfoExcludeItem(idChild, i);
            }
            double neighborObjectiveValue = algorithm.getObjectiveValue(false, idChild);
            if (neighborObjectiveValue >= currentObjectiveValue && neighborObjectiveValue < lowestObjectiveValue) {
                bestIndex = i;
                currentObjectiveValue = neighborObjectiveValue;
            }
            if (chromosomeChild.get(i) == 0) {
                chromosomeChild.set(i, 1);
                populationChildren.changeIndividualInfoIncludeItem(idChild, i);
            } else {
                chromosomeChild.set(i, 0);
                populationChildren.changeIndividualInfoExcludeItem(idChild, i);
            }
        }
        if (bestIndex != -1) {
            if (chromosomeChild.get(bestIndex) == 0) {
                chromosomeChild.set(bestIndex, 1);
                populationChildren.changeIndividualInfoIncludeItem(idChild, bestIndex);
            } else {
                chromosomeChild.set(bestIndex, 0);
                populationChildren.changeIndividualInfoExcludeItem(idChild, bestIndex);
            }
            return true;
        }
        return false;
    }
}
