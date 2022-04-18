package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Random Mutation Hill Climbing accepting Only Improving moves
 * <p> makes a random bit-flip and accepts it if the objective value is higher than the original one
 */
public class RMHC_OI extends LocalSearch {
    @Override
    public boolean applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                 MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        int i = rnd.nextInt(problem.getNumOfItems());

        double currentObjectiveValue = algorithm.getObjectiveValue(false, idChild);

        if (chromosomeChild.get(i) == 0) {
            chromosomeChild.set(i, 1);
            populationChildren.changeIndividualInfoIncludeItem(idChild, i);
        } else {
            chromosomeChild.set(i, 0);
            populationChildren.changeIndividualInfoExcludeItem(idChild, i);
        }
        double neighborObjectiveValue = algorithm.getObjectiveValue(false, idChild);
        if (neighborObjectiveValue > currentObjectiveValue) {
            return true;
        }
        if (chromosomeChild.get(i) == 0) {
            chromosomeChild.set(i, 1);
            populationChildren.changeIndividualInfoIncludeItem(idChild, i);
        } else {
            chromosomeChild.set(i, 0);
            populationChildren.changeIndividualInfoExcludeItem(idChild, i);
        }
        return true;
    }
}
