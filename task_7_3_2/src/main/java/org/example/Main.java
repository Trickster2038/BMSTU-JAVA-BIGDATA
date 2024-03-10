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

        String inputString = "Hello, Java! Java Java abc cba cba hello hElLo. Hello. Bbnn? Java. Mmmm!";

        Pattern pattern = Pattern.compile("([A-Z][^.!?]*[.!?])");

        Matcher matcher = pattern.matcher(inputString);

        System.out.println("Input String: " + inputString);

        ArrayList<String> sentences = new ArrayList<String>();

        while (matcher.find()) {
            System.out.println("=> " + inputString.substring(matcher.start(), matcher.end()));
            sentences.add(inputString.substring(matcher.start(), matcher.end()));
        }

        // count max word-connected graph nodes
        int max = 0;
        for(Integer i=0; i < sentences.size(); i++){
            int cnt = 0;
            for (Integer j=0; j < sentences.size(); j++) {
                 Pattern pattern2 = Pattern.compile("(^|\\b)\\w+[ .!?,]", Pattern.CASE_INSENSITIVE);
                 Matcher matcher2 = pattern2.matcher(sentences.get(i));
//                 System.out.println(">>> " + sentences.get(i));
                 while (matcher2.find()) {
                    String sub = sentences.get(i).substring(matcher2.start(), matcher2.end() - 1);
//                    System.out.println(i.toString() + " - " + sub);
                    Pattern pattern3 = Pattern.compile("(^|\\b)" + sub + "[ .!?,]", Pattern.CASE_INSENSITIVE);
                    Matcher matcher3 = pattern3.matcher(sentences.get(j));
                    if(matcher3.find()){
                        System.out.println("[" + i.toString() + ":" + j.toString() + "] " + sub + " : " +  sentences.get(j).substring(matcher3.start(), matcher3.end() - 1));
                        cnt++;
                        break;
                    }
                }
            }
            if(cnt > max){
                max = cnt;
            }
        }

        System.out.println("Max: " + max);
    }
}
