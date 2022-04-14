package com.yuhao.config;

public class Constant {
    /**
     * path of test instance file to read from
     * <p> [Instruction] a string of the relative path / absolute path of the instance file
     * <br> [Threat] invalid path will result in {@code FileNotFoundException}
     */
    public static String FILE_TO_READ = "resource/otherInstances/2614-f.txt";
//    public static String FILE_TO_READ = "resource/initialTestInstances/test1_4_20.txt";
//    public static String FILE_TO_READ = "resource/initialTestInstances/test2_10_269.txt";
//    public static String FILE_TO_READ = "resource/initialTestInstances/test3_20_879.txt";
//    public static String FILE_TO_READ = "resource/hiddenInstances/hidden1_5_80.txt";
//    public static String FILE_TO_READ = "resource/hiddenInstances/hidden2_7_50.txt";
//    public static String FILE_TO_READ = "resource/hiddenInstances/hidden3_10_60.txt";
//    public static String FILE_TO_READ = "resource/hiddenInstances/hidden4_15_375.txt";
//    public static String FILE_TO_READ = "resource/hiddenInstances/hidden5_23_10000.txt";

    /**
     * path of the folder for generated objective value files for each trial
     * <p> [Instruction] a string of the relative path / absolute path of the intended folder
     * <br> [Threat] invalid path will result in {@code FileNotFoundException}; Please note that the files in the
     * folder with the same name will be overwritten
     */
    public static String FOLDER_TO_WRITE = "output/instanceName_n_W_trialID_output.txt"; // TODO: change

    /**
     * whether to use a certain seed to run the trials
     * <p> [Instruction] 'true' means use a certain seed, 'false' means use a random seed
     */
    public static boolean IS_USE_SEED = true;
    /**
     * the certain seed to run the trials, only takes effect if {@code IS_USE_SEED == true}
     * <p> [Instruction] a long number
     */
    public static long SEED = 123456;

    /**
     * number of trials to perform
     * <p> [Instruction] suggested value : 5
     */
    public static int NUMBER_OF_TRIALS = 5;
    /**
     * number of loops to perform per trial
     * <p> [Instruction] suggested value : 300
     */
    public static int NUMBER_OF_LOOPS = 300;

    /**
     * the population size
     * <p> [Instruction] an integer indicating the number of individuals in a generation
     * <br> [Threat] please make it an EVEN NUMBER, otherwise there will be {@code IndexOutOfBoundsException} when
     * applying crossover
     */
    public static int POPULATION_SIZE = 10;
    /**
     * the tournament size when applying tournament selection on parents
     * <p> [Instruction] an integer indicating the tour size, should be less than {@code POPULATION_SIZE}
     */
    public static int TOURNAMENT_SIZE = 3;
    /**
     * the innovation rate when applying mutation of memeplex
     * <p> [Instruction] an double number between 0 and 1
     */
    public static double INNOVATION_RATE = 0.2;

    /**
     * Crossover options to use
     * <p> 0 : 1-PTX (One Point Crossover)
     * <br> 1 : 2-PTX (Two Point Crossover)
     * <br> 2 : UX (Uniform Crossover)
     * <p> [Instruction] please put the index of crossover options you want to apply in the multimeme memetic algorithm
     * in the brackets below
     * <br> [Threat] please put more than 2 options to enable a mutation of this meme; putting only 1 option in the
     * bracket means the way of crossover is fixed throughout the run
     */
    public static int[] CROSSOVER_OPTIONS_TO_USE = {0, 1, 2};
    /**
     * Mutation OR Ruin-Recreate options to use
     * <p> 0 : Bit flip [Mutation]
     * <br> 1 : Bit flip [Mutation] // TODO: subject to change
     * <br> 2 : Bit flip [Mutation] // TODO: subject to change
     * <p> [Instruction] please put the index of mutation or ruin-recreate options you want to apply in the multimeme
     * memetic algorithm in the brackets below
     * <br> [Threat] please put more than 2 options to enable a mutation of this meme; putting only 1 option in the
     * bracket means the way of mutation or ruin-recreate is fixed throughout the run
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
     * <p> [Instruction] please put the index of IoM options you want to apply in the multimeme memetic algorithm in
     * the brackets below
     * <br> [Threat] please put more than 2 options to enable a mutation of this meme; putting only 1 option in the
     * bracket means the IoM is fixed throughout the run
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
     * <p> [Instruction] please put the index of local search options you want to apply in the multimeme memetic
     * algorithm in the brackets below
     * <br> [Threat] please put more than 2 options to enable a mutation of this meme; putting only 1 option in the
     * bracket means the way of local search is fixed throughout the run
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
     * <p> [Instruction] please put the index of DoS options you want to apply in the multimeme memetic algorithm in
     * the brackets below
     * <br> [Threat] please put more than 2 options to enable a mutation of this meme; putting only 1 option in the
     * bracket means the DoS is fixed throughout the run
     */
    public static int[] DEPTH_OF_SEARCH_OPTIONS_TO_USE = {0, 1, 2, 3, 4, 5};
}
