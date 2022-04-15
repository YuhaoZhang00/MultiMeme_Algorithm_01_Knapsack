package com.yuhao.data;

import java.util.LinkedList;
import java.util.Random;

import static com.yuhao.config.Constant.*;

public class Population {
    /**
     * list of individuals
     */
    private LinkedList<LinkedList<Integer>> m_population;

    /**
     * list of memes of each individual
     */
    private LinkedList<Memeplex> m_memeplexs;

    /**
     * chromosome length of each individual
     */
    private int m_chromosomeLength;

    private Random rnd;

    public LinkedList<Integer> getIndividual(int id) {
        return m_population.get(id);
    }

    public void setIndividual(int id, LinkedList<Integer> value) {
        m_population.set(id, value);
    }

    public Memeplex getMemeplex(int id) {
        return m_memeplexs.get(id);
    }

    public Population(Random rnd, int chromosomeLength, boolean isSetRandomValue) {
        this.rnd = rnd;

        m_chromosomeLength = chromosomeLength;
        initialise(isSetRandomValue);
    }

    private void initialise(boolean isSetRandomValue) {
        m_population = iniPopulation(isSetRandomValue);
        m_memeplexs = iniMemeplexs(rnd, isSetRandomValue);
    }

    private LinkedList<LinkedList<Integer>> iniPopulation(boolean isSetRandomValue) {
        LinkedList<LinkedList<Integer>> population = new LinkedList<>();
        if (isSetRandomValue) {
            for (int i = 0; i < POPULATION_SIZE; i++) {
                LinkedList<Integer> individual = new LinkedList<>();
                for (int j = 0; j < m_chromosomeLength; j++) {
                    individual.add((rnd.nextDouble() < 0.5) ? 0 : 1);
                }
                population.add(individual);
            }
        } else {
            for (int i = 0; i < POPULATION_SIZE; i++) {
                LinkedList<Integer> individual = new LinkedList<>();
                for (int j = 0; j < m_chromosomeLength; j++) {
                    individual.add(0);
                }
                population.add(individual);
            }
        }
        return population;

    }

    private LinkedList<Memeplex> iniMemeplexs(Random rnd, boolean isSetRandomValue) {
        LinkedList<Memeplex> memeplexes = new LinkedList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            memeplexes.add(new Memeplex(rnd, isSetRandomValue));
        }
        return memeplexes;
    }

    public void reInitialise(boolean isSetRandomValue) {
        if (isSetRandomValue) {
            for (int i = 0; i < POPULATION_SIZE; i++) {
                for (int j = 0; j < m_chromosomeLength; j++) {
                    m_population.get(i).set(j, (rnd.nextDouble() < 0.5) ? 0 : 1);
                }
                m_memeplexs.get(i).iniMemeplexOptions(true);
            }
        } else {
            for (int i = 0; i < POPULATION_SIZE; i++) {
                for (int j = 0; j < m_chromosomeLength; j++) {
                    m_population.get(i).set(j, 0);
                }
                m_memeplexs.get(i).iniMemeplexOptions(false);
            }
        }
    }
}
