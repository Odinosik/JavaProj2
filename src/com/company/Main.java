package com.company;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        final int n = 15;
        FibonacciTask fibonacci = new FibonacciTask(n);
        ForkJoinPool pool = new ForkJoinPool();
        double timeStart = System.currentTimeMillis();
        pool.execute(fibonacci);
        //long wynik = (long) pool.invoke(fibonacci);
        while (!fibonacci.isCompletedNormally()){
            System.out.println("Aktywne wątki: " + pool.getActiveThreadCount());
            System.out.println("Licznik: " + pool.getQueuedTaskCount());
        }
        long wynik = (long) fibonacci.join();

        pool.shutdown();

        System.out.println(wynik);
    }

    public static int fibonacci_singleCore(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci_singleCore(n - 1) + fibonacci_singleCore(n - 2);
        }
    }
}
