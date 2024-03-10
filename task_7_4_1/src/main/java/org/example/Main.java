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

        String inputString = "Hello, Java! Java Java abc cba cba hello hElLo App AAAAA. Hello. Bbnn? Java. Mmmm!";

        String inputChar = "a";

        Pattern pattern = Pattern.compile("(^|\\b)\\w+[ .!?,]");

        Matcher matcher = pattern.matcher(inputString);

        System.out.println("Input String: " + inputString);

        ArrayList<CountedWord> words = new ArrayList<CountedWord>();

        while (matcher.find()) {
            String word = inputString.substring(matcher.start(), matcher.end() - 1);
            Pattern pattern2 = Pattern.compile(inputChar);

            Matcher matcher2 = pattern2.matcher(word);
            words.add(new CountedWord(word, matcher2.results().count()));
        }

        words.sort(Main::compareWords);

        for(CountedWord word : words){
            System.out.println(word);
        }
    }

    private static int compareWords(CountedWord cw1, CountedWord cw2){
        if (cw1.cnt != cw2.cnt) {
            return (int) (cw1.cnt - cw2.cnt);
        }
        return cw1.word.compareTo(cw2.word);
    }
}

class CountedWord {
    public String word;
    public long cnt;

    CountedWord(String word, long cnt){
        this.word = word;
        this.cnt = cnt;
    }

    public String toString(){
        return String.format("<word> %s [%d]", word, cnt);
    }
}
