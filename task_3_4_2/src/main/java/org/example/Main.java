package org.example;

/*
       Система Платежи. Клиент имеет Счет в банке и Кредитную Карту (КК).

       + Клиент может оплатить Заказ,
       + сделать платеж на другой Счет,
       + заблокировать КК
       + и аннулировать Счет.
       + Администратор может заблокировать КК за превышение кредита.
 */

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== Bank class work example ===\n\n");

        Person clientSergey = new Person("Sergey");
        Person clientMax = new Person("Max");
        Person clientDmitry = new Person("Dmitry");

        Person adminAlex = new Person("Alex");

        Bank bank = new Bank();

        bank.registerClient(clientSergey, -5000, 10000);
        bank.registerClient(clientMax, -5000, 10000);
        bank.registerClient(clientDmitry, -5000, 10000);

        bank.hireAdmin(adminAlex);

        bank.purchaseByCard(clientSergey, 100, bank.getAccountIdByPerson(clientMax));            // card -> account; Sergey -100; 10000; Max 0; 10100
        bank.purchaseByCard(clientDmitry, 15000, bank.getAccountIdByPerson(clientMax));          // card -> account; Dmitry -15000; 10000; Max 0; 25100
        bank.transferFromAccount(clientDmitry, 100, bank.getAccountIdByPerson(clientMax));       // account -> account; Dmitry -15100; 10000; Max 0; 25200
        bank.blockCardById(clientSergey, bank.getCardIdByPerson(clientSergey));                         // card.isBlocked
        bank.closeAccount(clientDmitry);                                                                // card -> account; Dmitry -5100; null
        bank.blockCardById(adminAlex, bank.getCardIdByPerson(clientDmitry));                            // card.isBlocked

        System.out.println("=== System state after all operations ===\n\n");

        System.out.println(bank);
    }
}

class Bank {

    private  HashMap<Person, Integer> cardsIdByClient;
    private  HashMap<Person, Integer> accountsIdByClient;
    private  HashMap<Integer, Card> cardsById;
    private  HashMap<Integer, Account> accountsById;
    private HashSet<Person> admins;
    private HashSet<Person> clients;

    public Bank() {
        cardsIdByClient = new HashMap<>();
        accountsIdByClient = new HashMap<>();
        cardsById = new HashMap<>();
        accountsById = new HashMap<>();
        admins = new HashSet<>();
        clients = new HashSet<>();
    }

    public String toString() {
        String result = "=== Clients ===\n";
        for(Person client : clients) {
            result = String.format("%s %n%n %s", result, client);
            if(cardsIdByClient.containsKey(client)) {
                Card card = cardsById.get(cardsIdByClient.get(client));
                result = String.format("%s %n %s", result, card);
            }
            if(accountsIdByClient.containsKey(client)) {
                Account account = accountsById.get(accountsIdByClient.get(client));
                result = String.format("%s %n %s", result, account);
            }
        }
        result += "\n\n=== Admins ===\n";
        for(Person admin : admins) {
            result = String.format("%s %n %s", result, admin);
        }
        return result;
    }

    public void hireAdmin(Person admin) {
        admins.add(admin);
    }

    public void registerClient(Person client, Integer cardLimit, Integer accountBalance) throws Exception {
        Card card = new Card(cardLimit);
        Account account = new Account(accountBalance);

        if(clients.contains(client)) {
            throw new Exception("Client is already registered!");
        }

        cardsIdByClient.put(client, card.getId());
        accountsIdByClient.put(client, account.getId());
        cardsById.put(card.getId(), card);
        accountsById.put(account.getId(), account);

        clients.add(client);
    }

    public void purchaseByCard(Person client, Integer amount, Integer targetAccountId) throws Exception {
        if(!cardsIdByClient.containsKey(client)) {
            throw new Exception("Client has no card!");
        }
        if(!accountsById.containsKey(targetAccountId)) {
            throw new Exception("Target account doesn't exists!");
        }
        if(amount <= 0) {
            throw new Exception("Incorrect amount!");
        }
        Integer cardId = cardsIdByClient.get(client);
        Card card = cardsById.get(cardId);
        if(card.getIsBlocked()) {
            throw new Exception("Card is blocked!");
        }
        Account targetAccount = accountsById.get(targetAccountId);
        card.changeBalance(-amount);
        targetAccount.changeBalance(+amount);
        cardsById.put(cardId, card);
        accountsById.put(targetAccountId, targetAccount);
    }

    public void transferFromAccount(Person client, Integer amount, Integer targetAccountId) throws Exception {
        if(!accountsIdByClient.containsKey(client)) {
            throw new Exception("Client has no account!");
        }
        if(!accountsById.containsKey(targetAccountId)) {
            throw new Exception("Target account doesn't exists!");
        }
        if(amount <= 0) {
            throw new Exception("Incorrect amount!");
        }
        Integer accountId = accountsIdByClient.get(client);
        Account account = accountsById.get(accountId);
        if(amount > account.getBalance()) {
            throw new Exception("Incorrect amount!");
        }
        Account targetAccount = accountsById.get(targetAccountId);
        account.changeBalance(-amount);
        targetAccount.changeBalance(+amount);
        accountsById.put(accountId, account);
        accountsById.put(targetAccountId, targetAccount);
    }

    public Integer getCardIdByPerson(Person client) throws Exception {
        if(!cardsIdByClient.containsKey(client)){
            throw new Exception("Client has no card!");
        }
        return cardsIdByClient.get(client);
    }

    public Integer getAccountIdByPerson(Person client) throws Exception {
        if(!accountsIdByClient.containsKey(client)){
            throw new Exception("Client has no account!");
        }
        return accountsIdByClient.get(client);
    }

    public void blockCardById(Person person, Integer targetCardId) throws Exception {
        if(!cardsIdByClient.containsKey(person) && !admins.contains(person)) {
            throw new Exception("Client has no card!");
        }
        Integer personCardId = cardsIdByClient.getOrDefault(person, -1);
        if(!personCardId.equals(targetCardId) && !admins.contains(person)) {
            throw new Exception("Client is not owner of card and is not admin!");
        }
        Card card = cardsById.get(targetCardId);
        if(!personCardId.equals(targetCardId) && admins.contains(person) && !card.limitReached()) {
            throw new Exception("Card limit is not reached!");
        }
        card.block();
        cardsById.put(targetCardId, card);
    }

    public void closeAccount(Person client) throws Exception {
        if(!accountsIdByClient.containsKey(client)) {
            throw new Exception("Client has no account!");
        }
        if(!cardsIdByClient.containsKey(client)) {
            throw new Exception("Client has no card!");
        }
        Integer accountId = accountsIdByClient.get(client);
        Integer cardId = cardsIdByClient.get(client);
        Card card = cardsById.get(cardId);
        Account account = accountsById.get(accountId);

        card.changeBalance(+account.getBalance());
        account.changeBalance(-account.getBalance());

        cardsById.put(cardId, card);
        accountsById.remove(accountId);
        accountsIdByClient.remove(client);
    }

    private class Card {
        private static Integer cardIdCounter = 1000;
        private Integer id;
        private Integer balance;
        private Integer limit;
        private Boolean isBlocked;
        public Card(Integer limit) {
            this.id = cardIdCounter;
            this.balance = 0;
            this.limit = limit;
            this.isBlocked = false;
            cardIdCounter++;
        }

        public String toString() {
            return String.format("<Card> id: %d balance: %d limit: %d isBlocked: %b", id, balance, limit, isBlocked);
        }

        public Boolean limitReached() {
            return balance < limit;
        }

        public void block(){
            this.isBlocked = true;
        }

        public Boolean getIsBlocked() {
            return isBlocked;
        }

        public void changeBalance(Integer amount){
            this.balance += amount;
        }

        public Integer getId() {
            return id;
        }

        public Integer getBalance() {
            return balance;
        }
    }

    private class Account {
        private static Integer accountIdCounter = 1000;
        private Integer id;
        private Integer balance;
        public Account(Integer balance) {
            this.id = accountIdCounter;
            this.balance = balance;
            accountIdCounter++;
        }

        public String toString() {
            return String.format("<Account> id: %d balance: %d", id, balance);
        }

        public void changeBalance(Integer amount){
            this.balance += amount;
        }

        public Integer getId() {
            return id;
        }

        public Integer getBalance() {
            return balance;
        }
    }

}

class Person {
    private static Integer idCounter = 0;
    private String name;
    private Integer id;

    public Person(String name) {
        this.name = name;
        this.id = idCounter;
        idCounter++;
    }

    public String toString() {
        return String.format("<Person> id: %s name: %s", this.id, this.name);
    }
}
