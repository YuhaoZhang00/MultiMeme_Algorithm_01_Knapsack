package com.yuhao.algorithm.crossover;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.Random;

public class TwoPTX extends Crossover {
    @Override
    public void applyCrossover(Random rnd, Problem problem, Population populationParent, Population populationChildren,
                               int idParent1, int idParent2, int idChild1, int idChild2) {
        LinkedList<Integer> chromosomeParent1 = populationParent.getIndividual(idParent1);
        LinkedList<Integer> chromosomeParent2 = populationParent.getIndividual(idParent2);
        LinkedList<Integer> chromosomeChild1 = new LinkedList<>();
        LinkedList<Integer> chromosomeChild2 = new LinkedList<>();

        int i = rnd.nextInt(problem.getNumOfItems());
        int j = rnd.nextInt(problem.getNumOfItems());
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }

        for (int allele = 0; allele < problem.getNumOfItems(); allele++) {
            if (allele < i || allele >= j) {
                chromosomeChild1.add(chromosomeParent2.get(allele));
                chromosomeChild2.add(chromosomeParent1.get(allele));
            } else {
                chromosomeChild1.add(chromosomeParent1.get(allele));
                chromosomeChild2.add(chromosomeParent2.get(allele));
            }
        }

        populationChildren.setIndividual(idChild1, chromosomeChild1);
        populationChildren.setIndividual(idChild2, chromosomeChild2);
    }
}