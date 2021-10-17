package com.mvnikitin.practice.lesson3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class LockBasedCounter {

    final Lock lock = new ReentrantLock();
    private int counter;
    private int decrementsCount;
    private int incrementsCount;

    public int incrementAndGet() {
        while(true) {
            if (lock.tryLock()) {
               try {
                   System.out.println(Thread.currentThread().getName() + " increments the counter: value before = " + counter);
                   counter++;
                   incrementsCount++;
               } finally {
                   System.out.println(Thread.currentThread().getName() + " incremented the counter: value now = " + counter);
                   lock.unlock();
               }
               return counter;
            }
        }
    }

    public int decrementAndGet() {
        while(true) {
            if (lock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " dencrements the counter: value before = " + counter);
                    counter--;
                    decrementsCount++;
                } finally {
                    System.out.println(Thread.currentThread().getName() + " decremented the counter: value now = " + counter);
                    lock.unlock();
                }
                return counter;
            }
        }
    }

    public void printStatistics() {
        System.out.println("counter= " + counter + ", decrements number= " + decrementsCount + ", increments number= " + incrementsCount);
    }

    public static void main(String[] args) throws InterruptedException {
        final int THREAD_ITERATIONS_COUNT = 10;
        final int THREAD_COUNT =5;

        LockBasedCounter counter = new LockBasedCounter();

        Runnable routine = () -> {
            Random rnd = new Random();
            for (int i = 0; i < THREAD_ITERATIONS_COUNT; i++) {
                if (rnd.nextBoolean()) {
                    counter.incrementAndGet();
                } else {
                    counter.decrementAndGet();
                }
            }
        };

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(routine);
            threads[i].start();
        }

        for (Thread t: threads) {
            t.join();
        }

        counter.printStatistics();
    }
}
