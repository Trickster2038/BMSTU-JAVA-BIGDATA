package org.example;

/*
    Создать классы, спецификации которых приведены ниже.
    Определить конструкторы и методы setТип(), getТип(), toString().
    Определить дополнительно методы в классе, создающем массив объектов.
    Задать критерий выбора данных и вывести эти данные на консоль.

    2. Customer: id, Фамилия, Имя, Отчество, Адрес, Номер кредитной карточки, Номер банковского счета.
    Создать массив объектов. Вывести:
    a) список покупателей в алфавитном порядке;
    b) список покупателей, у которых номер кредитной карточки находится в заданном интервале.
 */

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

        System.out.println("Enter substring to replace (from):");
        String substring_from = scanner.nextLine();

        System.out.println("Enter substring to replace (to):");
        String substring_to = scanner.nextLine();

        BufferedReader reader = new BufferedReader(new FileReader(in_filename));
        BufferedWriter writer = new BufferedWriter(new FileWriter(out_filename));

        String currentLine;

        System.out.println("File compare:");

        while ((currentLine = reader.readLine()) != null) {
            System.out.printf("%s\n", currentLine);
            System.out.printf("> %s\n", currentLine.replace(substring_from, substring_to));
            writer.write(currentLine.replace(substring_from, substring_to) + System.lineSeparator());
        }

        writer.close();
        reader.close();
    }
}
