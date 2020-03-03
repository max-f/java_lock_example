package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class DataStruct {

    private ReentrantLock lock;

    public DataStruct() {
        lock = new ReentrantLock();
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
