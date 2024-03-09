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

        System.out.println("=== Set work example ===\n\n");

        MyIntSet set1 = new MyIntSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);

        MyIntSet set2 = new MyIntSet();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);

        System.out.printf("set-1: %s \n", set1);
        System.out.printf("set-2: %s \n", set2);
        System.out.printf("union: %s \n", set1.union(set2));
        System.out.printf("cross: %s \n", set1.cross(set2));

    }
}
class MyIntSet extends HashSet<Integer> {

    MyIntSet() {
        super();
    }

    public MyIntSet union(MyIntSet set1) {
        MyIntSet result = new MyIntSet();
        result.addAll(this);
        result.addAll(set1);
        return result;
    }

    public MyIntSet cross(MyIntSet set1) {
        MyIntSet result = new MyIntSet();
        for(Integer elem : this){
            if(set1.contains(elem)) {
                result.add(elem);
            }
        }
        return result;
    }
}

