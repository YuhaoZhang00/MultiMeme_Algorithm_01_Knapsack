## My Multimeme Memetic Algorithm (MMA) for solving 0-1 Knapsack Problem

### COMP2001 AIM Coursework (2122SPR @ UoN, Module Convenor: [Ender ÖZCAN](http://www.cs.nott.ac.uk/~pszeo/)) by Yuhao Zhang

#### How to run the algorithm

1. Modify configurations in `\src\com\yuhao\config\Constant.java`
    - put the input instance file in correct place (e.g. `\resource\test_instance.txt`) and configure the path to the
      input file and output file
    - configure number of trials, random seeds to use, etc.
    - do parameter tuning (e.g. innovation rate, crossover operator to use, etc.)
    - **detailed instruction of parameters can be found in `Constant` class**
2. Build the whole project
3. Run `main` method of `CourseworkRunner` in `\src\com\yuhao\CourseworkRunner.java`
4. The best solution and its objective value per trial can be found in the standard output; while the best/worst
   objective value of the first `MAX_NUMBER_OF_ENTRIES_TO_WRITE` loops per trial can be found in the output files

#### Additional notes

- IDE used for this project: **IntelliJ IDEA** _or_ **Eclipse**
- Inspired by : Ender Ozcan et al., _A Self-adaptive Multimeme Memetic Algorithm Co-evolving Utility Scores to Control
  Genetic Operators and Their Parameter Settings_, pp. 11-13 (
  Link: http://www.cs.nott.ac.uk/~pszeo/docs/publications/multimemeAlgorithmChesc.pdf)
