package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

import static com.yuhao.config.Constant.MAXIMUM_RR_PERCENTAGE;

/**
 * Ruins a random number of alleles (which is guaranteed to be smaller than {@code MAXIMUM_RR_PERCENTAGE *
 * population_size}), and recreates by including {@code n} lowest weight items in the ruined set in knapsack, while
 * {@code n} is exactly the number of items that are excluded by the ruin step
 */
public class RRandomRLowestWeight extends MutationRuinRecreate {
    @Override
    public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren, int idChild) {
        class Entry implements Comparable<Entry> {
            private Integer m_index;
            private Double m_weight;

            Entry(int index, double weight) {
                m_index = index;
                m_weight = weight;
            }

            @Override
            public int compareTo(Entry other) {
                return this.m_weight.compareTo(other.m_weight);
            }
        }

        double ruinProbability = rnd.nextDouble(MAXIMUM_RR_PERCENTAGE);
        LinkedList<Integer> chromosomeChild = populationChildren.getIndividual(idChild);
        PriorityQueue<Entry> itemsToRuinWithWeight = new PriorityQueue<>();
        int n = 0;

        // ruin
        for (int i = 0; i < problem.getNumOfItems(); i++) {
            if (rnd.nextDouble() < ruinProbability) {
                if (chromosomeChild.get(i) == 1) {
                    chromosomeChild.set(i, 0);
                    populationChildren.changeIndividualInfoExcludeItem(idChild, i);
                    n++;
                }
                itemsToRuinWithWeight.add(new Entry(i, problem.getWeight(i)));
            }
        }

        // recreate
        for (int i = 0; i < n; i++) {
            chromosomeChild.set(itemsToRuinWithWeight.peek().m_index, 1);
            populationChildren.changeIndividualInfoIncludeItem(idChild, itemsToRuinWithWeight.poll().m_index);
        }
    }
}
