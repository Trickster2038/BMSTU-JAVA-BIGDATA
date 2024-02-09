package org.example;

import java.util.Scanner;

/*
    1. Создать класс Hello, который будет приветствовать любого пользователя, 
    используя командную строку. 
*/

public class Main {
    public static void main(String[] args) {

        System.out.print("Enter your name:\n");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String pc_name =  System.getProperty("user.name");
        System.out.printf("Hello, %s (%s)%n", name, pc_name);

    }
}