package com.dergtr.seek.fun;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 交替循环打印
public class ThreadLoopPrint {


    private static volatile String target = "A";

    private static final Object lock = new Object();


    public static void main1(String[] args) {

        Thread t1 = new Thread(new PrintThread("A","B"));
        Thread t2 = new Thread(new PrintThread("B","C"));
        Thread t3 = new Thread(new PrintThread("C","D"));
        Thread t4 = new Thread(new PrintThread("D","A"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }


    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();
        Condition c4 = lock.newCondition();

        Thread t1 = new Thread(new LockPrintThread("A","B",lock,c1,c2));
        Thread t2 = new Thread(new LockPrintThread("B","C",lock,c2,c3));
        Thread t3 = new Thread(new LockPrintThread("C","D",lock,c3,c4));
        Thread t4 = new Thread(new LockPrintThread("D","A",lock,c4,c1));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }


    public static class LockPrintThread implements Runnable{

        private String current;
        private String next;
        private Lock lock;
        private Condition c;
        private Condition nextCondition;

        public LockPrintThread(String current, String next, Lock lock, Condition c, Condition nextCondition) {
            this.current = current;
            this.next = next;
            this.lock = lock;
            this.c = c;
            this.nextCondition = nextCondition;
        }

        @Override
        public void run() {
            try{
                for(int i = 0 ; i < 2 ; i++){
                    lock.lock();
                    while (!target.equals(current)){
                        c.await();
                    }

                    System.out.println(current);
                    target =next;
                    nextCondition.signal();
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static class PrintThread implements Runnable {

        private String current;
        private String next;

        public PrintThread(String current, String next) {
            this.current = current;
            this.next = next;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

                synchronized (lock) {
                    while (!target.equals(current)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(current);
                    target = next;
                    lock.notifyAll();
                }
            }
        }
    }


}
