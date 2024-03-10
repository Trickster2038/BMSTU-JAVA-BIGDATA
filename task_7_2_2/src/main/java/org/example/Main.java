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

        String inputString = "Hello, Java! Java Java abc cba cba hello hElLo";

        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        Matcher matcher = pattern.matcher(inputString);

        System.out.println("Input String: " + inputString);

        Set<String> words = new HashSet<String>();

        while (matcher.find()) {
            String sub = inputString.substring(matcher.start(), matcher.end());
            if(!words.contains(sub.toLowerCase())) {
                words.add(sub.toLowerCase());
                int cnt = 0;
                Pattern pattern2 = Pattern.compile("\\b" + sub + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher matcher2 = pattern2.matcher(inputString);
                while (matcher2.find()) {
                    cnt++;
                }
                System.out.printf("%s : %d\n", sub, cnt);
            }
        }
    }
}
