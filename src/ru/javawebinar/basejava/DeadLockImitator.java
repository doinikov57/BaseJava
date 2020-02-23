package ru.javawebinar.basejava;

public class DeadLockImitator {
    private static final String lock1 = "loc1";
    private static final String lock2 = "loc2";

    public static void main(String[] args) {
        new Thread (() -> synchronizedMethod(lock1, lock2)).start();
        new Thread (() -> synchronizedMethod(lock2, lock1)).start();
    }

    static void synchronizedMethod(Object loc1, Object loc2) {
        String l1 = " lock" + loc1;
        String l2 = " lock" + loc2;
        System.out.println(Thread.currentThread().getName() + " wait" + l1);
        synchronized (loc1) {
            System.out.println(Thread.currentThread().getName() + " hold" + l1);
            sleepThread(200);
            System.out.println(Thread.currentThread().getName() + " wait" + l2);
            synchronized (loc2) {
                System.out.println(Thread.currentThread().getName() + " hold" + l2);
            }
        }
    }

    static void sleepThread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}