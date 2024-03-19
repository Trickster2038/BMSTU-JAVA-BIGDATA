package org.example;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("=== Bank with threads work example ===\n\n");

        BankThread thread1 = new BankThread(1, 1, "register", 0);
        BankThread thread2 = new BankThread(2, 1, "put_cash", 10);
        BankThread thread3 = new BankThread(3, 1, "get_cash", 10);
        BankThread thread4 = new BankThread(4, 1, "put_cash", 5);
        BankThread thread5 = new BankThread(5, 1, "get_cash", 3);

        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Thread.sleep(1000);
        thread2.join(); // put everywhere for correct work
        thread3.start();
        Thread.sleep(1000);
        thread4.start();
        Thread.sleep(1000);
        thread5.start();
    }
}

// singletone
class Bank {

    private static HashMap<Integer, Integer> accounts = new HashMap<>();

    public static int getCash(int client_id, int amt) throws BankException, InterruptedException {
        synchronized (accounts) {
            if (!accounts.containsKey(client_id)) {
                throw new BankException("client doesn't exist");
            }
            if (accounts.getOrDefault(client_id, 0) < amt || amt <= 0) {
                throw new BankException("incorrect balance or amount");
            }
            accounts.put(client_id, accounts.getOrDefault(client_id, 0) - amt);
            Thread.sleep(3000);
            return accounts.getOrDefault(client_id, 0);
        }
    }

    public synchronized static int putCash(int client_id, int amt) throws BankException, InterruptedException {
        synchronized (accounts) {
            if (!accounts.containsKey(client_id)) {
                throw new BankException("client doesn't exist");
            }
            if (amt <= 0) {
                throw new BankException("incorrect amount");
            }
            accounts.put(client_id, accounts.getOrDefault(client_id, 0) + amt);
            Thread.sleep(3000);
            return accounts.getOrDefault(client_id, 0);
        }
    }

    public synchronized static void registerClient(int client_id) throws BankException, InterruptedException {
        synchronized (accounts) {
            if (accounts.containsKey(client_id)) {
                throw new BankException("client already exists");
            }
            Thread.sleep(3000);
            accounts.put(client_id, 0);
        }
    }

    public static class BankException extends Exception {

        BankException(String msg){
            super(msg);
        }
    }
}

class BankThread extends Thread {
    public int amt;
    public int clientId;
    public int id;
    public String operation;
    BankThread(int p_id, int p_client_id, String p_operation, int p_amt) {
        id = p_id;
        amt = p_amt;
        clientId = p_client_id;
        operation = p_operation;
    }
    @Override
    public void run() {
        System.out.printf("\n>>> <begin ts:%d> tx-%d client-%d  operation-%s amt-%d\n", Calendar.getInstance().get(Calendar.SECOND) ,id, clientId, operation, amt);
        switch (operation) {
            case "register" -> {
                try {
                    Bank.registerClient(clientId);
                    System.out.printf("> [%d] client registered\n", id);
                } catch (Bank.BankException | InterruptedException e) {
                    System.out.printf("> [%d] Exception: %s\n", id, e);
                }
            }
            case "get_cash" -> {
                try {
                    int balance = Bank.getCash(clientId, amt);
                    System.out.printf("> [%d] get cash, balance: %d\n", id, balance);
                } catch (Bank.BankException | InterruptedException e) {
                    System.out.printf("> [%d] Exception: %s\n", id, e);
                }
            }
            case "put_cash" -> {
                try {
                    int balance = Bank.putCash(clientId, amt);
                    System.out.printf("> [%d] put cash, balance: %d\n", id, balance);
                } catch (Bank.BankException | InterruptedException e) {
                    System.out.printf("> [%d] Exception: %s\n", id, e);
                }
            }
            default -> System.out.printf("> [%d] unknown operation: %s \n", id, operation);
        }
        System.out.printf(">>> <finish ts:%d> tx-%d\n", Calendar.getInstance().get(Calendar.SECOND), id);
    }
}