package com.company.algorithm;

import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Class representing abstraction of a gene in genetic algorithm. For TSP problem, gene will represent
 * a city/node in the final path.
 */
public class Gene {

    // x-y coordinates of city in cartesian plane
    private final int x;
    private final int y;

    /**
     * Constructor that initializes city with x-y coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     */
    Gene(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    //represents a city as (x, y) coordinate
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Method to return the distance between 2 cities
     * @param other another city
     * @return the distance between two cities
     */
    double distance(final Gene other) {
        return sqrt(pow(getX() - other.getX(), 2) + pow(getY() - other.getY(), 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gene gene = (Gene) o;
        return x == gene.x &&
                y == gene.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
