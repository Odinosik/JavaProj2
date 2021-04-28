package com.company;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask {
    final long n;

    FibonacciTask(long n) {
        this.n = n;
    }

    public Long compute() {

        if(n<=10){
            return do_fibonacci(n);
        }
        ForkJoinTask<Long> subTask1 = new FibonacciTask(n-1).fork();
        ForkJoinTask<Long> subTask2 = new FibonacciTask(n-2).fork();
        //invokeAll(subTask1,subTask2);
        return subTask1.join() + subTask2.join();
    }

    static long do_fibonacci(long n) {
        if ( n<=1 ) return n;
        return do_fibonacci(n-1) + do_fibonacci(n-2);
    }
}
