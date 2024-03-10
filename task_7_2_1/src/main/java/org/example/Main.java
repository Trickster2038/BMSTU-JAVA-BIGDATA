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

        String inputString = "Hello, Java! avaJ abc cba cba";

        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        Matcher matcher = pattern.matcher(inputString);

        System.out.println("Input String: " + inputString);

        while (matcher.find()) {
            String sub = inputString.substring(matcher.start(), matcher.end());
            String sub_reversed = new StringBuilder(sub).reverse().toString();
            Pattern pattern2 = Pattern.compile("\\b" + sub_reversed + "\\b");
            Matcher matcher2 = pattern2.matcher(inputString);
            while (matcher2.find()) { // if for single usage + set for no reverse pairs
                System.out.printf("%s : %s\n", sub, inputString.substring(matcher2.start(), matcher2.end()));
            }
        }
    }
}
