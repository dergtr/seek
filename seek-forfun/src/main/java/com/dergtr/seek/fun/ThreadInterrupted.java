package com.dergtr.seek.fun;

public class ThreadInterrupted {


    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(()->{
            while (true){

                System.out.println("--- " + Thread.interrupted());

            }

        });

        t1.start();

        Thread.sleep(1000);

        System.out.println(t1.isInterrupted());
        System.out.println(t1.isInterrupted());

        t1.interrupt();

        System.out.println(t1.isInterrupted());
        System.out.println(t1.isInterrupted());


    }



}
