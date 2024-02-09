package org.example;

import java.util.*;
import java.util.stream.Collectors;

// Ввести с консоли n целых чисел и поместить их в массив. На консоль вывести:
// 1. Четные и нечетные числа


public class Main {
    public static void main(String[] args) {

        System.out.print("Enter ints (not int to finish):\n");
        ArrayList<Integer> int_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int input = sc.nextInt();
            int_list.add(input);
        }

        List<Integer> evenNumbers = int_list.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());

        System.out.print("Even numbers:");
        System.out.println(evenNumbers);

        List<Integer> unEvenNumbers = int_list.stream()
                .filter(x -> x % 2 == 1)
                .collect(Collectors.toList());

        System.out.print("Uneven numbers:");
        System.out.println(unEvenNumbers);
    }
}