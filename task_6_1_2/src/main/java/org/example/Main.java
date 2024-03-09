package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("=== I, U, R, least-square work example ===\n\n");

        List<Double> i =  new ArrayList<>(List.of(1.0, 2.1, 2.9, 4.05, 4.98, 6.03));
        List<Double> u =  new ArrayList<>(List.of(3.03, 6.1, 8.9, 12.05, 14.98, 18.03));

        System.out.printf("U: %s \n", u);
        System.out.printf("I: %s \n", i);

        Double r = calculateSlope(i, u);

        System.out.printf("R: %.5f \n", r);

        System.out.printf("diffs: %s\n", calculateDiffs(i, u, r));


    }

    private static List<Double> calculateDiffs(List<Double> x, List<Double> y, double k){
        return IntStream.range(0, x.size())
                .mapToObj(i -> Math.abs(x.get(i) * k - y.get(i)))
                .toList();
    }

    private static Double calculateSlope(List<Double> x, List<Double> y) {
        int n = x.size();
        Double sx = x.stream().mapToDouble(d -> d).sum();
        Double sy = y.stream().mapToDouble(d -> d).sum();

        double sxsy =
                IntStream.range(0, x.size())
                        .mapToObj(i -> x.get(i) * y.get(i))
                        .toList()
                        .stream().mapToDouble(d -> d).sum();

        double sx2 = x.stream().mapToDouble(d -> d * d).sum();;

        return (double) (n * sxsy - sx * sy) / (n * sx2 - sx * sx);
    }
}
