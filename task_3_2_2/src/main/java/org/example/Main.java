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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Customer class work example ===\n\n");

        CustomerCollection cs_collection = new CustomerCollection(
                new Customer[]{
                        new Customer(1, "Astakhov", "Sergey", "Victorovich", "Balashiha", "1111-2222-0000", "1111-2222-8000"),
                        new Customer(2, "Zakharov", "Pavel", "X.", "Moscow", "1111-2222-0001", "1111-2222-8001"),
                        new Customer(3, "Loktev", "Michael", "X.", "Kemerovo", "1111-2222-0002", "1111-2222-8002"),
                        new Customer(4, "Petrov", "Ivan", "X.", "Balashiha", "1111-2222-0003", "1111-2222-8003"),
                        new Customer(5, "Anisin", "Petr", "X.", "Ivanovo", "1111-2222-0004", "1111-2222-8004")
                }
        );

        System.out.println("=== Customers sorted by alphabet ===\n\n");

        System.out.println(cs_collection.orderByAlphabet());

        System.out.println("=== Customers filtered by cardnums(1111-2222-0001, 1111-2222-0003) ===\n\n");

        System.out.println(cs_collection.filterByCardnums("1111-2222-0001", "1111-2222-0003"));
    }
}

class CustomerCollection {
    Customer[] data;

    public CustomerCollection(Customer[] p_data) {
        this.data = p_data;
    }

    public String toString() {
        String result = "";
        for(Customer elem: data){
            result = String.format("%s %n%n%s", result, elem);
        }
        return result;
    }

    public CustomerCollection orderByAlphabet() {
        List<Customer> data_list = Arrays.asList(this.data);
        data_list.sort(Comparator.comparing(x -> x.getFio()));
        return new CustomerCollection(data_list.toArray(new Customer[0]));
    }

    public CustomerCollection filterByCardnums(String num1, String num2) {
        Customer[] filtered = Arrays.stream(this.data)
                .filter(x ->
                        Long.parseLong(x.getCardNumber().replace("-", "")) >=
                                Long.parseLong(num1.replace("-", ""))
                        && Long.parseLong(x.getCardNumber().replace("-", "")) <=
                                Long.parseLong(num2.replace("-", ""))
                )
                .toArray(Customer[]::new);
        return new CustomerCollection(filtered);
    }
}

class Customer {
    int id;
    String surname;
    String name;
    String thirdname;
    String address;
    String card_number;
    String account_number;

    public Customer(int id, String surname, String name, String thirdname, String address, String card_number, String account_number) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.thirdname = thirdname;
        this.address = address;
        this.card_number = card_number;
        this.account_number = account_number;
    }

    public String toString() {
        return String.format("Client(id=%d) %s %s %s%n", this.id, this.surname, this.name, this.thirdname) +
                String.format("Address: %s%n", this.address) +
                String.format("Card: %s Account:%s%n", this.card_number, this.account_number);
    }

    public String getFio() {
        return  String.format("%s %s %s", this.surname, this.name, this.thirdname);
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getThirdname() {
        return thirdname;
    }

    public String getAddress() {
        return address;
    }

    public String getCardNumber() {
        return card_number;
    }

    public String getAccountNumber() {
        return account_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThirdname(String thirdname) {
        this.thirdname = thirdname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
}