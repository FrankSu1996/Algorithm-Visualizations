package com.company.algorithm;

import com.company.GUI.World;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class to represent a population of chromosomes for a given generation
 */
public class Population {

    private List<Chromosome> population;
    private final int initialSize;

    public Population(final Gene[] points, final int initialSize) {
        this.population = init(points, initialSize);
        this.initialSize = initialSize;
    }

    /**
     * Returns "best" fitness chromosome in population.
     * @return "best" fitness chromosome in population.
     */
    public Chromosome getAlpha() {
        return this.population.get(0);
    }

    /**
     * Method to create random population of a given size
     * @param points an array of cities
     * @param initialSize the size of the population
     * @return a list of chromosomes representing the population
     */
    private List<Chromosome> init(final Gene[] points, final int initialSize) {
        final List<Chromosome> population = new ArrayList<>();

        //use create method to generate population
        for(int i = 0; i < initialSize; i++) {
            final Chromosome chromosome = Chromosome.create(points);
            population.add(chromosome);
        }
        return population;
    }

    /**
     * perform update on population to represent 1 iteration in genetic algorithm.
     * Must perform chromosome crossover, mutation, create offspring, and select most fit individuals
     */
    public void update() {
        crossOver();
        mutation(10);
        spawn(1000);
        selection();
    }

    /**
     * Method to perform crossover within population. Updates population parameter with all
     * offspring PLUS parent population.
     */
    private void crossOver() {
        final List<Chromosome> newPopulation = new ArrayList<>();

        //for each chromosome in population, crossover with partner and add
        //offspring to new population
        for(final Chromosome chromosome : this.population) {
            final Chromosome partner = getCrossOverPartner(chromosome);
            newPopulation.addAll(Arrays.asList(chromosome.crossOver(partner)));
        }
        //add previous population members to new population
        this.population.addAll(newPopulation);
    }

    /**
     * Method to get random partner for mating
     * @param chromosome input chromosome for mating
     * @return a randomly selected partner
     */
    private Chromosome getCrossOverPartner(Chromosome chromosome) {
        //get random partner for mating, make sure isn't same as initial chromosome
        Chromosome partner = this.population.get(TSPUtils.randomIndex(this.population.size()));
        while(chromosome == partner) {
            partner = this.population.get(TSPUtils.randomIndex(this.population.size()));
        }
        return partner;
    }

    /**
     * Method to perform mutation on percentage of population. Updates population parameter with all
     * mutated chromosomes
     */
    private void mutation(int percent) {
        final List<Chromosome> newPopulation = new ArrayList<>();
        for (int i = 0; i < this.population.size()/percent; i++) {
            Chromosome mutation = this.population.get(TSPUtils.randomIndex(this.population.size())).mutate();
            newPopulation.add(mutation);
        }
        this.population.addAll(newPopulation);
    }

    /**
     * Method to create new individuals in population given a size parameter. Adds new members to population
     */
    private void spawn(int spawnSize) {
        IntStream.range(0, spawnSize).forEach(e -> this.population.add(Chromosome.create(TSPUtils.CITIES)));
    }

    /**
     * Method to select
     */
    private void selection() {
        // first sort population based on fitness (total distance for possible solution)
        this.population.sort(Comparator.comparingDouble(Chromosome::calculateDistance));

        // keep "fittest" individuals in population
        this.population = this.population.stream().limit(this.initialSize).collect(Collectors.toList());
    }
}
