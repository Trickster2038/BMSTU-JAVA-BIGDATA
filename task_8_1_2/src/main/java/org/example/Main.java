package org.example;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("=== Robot with threads work example ===\n\n");

        Robot.walk();
    }
}

// singletone
class Robot {
    static AtomicBoolean leg = new AtomicBoolean(false);

    public static void walk(){
        RobotThread th1 = new RobotThread(true, "RIGHT");
        RobotThread th2 = new RobotThread(false, "LEFT");
        th1.start();
        th2.start();
    }

    public static class RobotThread extends Thread {
        private boolean leg;
        private String leg_str;
        RobotThread(boolean leg_fl, String p_leg_str) {
            leg = leg_fl;
            leg_str = p_leg_str;
        }
        @Override
        public void run() {
            while(true) {
                int sleep_ms = (int) ((Math.random() * (3000 - 1000)) + 1000);
                try {
                    Thread.sleep(sleep_ms);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(leg == Robot.leg.get()) {
                    System.out.printf("%s <ts:%d>\n", leg_str, Calendar.getInstance().get(Calendar.SECOND) );
                    Robot.leg.set(!Robot.leg.get());
                }
            }
        }
    }
}

