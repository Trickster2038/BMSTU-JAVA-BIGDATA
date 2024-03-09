package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("=== Custom collection work example ===\n\n");

        MyCollection myCollection = new MyCollection();
        myCollection.add(1);
        myCollection.add(2);
        myCollection.add(10);
        myCollection.add(11);

        System.out.printf("Collection: %s \n", myCollection);
        System.out.printf("Closest to 8: %d \n", myCollection.findClosest(8));
    }
}

class MyCollection extends HashSet<Integer> {
    public Integer findClosest(Integer r){
        return this.stream().min(Comparator.comparingInt(a -> Math.abs(r - a))).orElseThrow();
    }
}