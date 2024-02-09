package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("Cmd args:");

        for (int i = args.length - 1; i >= 0; i--) {
            System.out.printf("Argument %d: %s%n", i, args[i]);
        }
    }
}