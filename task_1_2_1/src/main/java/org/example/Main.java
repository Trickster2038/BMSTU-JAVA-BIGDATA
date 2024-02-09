package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("Enter strings (empty to finish):\n");
        ArrayList<String> string_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        do{
            string_list.add(sc.nextLine());
        }while(!string_list.get(string_list.size() - 1).isEmpty());

        string_list.remove(string_list.size() - 1);

        String longestString = Collections.max(string_list, Comparator.comparingInt(String::length));
        System.out.printf("Longest string (length=%d): %s%n", longestString.length(), longestString);

        String shortestString = Collections.min(string_list, Comparator.comparingInt(String::length));
        System.out.printf("Shortest string (length=%d): %s%n", shortestString.length(), shortestString);
    }
}