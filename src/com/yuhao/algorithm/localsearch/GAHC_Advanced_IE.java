package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * an advanced First Improvement Hill Climbing accepting Improving or Equal moves
 * <p> starts from a random point, does the bit-flip through the whole loop while updating objective value at the same
 * time, and accepts every bit-flip that results in an objective value greater than or equal to current objective value
 */
public class GAHC_Advanced_IE extends LocalSearch {
    @Override
    public void applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                 MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int j = rnd.nextInt(problem.getNumOfItems());

        double highestObjectiveValue = algorithm.getObjectiveValue(false, idChild);
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            int index = (i + j) % problem.getNumOfItems();
            chromosomeChild.set(index, (chromosomeChild.get(index) == 0) ? 1 : 0);
            double neighborObjectiveValue = algorithm.getObjectiveValue(false, idChild);
            if (neighborObjectiveValue >= highestObjectiveValue) {
                highestObjectiveValue = neighborObjectiveValue;
            } else {
                chromosomeChild.set(index, (chromosomeChild.get(index) == 0) ? 1 : 0);
            }
        }
    }
}
