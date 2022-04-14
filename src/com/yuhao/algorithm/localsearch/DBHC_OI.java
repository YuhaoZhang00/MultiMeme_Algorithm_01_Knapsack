package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Davis' Bit Hill Climbing accepting Only Improving moves
 * <p> does the bit-flip for all alleles in random order from a permutation while updating objective value at the same
 * time, and accepts every bit-flip that results in an objective value greater than the original one
 */
public class DBHC_OI extends LocalSearch {
    @Override
    public void applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                 MultimemeComponent algorithm, int idChild) {
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);

        // create a random permutation
        ArrayList<Integer> permutation = new ArrayList<>(Collections.nCopies(problem.getNumOfItems(), -1));
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            int j = rnd.nextInt(problem.getNumOfItems() - i);
            while (permutation.get(j) != -1) {
                j = (j + 1) % problem.getNumOfItems();
            }
            permutation.set(j, i);
        }

        double highestObjectiveValue = algorithm.getObjectiveValue(false, idChild);
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            int index = permutation.get(i);
            chromosomeChild.set(index, (chromosomeChild.get(index) == 0) ? 1 : 0);
            double neighborObjectiveValue = algorithm.getObjectiveValue(false, idChild);
            if (neighborObjectiveValue > highestObjectiveValue) {
                highestObjectiveValue = neighborObjectiveValue;
            } else {
                chromosomeChild.set(index, (chromosomeChild.get(index) == 0) ? 1 : 0);
            }
        }
    }
}
