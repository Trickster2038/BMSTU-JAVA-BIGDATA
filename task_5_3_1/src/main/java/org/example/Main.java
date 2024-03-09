package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("=== File class work example ===\n\n");

        System.out.println("Enter input file name (block.txt): ");

        Scanner scanner = new Scanner(System.in);

        String in_filename = scanner.nextLine();

        if(Objects.equals(in_filename, "")) {
            in_filename = "block.txt";
            System.out.println("using block.txt");
        }

        System.out.println("Enter output file name (out.txt): ");

        String out_filename = scanner.nextLine();

        if(Objects.equals(out_filename, "")) {
            out_filename = "out.txt";
            System.out.println("using out.txt");
        }

        File out_file = new File(out_filename);
        out_file.createNewFile();

        System.out.println("Enter substring to delete:");

        String substring = scanner.nextLine();

        BufferedReader reader = new BufferedReader(new FileReader(in_filename));
        BufferedWriter writer = new BufferedWriter(new FileWriter(out_filename));

        String currentLine;

        System.out.println("File compare:");

        while ((currentLine = reader.readLine()) != null) {
            System.out.printf("%s\n", currentLine);
            System.out.printf("> %s\n", currentLine.replace(substring, ""));
            writer.write(currentLine.replace(substring, "") + System.lineSeparator());
        }

        writer.close();
        reader.close();
    }
}
