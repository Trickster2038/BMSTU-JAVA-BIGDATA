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

import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Customer class work example ===\n\n");

        Scanner sc = new Scanner(System.in);

        int m = 0;
        String buf;
        boolean retry_fl = true;

        while(retry_fl) {
            try {
                System.out.println("Enter number of customers:");
                buf = sc.next();
                m = Integer.parseInt(buf);
                retry_fl = false;
            } catch (NumberFormatException e) {
                System.out.println("Exception: num format is incorrect!");
            }
        }

        Customer[] customers = new Customer[m];

        for(int i = 0; i < m; i++) {
            System.out.printf("Enter name of customer[%d]:\n", i);
            String name = sc.next();

            System.out.printf("Enter surnmame of customer[%d]:\n", i);
            String surname = sc.next();

            System.out.printf("Enter thirdname of customer[%d]:\n", i);
            String thirdname = sc.next();

            System.out.printf("Enter address of customer[%d]:\n", i);
            String address = sc.next();

            String card_num = "";

            retry_fl = true;

            while(retry_fl) {
                try {
                    System.out.printf("Enter number of card of customer[%d]:\n", i);
                    card_num = sc.next();
                    Long.parseLong(card_num.replace("-", ""));
                    retry_fl = false;
                } catch (NumberFormatException e) {
                    System.out.println("Exception: num format is incorrect!");
                }
            }

            String account_num = "";

            retry_fl = true;

            while(retry_fl) {
                try {
                    System.out.printf("Enter number of account of customer[%d]:\n", i);
                    account_num = sc.next();
                    Long.parseLong(account_num.replace("-", ""));
                    retry_fl = false;
                } catch (NumberFormatException e) {
                    System.out.println("Exception: num format is incorrect!");
                }
            }

            customers[i] = new Customer(name, surname, thirdname, address, card_num, account_num);
        }

        CustomerCollection cs_collection = new CustomerCollection(customers);

        System.out.println("=== Customers sorted by alphabet ===\n\n");

        System.out.println(cs_collection.orderByAlphabet());

        System.out.println("=== Customers filtered by cardnums(1111-2222-0001, 1111-2222-0003) ===\n\n");

        try {
            System.out.println(cs_collection.filterByCardnums("1111-2222-0001", "1111-2222-0003"));
        } catch (CustomerException e) {
            System.out.printf("Customer exception: %s\n", e);
        }

        System.out.println("=== Customers filtered by cardnums(1111-ffff-0001, 1111-2222-0003) ===\n\n");

        try {
            System.out.println(cs_collection.filterByCardnums("1111-ffff-0001", "1111-2222-0003"));
        } catch (CustomerException e) {
            System.out.printf("Customer exception: %s\n", e);
        }
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

    public CustomerCollection filterByCardnums(String num1, String num2) throws CustomerException {
        try {
            Long.parseLong(num1.replace("-", ""));
            Long.parseLong(num2.replace("-", ""));
        } catch (NumberFormatException e) {
            throw new CustomerException("Invalid card number!");
        }
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
    static int idCnt = 0;
    int id;
    String surname;
    String name;
    String thirdname;
    String address;
    String card_number;
    String account_number;

    public Customer(String surname, String name, String thirdname, String address, String card_number, String account_number) {
        this.id = idCnt;
        this.surname = surname;
        this.name = name;
        this.thirdname = thirdname;
        this.address = address;
        this.card_number = card_number;
        this.account_number = account_number;
        idCnt++;
    }

    public String toString() {
        return String.format("Client(id=%d) %s %s %s%n", this.id, this.surname, this.name, this.thirdname) +
                String.format("Address: %s%n", this.address) +
                String.format("Card: %s Account: %s%n", this.card_number, this.account_number);
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

class CustomerException extends Exception {
    public CustomerException(String message) {
        super(message);
    }
}