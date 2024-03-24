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
        st.add("st4");
        st.add("st5");
        st.add("st6");

        System.out.printf("ArrayList: %s\n", st);
        System.out.printf("ArrayList[last]: %s\n", st.stream().reduce((first, second) -> second).orElse(null));;
        System.out.printf("ArrayList[2]: %s\n", st.stream().skip(2).findFirst().get());
    }
}

