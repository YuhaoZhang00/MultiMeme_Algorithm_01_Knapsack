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
     * list of total profit per individual
     */
    private LinkedList<Double> m_totalProfit;

    /**
     * list of total weight per individual
     */
    private LinkedList<Double> m_totalWeight;

    private Random rnd;
    private Problem m_problem;

    public LinkedList<Integer> getIndividual(int id) {
        return m_population.get(id);
    }

    public void setIndividual(int id, LinkedList<Integer> value) {
        m_population.set(id, value);
    }

    public Memeplex getMemeplex(int id) {
        return m_memeplexs.get(id);
    }

    public Double getIndividualTotalProfit(int id) {
        return m_totalProfit.get(id);
    }

    public Double getIndividualTotalWeight(int id) {
        return m_totalWeight.get(id);
    }

    public void setIndividualTotalProfit(int id, double value) {
        m_totalProfit.set(id, value);
    }

    public void setIndividualTotalWeight(int id, double value) {
        m_totalWeight.set(id, value);
    }

    /**
     * Method for delta evaluation - include an item
     */
    public void changeIndividualInfoIncludeItem(int individualId, int index) {
        m_totalProfit.set(individualId, (m_totalProfit.get(individualId) + m_problem.getProfit(index)));
        m_totalWeight.set(individualId, (m_totalWeight.get(individualId) + m_problem.getWeight(index)));
    }

    /**
     * Method for delta evaluation - exclude an item
     */
    public void changeIndividualInfoExcludeItem(int individualId, int index) {
        m_totalProfit.set(individualId, (m_totalProfit.get(individualId) - m_problem.getProfit(index)));
        m_totalWeight.set(individualId, (m_totalWeight.get(individualId) - m_problem.getWeight(index)));
    }

    /**
     * Method for delta evaluation - recalculate
     */
    public void changeIndividualInfoRecalculate(int individualId) {
        LinkedList<Integer> individual = m_population.get(individualId);

        double totalProfit = 0;
        double totalWeight = 0;
        for (int i = 0; i < m_problem.getNumOfItems(); i++) {
            if (individual.get(i) == 1) {
                totalProfit += m_problem.getProfit(i);
                totalWeight += m_problem.getWeight(i);
            }
        }
        m_totalProfit.set(individualId, totalProfit);
        m_totalWeight.set(individualId, totalWeight);
    }

    public Population(Random rnd, Problem problem, boolean isSetRandomValue) {
        this.rnd = rnd;

        m_problem = problem;
        initialise(isSetRandomValue);
    }

    private void initialise(boolean isSetRandomValue) {
        iniPopulation(isSetRandomValue);
        m_memeplexs = iniMemeplexs(rnd, isSetRandomValue);
    }

    private void iniPopulation(boolean isSetRandomValue) {
        m_population = new LinkedList<>();
        m_totalProfit = new LinkedList<>();
        m_totalWeight = new LinkedList<>();
        if (isSetRandomValue) {
            for (int i = 0; i < POPULATION_SIZE; i++) {
                LinkedList<Integer> individual = new LinkedList<>();
                double totalProfit = 0;
                double totalWeight = 0;
                for (int j = 0; j < m_problem.getNumOfItems(); j++) {
                    if (rnd.nextDouble() < 0.5) {
                        individual.add(0);
                    } else {
                        individual.add(1);
                        totalProfit += m_problem.getProfit(j);
                        totalWeight += m_problem.getWeight(j);
                    }
                }
                m_population.add(individual);
                m_totalProfit.add(totalProfit);
                m_totalWeight.add(totalWeight);
            }
        } else {
            for (int i = 0; i < POPULATION_SIZE; i++) {
                LinkedList<Integer> individual = new LinkedList<>();
                for (int j = 0; j < m_problem.getNumOfItems(); j++) {
                    individual.add(0);
                }
                m_population.add(individual);
                m_totalProfit.add(0d);
                m_totalWeight.add(0d);
            }
        }

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
                for (int j = 0; j < m_problem.getNumOfItems(); j++) {
                    m_population.get(i).set(j, (rnd.nextDouble() < 0.5) ? 0 : 1);
                }
                changeIndividualInfoRecalculate(i);
                m_memeplexs.get(i).iniMemeplexOptions(true);
            }

        } else {
            for (int i = 0; i < POPULATION_SIZE; i++) {
                for (int j = 0; j < m_problem.getNumOfItems(); j++) {
                    m_population.get(i).set(j, 0);
                }
                changeIndividualInfoRecalculate(i);
                m_memeplexs.get(i).iniMemeplexOptions(false);
            }
        }
    }
}
