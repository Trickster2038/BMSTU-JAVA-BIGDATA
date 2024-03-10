package org.example;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("=== Regex work example ===\n\n");

        String inputString = "Hello World Java Example a b";

        Pattern pattern = Pattern.compile("."); // "\\p{L}"

        String result = "";
        String inputCopy = "";

        Matcher matcher = pattern.matcher(inputString);

        while (matcher.find()) {
            result += String.format("\\u%04x  ", (int) inputString.substring(matcher.start(), matcher.end()).charAt(0));
            inputCopy +=  String.format("%c  ", (int) inputString.substring(matcher.start(), matcher.end()).charAt(0));
        }

        System.out.println("Input String: " + inputCopy);
        System.out.println("Modified String: " + result);

    }
}
