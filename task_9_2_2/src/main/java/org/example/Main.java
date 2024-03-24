package org.example;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("=== Stram API work example ===\n\n");

        Collection<People> peoples = Arrays.asList(
                new People("Ivan", 16),
                new People("Petr", 23),
                new People("Maria", 42)
        );

        Comparator<Integer> customComparator = (a, b) -> {
            // Custom comparison logic between a and b
            // Return a negative value if a should come before b, positive if a should come after b, and 0 if they are equal
            return a - b; // Example: Sorting in ascending order
        };

        System.out.printf("ArrayList: %s\n", peoples);
        System.out.printf("List[reversed]: %s\n", peoples
                .stream()
                .sorted(Comparator.comparing(People::getName))
                .collect(Collectors.toList()).reversed()
        );
    }
}

class People {
    public String name;
    public int age;

    public String toString(){
        return String.format("%s-%d", name, age);
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
}

