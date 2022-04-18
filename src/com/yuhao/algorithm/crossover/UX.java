package com.yuhao.algorithm.crossover;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Uniform Crossover
 * <p> for each allele in the chromosome there is a probability (0.5) of crossover
 */
public class UX extends Crossover {
    @Override
    public void applyCrossover(Random rnd, Problem problem, Population populationParent, Population populationChildren,
                               int idParent1, int idParent2, int idChild1, int idChild2) {
        LinkedList<Integer> chromosomeParent1 = populationParent.getIndividual(idParent1);
        LinkedList<Integer> chromosomeParent2 = populationParent.getIndividual(idParent2);
        LinkedList<Integer> chromosomeChild1 = new LinkedList<>();
        LinkedList<Integer> chromosomeChild2 = new LinkedList<>();

        for (int allele = 0; allele < problem.getNumOfItems(); allele++) {
            if (rnd.nextDouble() < 0.5) {
                chromosomeChild1.add(chromosomeParent2.get(allele));
                chromosomeChild2.add(chromosomeParent1.get(allele));
            } else {
                chromosomeChild1.add(chromosomeParent1.get(allele));
                chromosomeChild2.add(chromosomeParent2.get(allele));
            }
        }

        populationChildren.setIndividual(idChild1, chromosomeChild1);
        populationChildren.setIndividual(idChild2, chromosomeChild2);
        populationChildren.changeIndividualInfoRecalculate(idChild1);
        populationChildren.changeIndividualInfoRecalculate(idChild2);
    }
}
