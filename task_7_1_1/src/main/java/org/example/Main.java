package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("=== Regex work example ===\n\n");

        String inputString = "Hello World Java Example a b";
        int k = 2;
        char replacementChar = '@';

        String result = inputString.replaceAll("(\\b\\w{" + (k - 1) + "})(\\w)(\\w*)", "$1" + replacementChar + "$3");

        System.out.println("k: " + k);
        System.out.println("ReplacementChar: " + replacementChar);
        System.out.println("Input String: " + inputString);
        System.out.println("Modified String: " + result);

    }
}
