package com.yuhao.algorithm.localsearch;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.Random;

public abstract class LocalSearch {
    abstract public void applyLocalSearch(Random rnd, Problem problem, Population populationChildren,
                                          MultimemeComponent algorithm, int idChild);
}

// TODO: for DB/GA/SA/LI HC If there is no improvement in a pass, the algorithm should be terminated.
// TODO: for RMHC number of bit flips can be set i.e. RMHC_1  RMHC_2