package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static java.util.Collections.*;

import java.text.SimpleDateFormat;
import java.util.*;

/*
    В приведенных ниже заданиях необходимо вывести внизу фамилию разработчика, 
    дату и время получения задания, а также дату и время сдачи задания. 
    Для получения последней даты и времени следует использовать класс Date. 
    
    2. Ввести n строк с консоли. Упорядочить и вывести строки 
    в порядке возрастания значений их длины. 
*/

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

        System.out.print("\nAstakhov Sergey\n");
        System.out.print("Get task date: 09/02/24 13:50:00\n");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String formattedDate = formatter.format(date);
        System.out.println("Current Date: " + formattedDate);
    }
}