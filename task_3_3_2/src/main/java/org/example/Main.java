package org.example;

/*
        Создать приложение, удовлетворяющее требованиям, приведенным в задании.
        Аргументировать принадлежность классу каждого создаваемого метода и корректно переопределить
        для каждого класса методы equals(), hashCode(), toString().

        2.	Создать объект класса Одномерный массив, используя класс Массив.
        Методы: создать, вывести на консоль, выполнить операции (сложить, вычесть, перемножить).
*/

import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("=== MyArray class work example ===\n");

        MyArray a = new MyArray();
        a.add(1);
        a.add(2);
        a.add(3);

        MyArray a1 = new MyArray();
        a1.add(1);
        a1.add(2);
        a1.add(3);

        MyArray a2 = a;

        MyArray b = new MyArray();
        b.add(6);
        b.add(5);
        b.add(4);

        MyArray c = new MyArray();
        c.add(0);

        System.out.println("=== Arrays ===\n");
        System.out.printf("a: %s %n%n", a);
        System.out.printf("a1: %s %n%n", a1);
        System.out.printf("a2: %s %n%n", a2);
        System.out.printf("b: %s %n%n", b);
        System.out.printf("c: %s %n%n", c);

        System.out.println("=== Math ===\n");
        System.out.printf("a + b: %s %n%n", a.sum(b));
        System.out.printf("a - b: %s %n%n", a.sub(b));
        System.out.printf("a * b: %s %n%n", a.mul(b));

        System.out.println("=== Equals ===\n");
        System.out.printf("a.equals(a): %b %n", a.equals(a));
        System.out.printf("a.equals(a1): %b %n", a.equals(a1));
        System.out.printf("a.equals(a2): %b %n", a.equals(a2));
        System.out.printf("a.equals(b): %b %n%n", a.equals(b));


        System.out.println("=== Exception catch ===\n");
        try {
            System.out.printf("a + c: %s %n%n", a.sum(c));
        }
        catch (Exception e) {
            System.out.printf("Exception (a + c): %s", e);
        }

    }
}


class MyArray extends ArrayList<Integer> {

    public MyArray() {
        super();
    }

    public boolean add(Integer x) {
        return super.add(x);
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String result = "MyArray[]{";
        for(Integer elem: this){
            result = String.format("%s %5d", result, elem);
        }
        result = String.format("%s } (hash: %h)", result, this.hashCode());
        return result;
    }

    public MyArray sum(MyArray v) throws Exception {
        MyArray result = new MyArray();
        if(this.size() != v.size()) {
            throw new Exception("Sizes don't match!");
        }
        for (int i = 0; i < this.size(); i++) {
            result.add(this.get(i) + v.get(i));
        }
        return result;
    }

    public MyArray sub(MyArray v) throws Exception {
        MyArray result = new MyArray();
        if(this.size() != v.size()) {
            throw new Exception("Sizes don't match!");
        }
        for (int i = 0; i < this.size(); i++) {
            result.add(this.get(i) - v.get(i));
        }
        return result;
    }

    public MyArray mul(MyArray v) throws Exception {
        MyArray result = new MyArray();
        if(this.size() != v.size()) {
            throw new Exception("Sizes don't match!");
        }
        for (int i = 0; i < this.size(); i++) {
            result.add(this.get(i) * v.get(i));
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(Integer elem: this){
            hash = Objects.hash(hash, elem);
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString())
                && this.getClass().equals(o.getClass());
    }
}