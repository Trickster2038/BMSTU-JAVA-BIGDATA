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

        String[] seacrh_words = new String[]{"java", "hello", "abc"};

        System.out.println("Input String: " + inputString);

        ArrayList<CountedWord> words = new ArrayList<CountedWord>();

        for (String word : seacrh_words) {
            Pattern pattern = Pattern.compile("(^|\\b)" + word + "[ .!?,]", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputString);
            words.add(new CountedWord(word, matcher.results().count()));
        }

        words.sort(CountedWord::compareWordsDesc);

        for(CountedWord word : words){
            System.out.println(word);
        }

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

    public static int compareWordsDesc(CountedWord cw1, CountedWord cw2){
        return (int) (- cw1.cnt + cw2.cnt);
    }
}
