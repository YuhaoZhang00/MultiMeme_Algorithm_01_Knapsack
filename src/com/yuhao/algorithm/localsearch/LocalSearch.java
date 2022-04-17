package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.Random;

public abstract class LocalSearch {
    abstract public boolean applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                             MultimemeComponent algorithm, int idChild);
}
