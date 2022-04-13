package com.yuhao.data;

import java.util.Random;

import static com.yuhao.config.Constant.*;

/**
 * The memeplex used in this Multimeme Memetic Algorithm can be denoted as <code>[ C_c {M or R}_m I_i L_l D_d ]</code>
 * <p> C : crossover
 * <br> M : mutation , R : ruin-recreate
 * <br> I : IoM
 * <br> L : local search
 * <br> D : DoS
 * <br> Inspired by : Ender Ozcan et al., A Self-adaptive Multimeme Memetic Algorithm Co-evolving Utility Scores to Control
 * Genetic Operators and Their Parameter Settings, pp. 11-12 (Link :
 * http://www.cs.nott.ac.uk/~pszeo/docs/publications/multimemeAlgorithmChesc.pdf)
 */
public class Memeplex {
    private int m_crossoverOption;
    private int m_mutationOption;
    private int m_IoMOption;
    private int m_localSearchOption;
    private int m_DoSOption;

    private Random m_rnd;

    public int getCrossoverOption() {
        return m_crossoverOption;
    }

    public void setCrossoverOption(int option) {
        m_crossoverOption = option;
    }

    public void setAnotherRandomCrossoverOption() {
        int option;
        do {
            option = m_rnd.nextInt(CROSSOVER_OPTIONS_TO_USE.length);
        } while (CROSSOVER_OPTIONS_TO_USE[option] == m_crossoverOption);
        m_crossoverOption = CROSSOVER_OPTIONS_TO_USE[option];
    }

    public int getMutationOption() {
        return m_mutationOption;
    }

    public void setMutationOption(int option) {
        m_mutationOption = option;
    }

    public void setAnotherRandomMutationOption() {
        int option;
        do {
            option = m_rnd.nextInt(MUTATION_OPTIONS_TO_USE.length);
        } while (MUTATION_OPTIONS_TO_USE[option] == m_mutationOption);
        m_mutationOption = MUTATION_OPTIONS_TO_USE[option];
    }

    public int getIoMOption() {
        return m_IoMOption;
    }

    public void setIoMOption(int option) {
        m_IoMOption = option;
    }

    public void setAnotherRandomIoMOption() {
        int option;
        do {
            option = m_rnd.nextInt(INTENSITY_OF_MUTATION_OPTIONS_TO_USE.length);
        } while (INTENSITY_OF_MUTATION_OPTIONS_TO_USE[option] == m_IoMOption);
        m_IoMOption = INTENSITY_OF_MUTATION_OPTIONS_TO_USE[option];
    }

    public int getLocalSearchOption() {
        return m_localSearchOption;
    }

    public void setLocalSearchOption(int option) {
        m_localSearchOption = option;
    }

    public void setAnotherRandomLocalSearchOption() {
        int option;
        do {
            option = m_rnd.nextInt(LOCAL_SEARCH_OPTIONS_TO_USE.length);
        } while (LOCAL_SEARCH_OPTIONS_TO_USE[option] == m_localSearchOption);
        m_localSearchOption = LOCAL_SEARCH_OPTIONS_TO_USE[option];
    }

    public int getDoSOption() {
        return m_DoSOption;
    }

    public void setDoSOption(int option) {
        m_DoSOption = option;
    }

    public void setAnotherRandomDoSOption() {
        int option;
        do {
            option = m_rnd.nextInt(DEPTH_OF_SEARCH_OPTIONS_TO_USE.length);
        } while (DEPTH_OF_SEARCH_OPTIONS_TO_USE[option] == m_DoSOption);
        m_DoSOption = DEPTH_OF_SEARCH_OPTIONS_TO_USE[option];
    }

    public Memeplex(Random rnd, boolean isSetRandomValue) {
        m_rnd = rnd;

        if (isSetRandomValue) {
            m_crossoverOption = CROSSOVER_OPTIONS_TO_USE[m_rnd.nextInt(CROSSOVER_OPTIONS_TO_USE.length)];
            m_mutationOption = MUTATION_OPTIONS_TO_USE[m_rnd.nextInt(MUTATION_OPTIONS_TO_USE.length)];
            m_IoMOption =
                    INTENSITY_OF_MUTATION_OPTIONS_TO_USE[m_rnd.nextInt(INTENSITY_OF_MUTATION_OPTIONS_TO_USE.length)];
            m_localSearchOption = LOCAL_SEARCH_OPTIONS_TO_USE[m_rnd.nextInt(LOCAL_SEARCH_OPTIONS_TO_USE.length)];
            m_DoSOption = DEPTH_OF_SEARCH_OPTIONS_TO_USE[m_rnd.nextInt(DEPTH_OF_SEARCH_OPTIONS_TO_USE.length)];
        }
    }
}
