package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("=== Parking collection work example ===\n\n");

        Parking parking = new Parking(10);

        System.out.printf("init: %s\n", Arrays.toString(parking.getData()));

        parking.Park();

        System.out.printf("=> %s\n", Arrays.toString(parking.getData()));

        parking.Park();

        System.out.printf("=> %s\n", Arrays.toString(parking.getData()));

        parking.Park();

        System.out.printf("=> %s\n", Arrays.toString(parking.getData()));

        parking.Park();

        System.out.printf("=> %s\n", Arrays.toString(parking.getData()));

        parking.DriveAway(1);

        System.out.printf("<=[1] %s\n", Arrays.toString(parking.getData()));

        parking.Park();

        System.out.printf("=> %s\n", Arrays.toString(parking.getData()));

    }
}

class Parking {

    private boolean[] data;
    Parking(int n) {
        data = new boolean[n];
        Arrays.fill(data, false);
    }

    public boolean[] getData() {
        return data;
    }

    public boolean Park(){
        for (int i = 0; i < data.length; i++) {
            if(!data[i]){
                data[i] = true;
                return true;
            }
        }
        return false;
    }

    public void DriveAway(int position){
        data[position] = false;
    }

}