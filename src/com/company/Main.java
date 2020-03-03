package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        final Logic logic = new Logic();
        final DataStruct ds = new DataStruct();

        Thread t1 = new Thread(() -> logic.prepare(ds));
        Thread t2 = new Thread(() -> logic.prepare(ds));
        Thread t3 = new Thread(() -> logic.prepare(ds));

        t1.start();
        t2.start();
        t3.start();
    }
}
