package com.yuhao.algorithm.mutation_or_ruinrecreate;

import com.yuhao.algorithm.MultimemeComponent;
import com.yuhao.data.Population;
import com.yuhao.data.Problem;

import java.util.Random;

public abstract class MutationRuinRecreate {
    abstract public void applyMutationOrRuinRecreate(Random rnd, Problem problem, Population populationChildren,
                                                     int idChild);
}

// TODO: comment for subclass
// THINK OF: highest weight / lowest weight / highest value per weight