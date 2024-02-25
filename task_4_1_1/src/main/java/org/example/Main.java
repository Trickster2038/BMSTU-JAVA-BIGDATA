package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== A program about City with inner classes ===\n");

        City city = new City("Reutov");
        city.add("street", "Lenina");
        city.add("prospekt", "50 years of Victory");
        city.add("avenue", "Enthusiasts");
        city.add("avenue", "Green");

        System.out.println(city.toString());
    }
}

class City {

    private ArrayList<TopographyObject> content = new ArrayList<>();
    private String name;

    public City(String name) {
        this.name = name;
    }

    public void add(String type, String name) {
        TopographyObject t = new TopographyObject(type, name);
        content.add(t);
    }

    public String toString() {
        String s = String.format("<city> %s\n\n", name);
        for(TopographyObject x : content) {
            s = String.format("%s%s\n", s, x.toString());
        }
        return s;
    }

    private class TopographyObject {
        private final String type;

        private final String name;

        public TopographyObject(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String toString() {
            return String.format("<%s> %s", type, name);
        }
    }

}