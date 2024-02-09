package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


// Ввести с консоли n целых чисел и поместить их в массив. На консоль вывести:
// 2. Наибольшее и наименьшее число.

public class Main {
    public static void main(String[] args) {

        System.out.print("Enter ints (not int to finish):\n");
        ArrayList<Integer> int_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int input = sc.nextInt();
            int_list.add(input);
        }

        System.out.printf("Max int: %d%n", Collections.max(int_list));
        System.out.printf("Min int: %d%n", Collections.min(int_list));
    }
}