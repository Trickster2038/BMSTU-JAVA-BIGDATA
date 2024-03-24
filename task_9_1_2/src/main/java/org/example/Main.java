package org.example;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("=== Stram API work example ===\n\n");

        ArrayList<String> st = new ArrayList<>();
        st.add("st1");
        st.add("st2");
        st.add("st3");
        st.add("apple");
        st.add("st5");
        st.add("st6");

        String rg1 = ".*app.*";
        String rg2 = ".*pineapple.*";

        System.out.printf("ArrayList: %s\n", st);
        System.out.printf("ArrayList[first]: %s\n", st.stream().findFirst().get());
        System.out.printf("ArrayList[regex='%s']: %b\n", rg1, st.stream().anyMatch(row -> row.matches(rg1)));
        System.out.printf("ArrayList[regex='%s']: %b\n", rg2, st.stream().anyMatch(row -> row.matches(rg2)));
    }
}

