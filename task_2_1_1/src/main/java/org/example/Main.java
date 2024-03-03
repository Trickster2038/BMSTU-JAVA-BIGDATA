package org.example;

import java.text.SimpleDateFormat;
import java.util.*;

/*
    В приведенных ниже заданиях необходимо вывести внизу фамилию разработчика, 
    дату и время получения задания, а также дату и время сдачи задания. 
    Для получения последней даты и времени следует использовать класс Date. 
    
    1. Ввести n строк с консоли, найти самую короткую и самую длинную строки. 
    Вывести найденные строки и их длину. 

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

        String longestString = Collections.max(string_list, Comparator.comparingInt(String::length));
        System.out.printf("Longest string (length=%d): %s%n", longestString.length(), longestString);

        String shortestString = Collections.min(string_list, Comparator.comparingInt(String::length));
        System.out.printf("Shortest string (length=%d): %s%n", shortestString.length(), shortestString);

        System.out.print("\nAstakhov Sergey\n");
        System.out.print("Get task date: 09/02/24 13:50:00\n");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String formattedDate = formatter.format(date);
        System.out.println("Current Date: " + formattedDate);
    }
}