package com.yuhao.algorithm.crossover;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

/**
 * Put the items that are included by at least one of the parents to a child, and items that are included by only one
 * parents to another child
 * <p> like the {@code IntersectAndSame} way of crossover but this time we think of excluded items. One child
 * avoids items that are excluded by both parents, and the other one avoids items that are excluded by only one parents
 */
public class IntersectAndBoth extends Crossover {
    @Override
    public void applyCrossover(Random rnd, Problem problem, Population populationParent, Population populationChildren,
                               int idParent1, int idParent2, int idChild1, int idChild2) {
        LinkedList<Integer> chromosomeParent1 = populationParent.getIndividual(idParent1);
        LinkedList<Integer> chromosomeParent2 = populationParent.getIndividual(idParent2);
        LinkedList<Integer> chromosomeChild1 = new LinkedList<>();
        LinkedList<Integer> chromosomeChild2 = new LinkedList<>();

        for (int allele = 0; allele < problem.getNumOfItems(); allele++) {
            chromosomeChild1.add(chromosomeParent1.get(allele) | chromosomeParent2.get(allele));
            chromosomeChild2.add(chromosomeParent1.get(allele) ^ chromosomeParent2.get(allele));
        }

        populationChildren.setIndividual(idChild1, chromosomeChild1);
        populationChildren.setIndividual(idChild2, chromosomeChild2);
    }
}
