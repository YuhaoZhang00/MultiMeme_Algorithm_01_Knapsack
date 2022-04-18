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
    public boolean applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                 MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);

        double highestObjectiveValue = algorithm.getObjectiveValue(false, idChild);
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
            if (neighborObjectiveValue > highestObjectiveValue) {
                bestIndex = i;
                highestObjectiveValue = neighborObjectiveValue;
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
