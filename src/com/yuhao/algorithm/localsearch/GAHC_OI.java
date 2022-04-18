package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Gradient Ascent / Next Ascent / First Improvement Hill Climbing accepting Only Improving moves
 * <p> starts from a random point and accepts the first neighbor with the objective value which is greater than current
 * objective value
 */
public class GAHC_OI extends LocalSearch {
    @Override
    public boolean applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                 MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int j = rnd.nextInt(problem.getNumOfItems());

        double highestObjectiveValue = algorithm.getObjectiveValue(false, idChild);
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            int index = (i + j) % problem.getNumOfItems();
            if (chromosomeChild.get(index) == 0) {
                chromosomeChild.set(index, 1);
                populationChildren.changeIndividualInfoIncludeItem(idChild, index);
            } else {
                chromosomeChild.set(index, 0);
                populationChildren.changeIndividualInfoExcludeItem(idChild, index);
            }
            double neighborObjectiveValue = algorithm.getObjectiveValue(false, idChild);
            if (neighborObjectiveValue > highestObjectiveValue) {
                return true;
            } else {
                if (chromosomeChild.get(index) == 0) {
                    chromosomeChild.set(index, 1);
                    populationChildren.changeIndividualInfoIncludeItem(idChild, index);
                } else {
                    chromosomeChild.set(index, 0);
                    populationChildren.changeIndividualInfoExcludeItem(idChild, index);
                }
            }
        }
        return false;
    }
}
