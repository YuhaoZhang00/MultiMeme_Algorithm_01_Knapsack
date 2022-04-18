package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

import static com.yuhao.config.Constant.MAXIMUM_RR_PERCENTAGE;

/**
 * Ruins a random number of alleles (which is guaranteed to be smaller than {@code MAXIMUM_RR_PERCENTAGE *
 * population_size}) and recreate them of random 0 or 1
 */
public class RRandomRRandom extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        double ruinProbability = rnd.nextDouble(MAXIMUM_RR_PERCENTAGE);
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);

        // ruin & recreate (at the same time)
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (rnd.nextDouble() < ruinProbability) {
                if (rnd.nextDouble() < 0.5) {
                    if (chromosomeChild.get(i) == 1) {
                        chromosomeChild.set(i, 0);
                        populationChildren.changeIndividualInfoExcludeItem(idChild, i);
                    }
                } else {
                    if (chromosomeChild.get(i) == 0) {
                        chromosomeChild.set(i, 1);
                        populationChildren.changeIndividualInfoIncludeItem(idChild, i);
                    }
                }
            }
        }
    }
}
