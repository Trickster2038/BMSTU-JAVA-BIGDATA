package org.example;

/*
    Создать приложение, удовлетворяющее требованиям, приведенным в задании.
    Аргументировать принадлежность классу каждого создаваемого метода и
    корректно переопределить для каждого класса методы equals(), hashCode(), toString().

    1.	Создать объект класса Текстовый файл, используя класс Файл.
    Методы: создать, переименовать, вывести на консоль содержимое, дополнить, удалить.

    About equals() and hashCode(): https://stackoverflow.com/questions/24446763/difference-between-equals-and-hashcode
    If you have two objects that are equal then they must have the same hashCode, however the reverse is not true
 */

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("=== Text file work example ===\n\n");

        TextFile a = new TextFile("a.txt");
        a.createOnDisk();
        a.append("aaaaaa");

        TextFile a_copy = new TextFile("a_copy.txt");
        a_copy.createOnDisk();
        a_copy.append("aaaaaa");

        TextFile a1 = new TextFile("a1.txt");
        a1.createOnDisk();
        a1.append("a1a1a1a1a1a");

        TextFile b = new TextFile("b.txt");
        b.createOnDisk();
        b.append("bbbbbbbbbbbbbbbbb");
        b.append("\nb1b1b1");

        System.out.println("=== Files ===\n\n");

        System.out.println(a);
        System.out.println(a_copy);
        System.out.println(a1);
        System.out.println(b);

        System.out.println("=== Rename a1 -> a2 ===\n");
        System.out.println(a1.rename("a2.txt"));
        System.out.println(a1);

        System.out.println("=== Equals ===\n");
        System.out.printf("a.equals(a): %b%n", a.equals(a));
        System.out.printf("a.equals(a_copy): %b%n", a.equals(a_copy));
        System.out.printf("a.equals(b): %b%n", a.equals(b));

        System.out.println("\n\nEnter a string to delete files and finish: \n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println(a.delete());
        System.out.println(a_copy.delete());
        System.out.println(a1.delete());
        System.out.println(b.delete());
    }
}

class TextFile extends File {

    public TextFile(String filename) {
        super(filename);
    }

    public boolean createOnDisk() throws IOException {
        return createNewFile();
    }

    public String getName() {
        return super.getName();
    }

    public boolean rename(String new_name) {
        return renameTo(new File(new_name));
    }

    public String getText() throws IOException {
        return Files.readString(toPath());
    }

    public void printText() throws IOException {
        System.out.println(getText());
    }

    public void append(String s) throws IOException {
        Files.writeString(toPath(), s, StandardOpenOption.APPEND);
    }

    public boolean delete() throws SecurityException {
        return super.delete();
    }

    @Override
    public String toString() {
        String content = "";
        try {
            content = getText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format(
                "Filepath: %s (rights r-w-x: %b-%b-%b) %nHash: %h %nText: %s%n",
                getAbsolutePath(), canRead(), canWrite(), canExecute(), hashCode(), content);
    }

    // based only on content and rights
    @Override
    public int hashCode() {
        String content = "";
        try {
            content = getText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format(
                "r-w-x: %b-%b-%b content: %s",
               canRead(), canWrite(), canExecute(), content
        ).hashCode();
    }

    // compare also path
    @Override
    public boolean equals(Object obj) {
        return Objects.equals(toString(), obj.toString())
                && getClass().equals(obj.getClass());
    }
}