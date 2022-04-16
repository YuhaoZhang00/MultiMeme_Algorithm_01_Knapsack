package com.yuhao.algorithm.crossover;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.Random;

public abstract class Crossover {
    abstract public void applyCrossover(Random rnd, Problem problem, Population populationParent,
                                        Population populationChildren, int idParent1, int idParent2, int idChild1,
                                        int idChild2);
}
