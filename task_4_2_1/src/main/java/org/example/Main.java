package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Classes of books ===\n\n");

        Book book;
        book = new Encyclopedia("1111-2222-3333", "Very clever Mr.", "Very smart book", 2001, "NYC-Book");
        System.out.println(book);

        book = new ReferenceBook("1111-2222-4444", "Another very clever Mr.", "Book about bees", 2005, "Spb-Book");
        System.out.println(book);
    }
}

abstract class Book {
    protected String isbn;
    protected String author;
    protected String name;
    protected Integer year;
    protected String publisher;

    public abstract String toString();
}

class Encyclopedia extends Book {

    public Encyclopedia(String isbn, String author, String name, Integer year, String publisher) {
        this.isbn = isbn;
        this.author = author;
        this.name = name;
        this.year = year;
        this.publisher = publisher;

    }

    @Override
    public String toString() {
        return String.format("<Encyclopedia> \nISBN: %s\nAuthor: %s\nCaption: %s\nYear: %d\nPublisher: %s\n\n", isbn, author, name, year, publisher);
    }
}

class ReferenceBook extends Book {

    public ReferenceBook(String isbn, String author, String name, Integer year, String publisher) {
        this.isbn = isbn;
        this.author = author;
        this.name = name;
        this.year = year;
        this.publisher = publisher;

    }

    @Override
    public String toString() {
        return String.format("<ReferenceBook> \nISBN: %s\nAuthor: %s\nCaption: %s\nYear: %d\nPublisher: %s\n\n", isbn, author, name, year, publisher);
    }
}