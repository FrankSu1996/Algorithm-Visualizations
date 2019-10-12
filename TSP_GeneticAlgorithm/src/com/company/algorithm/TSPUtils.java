package com.company.algorithm;

import com.company.GUI.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TSPUtils {

    private final static Random R = new Random(10000);

    public static final Gene[] CITIES = generateData(50);

    private TSPUtils() {
        throw new RuntimeException("Cannot instantiate this class!");
    }

    /**
     * Method to generate random cities within the width and height of cartesian plane (world)
     * @param numberDataPoints the number of cities to be generated
     * @return
     */
    private static Gene[] generateData(final int numberDataPoints) {
        final Gene[] data = new Gene[numberDataPoints];
        for(int i = 0; i < numberDataPoints; i++) {
            data[i] = new Gene(TSPUtils.randomIndex(World.WIDTH), TSPUtils.randomIndex(World.HEIGHT));
        }
        return data;
    }

    /**
     * Return random index between 0 and limit -1
     * @param limit the max limit returned
     * @return a random index
     */
    static int randomIndex(final int limit) {
        return R.nextInt(limit);
    }

    /**
     * Method to split a list into two halves
     * @param list input list
     * @param <T>
     * @return an array of 2 lists representing first and second half
     */
    static<T> List<T>[] split(final List<T> list) {

        final List<T> first = new ArrayList<>();
        final List<T> second = new ArrayList<>();
        final int size = list.size();

        //loop through list, add first half to first list, second
        //half to second list
        IntStream.range(0, size).forEach(i -> {
            if(i < (size+1)/2) {
                first.add(list.get(i));
            }
            else {
                second.add(list.get(i));
            }
        });
        return (List<T>[]) new List[] {first, second};
    }
}
