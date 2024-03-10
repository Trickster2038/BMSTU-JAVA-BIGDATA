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

        String inputString = "apple zalgo error nuget redemtion orange";

        ArrayList<String> strs = new ArrayList<String>();

        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        Matcher matcher = pattern.matcher(inputString);

        while (matcher.find()) {
            String sub = inputString.substring(matcher.start(), matcher.end());
            strs.add(sub);
        }

        strs = buildChains(inputString, strs, 0);

        System.out.println("Input String: " + inputString);

        System.out.println("Longest String: " + strs.stream().max((s1, s2) -> s1.length() - s2.length()).orElseThrow());

    }

    private static ArrayList<String> buildChains(String inputString, ArrayList<String> current_chains, int depth){
        ArrayList<String> new_chains = new ArrayList<String>();
        boolean changes_fl = false;
        for(int i = 0; i < current_chains.size(); i++) {
            String end = current_chains.get(i).substring(current_chains.get(i).length() - 1);
            System.out.printf("=> [%d] '%s' : '%s'\n", depth ,end, current_chains.get(i));
            Pattern pattern = Pattern.compile("\\b" + end + "\\w+\\b");
            Matcher matcher = pattern.matcher(inputString);
            boolean fl_new_appends = false;
            while (matcher.find()) {
                changes_fl = true;
                fl_new_appends = true;
                new_chains.add(current_chains.get(i) + " " + inputString.substring(matcher.start(), matcher.end()));
            }
            if(!fl_new_appends){
                new_chains.add(current_chains.get(i));
            }
        }
        if(changes_fl){
            new_chains = buildChains(inputString, new_chains, depth + 1);
        }
        return new_chains;
    }
}
