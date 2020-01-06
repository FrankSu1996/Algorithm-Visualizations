# Algorithm Visualizations
This repository contains various applications that give a visual representation of how certain algorithms work.

Currently includes:
* [Genetic Algorithm](https://towardsdatascience.com/introduction-to-genetic-algorithms-including-example-code-e396e98d8bf3)- a search heuristic algorithm used to solve ["The travelling salesman problem"](https://en.wikipedia.org/wiki/Travelling_salesman_problem)
* Graph Processing Algorithms: Includes various algorithms for finding paths between start and end nodes in a graph. Currently includes:
1. [Djikstra's Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) - a weighted graph traversal algorithm used to find the shortest path between nodes
2. [Depth First Search](https://en.wikipedia.org/wiki/Depth-first_search) - non-weighted graph traversal algorithm that does not gauruntee the shortest path
3. [Breadth First Search](https://en.wikipedia.org/wiki/Breadth-first_search) - a non-weighted graph traversal algorithm that gauruntees the shortest path

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

## Graph Processing Algorithms
To run the application, please clone the repository. Then, inside the GraphAlgorithms directory, first run **npm install** to install dependencies, then **npm start** to view the application running locally in your browser.
