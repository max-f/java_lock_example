package com.company;

public class Logic {

    private static final int RECURSIVE_CALLS = 3;

    public Logic() {
        //NOP
    }

    public void prepare(final DataStruct ds) {
        ds.getLock().lock();
        try {
            post(RECURSIVE_CALLS, ds);
        } finally {
            ds.getLock().unlock();
        }
    }

    public void post(final int count, final DataStruct ds) {
        if (count == 0) {
            return;
        }

        final String threadName = Thread.currentThread().getName();
        final int holdCount = ds.getLock().getHoldCount();
        final int queueLength = ds.getLock().getQueueLength();

        System.out.println(String.format("Thread name: %s - Lock hold count: %d - Lock queue length: %d", threadName,
                holdCount, queueLength));

        post(count - 1, ds);
    }
}
