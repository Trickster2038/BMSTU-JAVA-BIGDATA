package org.example;

/*
       Система Платежи. Клиент имеет Счет в банке и Кредитную Карту (КК).

       + Клиент может оплатить Заказ,
       + сделать платеж на другой Счет,
       + заблокировать КК
       + и аннулировать Счет.
       Администратор может заблокировать КК за превышение кредита.
 */

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
    }
}

class Bank {

    public void registerClient(Client client, Integer cardLimit, Integer accountBalance) throws Exception {
        Card card = new Card(cardLimit);
        Account account = new Account(accountBalance);

        if(cardsIdByClient.containsKey(client) || accountsIdByClient.containsKey(client)) {
            throw new Exception("Client is already registered!");
        }

        cardsIdByClient.put(client, card.getId());
        accountsIdByClient.put(client, account.getId());
        cardsById.put(card.getId(), card);
        accountsById.put(account.getId(), account);
    }

    public void purchaseByCard(Client client, Integer amount, Integer targetAccountId) throws Exception {
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

    public void transferFromAccount(Client client, Integer amount, Integer targetAccountId) throws Exception {
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

    public Integer getCardId(Client client) throws Exception {
        if(!cardsIdByClient.containsKey(client)){
            throw new Exception("Client has no card!");
        }
        return cardsIdByClient.get(client);
    }

    public void blockCardById(Client client, Integer targetCardId) throws Exception {
        if(!cardsIdByClient.containsKey(client)) {
            throw new Exception("Client has no card!");
        }
        Integer cardId = cardsIdByClient.get(client);
        if(!cardId.equals(targetCardId)) {
            throw new Exception("Client is not owner of card!");
        }
        Card card = cardsById.get(cardId);
        card.block();
        cardsById.put(cardId, card);
    }

    public void closeAccount(Client client) throws Exception {
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
    }

    private  HashMap<Client, Integer> cardsIdByClient;
    private  HashMap<Client, Integer> accountsIdByClient;
    private  HashMap<Integer, Card> cardsById;
    private  HashMap<Integer, Account> accountsById;

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

    public Bank() {
        cardsIdByClient = new HashMap<>();
        accountsIdByClient = new HashMap<>();
        cardsById = new HashMap<>();
        accountsById = new HashMap<>();
    }


}

class Admin extends NamedEntity {
    public Admin(String name) {
        super(name);
    }

    public String toString() {
        return String.format("<Admin> %s", super.toString());
    }
}

class Client extends NamedEntity {
    public Client(String name) {
        super(name);
    }

    public String toString() {
        return String.format("<Client> %s", super.toString());
    }
}

class NamedEntity {
    private static Integer idCounter = 0;
    private String name;
    private Integer id;

    public NamedEntity(String name) {
        this.name = name;
        this.id = idCounter;
        idCounter++;
    }

    public String toString() {
        return String.format("id: %s name: %s", this.id, this.name);
    }
}
