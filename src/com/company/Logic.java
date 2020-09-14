package com.company;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Logic {

    private static final int RECURSIVE_CALLS = 3;

    public Logic() {
        //NOP
    }

    public void prepare(final DataStruct ds) {
        ds.getLock().lock();
        try {
//            postSync(RECURSIVE_CALLS, ds);
            postAsync(RECURSIVE_CALLS, ds);
        } finally {
            ds.getLock().unlock();
        }
    }

    public void postSync(final int count, final DataStruct ds) {
        if (count == 0) {
            return;
        }

        final String threadName = Thread.currentThread().getName();
        final int holdCount = ds.getLock().getHoldCount();
        final int queueLength = ds.getLock().getQueueLength();

        waitForMe();
        System.out.println(String.format("Thread name: %s - Lock hold count: %d - Lock queue length: %d", threadName,
                                         holdCount, queueLength));

        postSync(count - 1, ds);
    }


    public void postAsync(final int count, final DataStruct ds) {
        if (count == 0) {
            return;
        }

        final String threadName = Thread.currentThread().getName();
        final int holdCount = ds.getLock().getHoldCount();
        final int queueLength = ds.getLock().getQueueLength();

//        final ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> waitForMe());

        System.out.println(String.format("Thread name: %s - Lock hold count: %d - Lock queue length: %d", threadName,
                                         holdCount, queueLength));

        postAsync(count - 1, ds);
    }


    public String waitForMe() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //NOP
        }
        return "test";
    }

}
