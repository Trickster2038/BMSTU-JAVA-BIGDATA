package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static java.util.Collections.*;

public class Main {
    public static void main(String[] args) {

        System.out.print("Enter strings (empty to finish):\n");
        ArrayList<String> string_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        do{
            string_list.add(sc.nextLine());
        }while(!string_list.get(string_list.size() - 1).isEmpty());

        string_list.remove(string_list.size() - 1);

        string_list.sort(Comparator.comparingInt(String::length));

        System.out.print("Sorted arrayList of strings:\n");

        for (String element : string_list) {
            System.out.println(element);
        }
    }
}