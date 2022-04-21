package com.yuhao.config;

public class Constant {
    /**
     * path of test instance file to read from
     * <p> [Instruction] a string of the relative path / absolute path of the instance file
     * <br> [Threat] invalid path will result in {@code FileNotFoundException}
     */
    public static String FILE_TO_READ = "resource/otherInstances/2461.txt";
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
     * <br> [Threat] invalid path (e.g. folder not exist) will result in {@code IOException}
     */
    public static String FOLDER_TO_WRITE = "resource";
    /**
     * instance name for name of the output file; if there's a file with the same name in the given folder and path the
     * file will be overwritten, otherwise a file with corresponding name will be created
     * <p> [Instruction] a string of instance name, or {@code ""} to use the name of the name of the {@code
     * FILE_TO_READ}
     * <br> [Threat] Please note that the files in the folder with the same name will be overwritten
     */
    public static String INSTANCE_NAME = "";
    /**
     * max number of entries (i.e. pairs of best/worst objective values) to record in the output file
     * <p> [Instruction] an integer number, should be less than or equal to {@code NUMBER_OF_LOOPS}
     * <br> [Threat] if the number is greater than {@code NUMBER_OF_LOOPS}, there will be empty files created
     */
    public static int MAX_NUMBER_OF_ENTRIES_TO_WRITE = 100;

    /**
     * whether to use a certain seed to run the trials
     * <p> [Instruction] {@code true} means use a certain seed, {@code false} means use a random seed
     */
    public static boolean IS_USE_SEED = true;
    /**
     * the certain seed to run the trials, only takes effect if {@code IS_USE_SEED == true}
     * <p> [Instruction] a long number
     */
    public static long SEED = 1000;

    /**
     * number of trials to perform
     * <p> [Instruction] an integer number
     */
    public static int NUMBER_OF_TRIALS = 5;
    /**
     * number of loops to perform per trial
     * <p> [Instruction] an integer number
     */
    public static int NUMBER_OF_LOOPS = 150;

    /**
     * the population size
     * <p> [Instruction] an integer indicating the number of individuals in a generation
     * <br> [Threat] please make it an EVEN NUMBER, otherwise there will be {@code IndexOutOfBoundsException} when
     * applying crossover
     */
    public static int POPULATION_SIZE = 16;
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
     * <br> 3 : Intersect and Same ( c1 <- p1 AND p2 ; c2 <- p1 XOR p2 )
     * <br> 4 : Intersect and Both (reverse thinking of 'Intersect and Same') ( c1 <- p1 OR p2 ; c2 <- p1 XOR p2 )
     * <br> 5 : Same and Both ( c1 <- p1 AND p2 ; c2 <- p1 OR p2 )
     * <p> [Instruction] please put the index of crossover options you want to apply in the multimeme memetic algorithm
     * in the brackets below
     * <br> [Threat] please put more than 2 options to enable a mutation of this meme; putting only 1 option in the
     * bracket means the way of crossover is fixed throughout the run
     */
    public static int[] CROSSOVER_OPTIONS_TO_USE = {0, 1, 2, 3, 4, 5};
    /**
     * Mutation OR Ruin-Recreate options to use
     * <p> -- [Mutation] general approach --
     * <br> 0 : Bit flip [Mutation]
     * <br> -- [Mutation] profit --
     * <br> 1 : Replaces the highest profit item in the knapsack with the lowest profit item out of knapsack [Mutation]
     * <br> 2 : Replaces the highest profit item in the knapsack with the highest profit item out of knapsack
     * [Mutation]
     * <br> 3 : Replaces the lowest profit item in the knapsack with the lowest profit item out of knapsack [Mutation]
     * <br> 4 : Replaces the lowest profit item in the knapsack with the highest profit item out of knapsack [Mutation]
     * <br> 5 : Replaces a random item in the knapsack with the highest profit item out of knapsack [Mutation]
     * <br> 6 : Replaces a random item in the knapsack with the lowest profit item out of knapsack [Mutation]
     * <br> -- [Mutation] weight --
     * <br> 7 : Replaces the highest weight item in the knapsack with the lowest weight item out of knapsack [Mutation]
     * <br> 8 : Replaces the highest weight item in the knapsack with the highest weight item out of knapsack
     * [Mutation]
     * <br> 9 : Replaces the lowest weight item in the knapsack with the lowest weight item out of knapsack [Mutation]
     * <br> 10 : Replaces the lowest weight item in the knapsack with the highest weight item out of knapsack
     * [Mutation]
     * <br> 11 : Replaces a random item in the knapsack with the highest weight item out of knapsack [Mutation]
     * <br> 12 : Replaces a random item in the knapsack with the lowest weight item out of knapsack [Mutation]
     * <br> -- [Mutation] value per weight --
     * <br> 13 : Replaces the lowest 'profit per weight' item in the knapsack with the highest 'profit per weight' item
     * out of knapsack [Mutation]
     * <br> 14 : Replaces a random item in the knapsack with the highest 'profit per weight' item out of knapsack
     * [Mutation]
     * <br> -- [Ruin-Recreate] --
     * <br> 15 : Random Ruin and Random Recreate [Ruin-Recreate]
     * <br> 16 : Random Ruin and Recreate by including highest profit items [Ruin-Recreate]
     * <br> 17 : Random Ruin and Recreate by including lowest weight items [Ruin-Recreate]
     * <br> 18 : Random Ruin and Recreate by including highest 'profit per weight' items [Ruin-Recreate]
     * <p> [Instruction] please put the index of mutation or ruin-recreate options you want to apply in the multimeme
     * memetic algorithm in the brackets below
     * <br> [Threat] please put more than 2 options to enable a mutation of this meme; putting only 1 option in the
     * bracket means the way of mutation or ruin-recreate is fixed throughout the run
     */
    public static int[] MUTATION_OPTIONS_TO_USE = {0, 4, 5, 7, 12, 13, 14, 15, 16, 17, 18};
    /**
     * the maximum percentage of individuals a ruin-recreate method can ruin in a population, to avoid too much change
     * of information in a population
     * <p> [Instruction] an double number between 0 and 1
     */
    public static double MAXIMUM_RR_PERCENTAGE = 0.5;
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
     * <br> 8 : GAHC_Advanced_OI (a First Improvement Hill Climbing accepting Only Improving moves through a loop)
     * <br> 9 : GAHC_Advanced_IE (a First Improvement Hill Climbing accepting Improving or Equal moves through a loop)
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
