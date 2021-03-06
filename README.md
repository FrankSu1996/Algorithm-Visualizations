# Algorithm Visualizations
This repository contains various applications that give a visual representation of how certain algorithms work.

* [Genetic Algorithm](https://towardsdatascience.com/introduction-to-genetic-algorithms-including-example-code-e396e98d8bf3)- a search heuristic algorithm used to solve ["The travelling salesman problem"](https://en.wikipedia.org/wiki/Travelling_salesman_problem)

## Genetic Algorithm
The genetic algorithm is a heuristic algorithm inspired by the process of natural selection. The 
idea is that by initializing a population that represents potential solutions to a given problem, then
by randomly creating offspring and calculating their fitness as a value proportionate to the optimal solution,
we can select those who are most "fit" to represent the next generation. By repeating the process through 
many iterative generations, we the genetic algorithm will eventually come up with the optimal solution. 

[Read More about Genetic Algorithm](https://en.wikipedia.org/wiki/Genetic_algorithm)

### How to use this program
in out/artifacts/, there is an executable jar file that will open the GUI for the program. Upon downloading and executing the JAR, 
the main menu will appear as the following:



![alt text](https://github.com/FrankSu1996/Machine-Learning/blob/master/TSP_GeneticAlgorithm/src/images/mainMenu.png)


To start a simulation, you must enter a positive integer value for all of the parameters:

__Number of cities:__ The number of initial nodes that must be connected by the algorithm.

__Population Size:__ The size of the population in each generation used by the algorithm.

__Mutation Severity:__ How much of the dna is changed for each mutation that occurs in the algorithm.

Once these parameters are set, merely click the start button to be taken to the world page. Once at the world page, simply click the
options menu -> start to start the simulation. There is also an option to reset the simulation with the same parameters, or to go back
to the main menu and reset the parameters for a new simulation. Enjoy!!

![alt text](https://github.com/FrankSu1996/Machine-Learning/blob/master/TSP_GeneticAlgorithm/src/images/world.png)
