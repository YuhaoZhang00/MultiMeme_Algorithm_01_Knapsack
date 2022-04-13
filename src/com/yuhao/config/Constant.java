package com.yuhao.config;

public class Constant {
    public static String FILE_TO_READ = "resource/initialTestInstances/test1_4_20.txt";
    //	public static String FILE_TO_READ = "resource/initialTestInstances/test2_10_269.txt";
    //	public static String FILE_TO_READ = "resource/initialTestInstances/test3_20_879.txt";
    //	public static String FILE_TO_READ = "resource/hiddenInstances/hidden1_5_80.txt";
    //	public static String FILE_TO_READ = "resource/hiddenInstances/hidden2_7_50.txt";
    //	public static String FILE_TO_READ = "resource/hiddenInstances/hidden3_10_60.txt";
    //	public static String FILE_TO_READ = "resource/hiddenInstances/hidden4_15_375.txt";
    //	public static String FILE_TO_READ = "resource/hiddenInstances/hidden5_23_10000.txt";
    //	public static String FILE_TO_READ = "resource/otherInstances/test.txt";

    public static String FILE_TO_WRITE = "output/instanceName_n_W_trialID_output.txt"; // TODO: change

    public static boolean IS_USE_SEED = true;
    public static long SEED = 123456;

    public static int POPULATION_SIZE = 2; // suggested to be a even number - easy for crossover // TODO: this ok?
    public static int TOURNAMENT_SIZE = 2;

    /**
     * Crossover options to use
     * <p> 0 : 1-PTX (One Point Crossover)
     * <br> 1 : 2-PTX (Two Point Crossover)
     * <br> 2 : UX (Uniform Crossover)
     */
    public static int[] CROSSOVER_OPTIONS_TO_USE = {0, 1, 2};
    // all of the options should set more than 2 options // TODO: this ok?
    /**
     * Mutation OR Ruin-Recreate options to use
     * <p> 0 : Bit flip [Mutation]
     * <br> 1 : Bit flip [Mutation] // TODO: subject to change
     * <br> 2 : Bit flip [Mutation] // TODO: subject to change
     */
    public static int[] MUTATION_OPTIONS_TO_USE = {0, 1, 2};
    /**
     * Intensity of Mutation options to use
     * <p> 0 : mutation or ruin_recreate for 1 times
     * <br> 1 : mutation or ruin_recreate for 2 times
     * <br> 2 : mutation or ruin_recreate for 3 times
     * <br> 3 : mutation or ruin_recreate for 4 times
     * <br> 4 : mutation or ruin_recreate for 5 times
     * <br> 5 : mutation or ruin_recreate for 6 times
     */
    public static int[] INTENSITY_OF_MUTATION_OPTIONS_TO_USE = {0, 1, 2, 3, 4, 5};
    /**
     * Local search options to use
     * <p> 0 : RMHC_OI (Random Mutation Hill Climbing accepting Only Improving moves)
     * <br> 1 : RMHC_IE (Random Mutation Hill Climbing accepting Improving or Equal moves)
     * <br> 2 : DBHC_OI (Davis' Bit Hill Climbing accepting Only Improving moves)
     * <br> 3 : DBHC_IE (Davis' Bit Hill Climbing accepting Improving or Equal moves)
     * <br> 4 : SAHC_OI (Steepest Ascent / Best Improvement Hill Climbing accepting Only Improving moves)
     * <br> 5 : SAHC_IE (Steepest Ascent / Best Improvement Hill Climbing accepting Improving or Equal moves)
     * <br> 6 : GAHC_OI (Gradient Ascent / Next Ascent / First Improvement Hill Climbing accepting Only Improving
     * moves)
     * <br> 7 : GAHC_IE (Gradient Ascent / Next Ascent / First Improvement Hill Climbing accepting Improving or Equal
     * moves)
     * <br> 8 : GAHC_Advanced_OI (an advanced First Improvement Hill Climbing accepting Only Improving moves)
     * <br> 9 : GAHC_Advanced_IE (an advanced First Improvement Hill Climbing accepting Improving or Equal moves)
     * <br> 10 : LeastImprovement_OI (Least Improvement Hill Climbing accepting Only Improving moves)
     * <br> 11 : LeastImprovement_IE (Least Improvement Hill Climbing accepting Improving or Equal moves)
     */
    public static int[] LOCAL_SEARCH_OPTIONS_TO_USE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    /**
     * Depth of Search options to use
     * <p> 0 : local search for 1 times
     * <br> 1 : local search for 2 times
     * <br> 2 : local search for 3 times
     * <br> 3 : local search for 4 times
     * <br> 4 : local search for 5 times
     * <br> 5 : local search for 6 times
     */
    // TODO: NOTE: see cw sheet for hint in local search algorithms
    public static int[] DEPTH_OF_SEARCH_OPTIONS_TO_USE = {0, 1, 2, 3, 4, 5};

    public static double INNOVATION_RATE = 0.2;
}
