package org.example;


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("=== File class work example ===\n\n");

        System.out.println("Enter input file name (example.java): ");

        Scanner scanner = new Scanner(System.in);

        String in_filename = scanner.nextLine();

        if(Objects.equals(in_filename, "")) {
            in_filename = "example.java";
            System.out.println("using example.java");
        }

        System.out.println("Enter output file name (out_example.java): ");

        String out_filename = scanner.nextLine();

        if(Objects.equals(out_filename, "")) {
            out_filename = "out_example.java";
            System.out.println("using out_example.java");
        }

        out_filename = "./output/" + out_filename;

        File out_file = new File(out_filename);
        File dir = new File("./output");
        dir.mkdirs();
        out_file.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(in_filename));
        BufferedWriter writer = new BufferedWriter(new FileWriter(out_filename));

        String currentLine;

        System.out.println("File compare:");

        while ((currentLine = reader.readLine()) != null) {
            System.out.printf("%s\n", currentLine);
            String new_str = currentLine.replace("public ", "private ").replace("public\t", "private\t");
            System.out.printf("> %s\n", new_str);
            writer.write(new_str + System.lineSeparator());
        }

        writer.close();
        reader.close();
    }
}
