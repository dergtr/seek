package com.dergtr.seek.fun;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {


    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();


    public static void main(String[] args) {


        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String message = "producer" + i;
                    System.out.println("生产消息：" + message);
                    queue.put(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread consumer = new Thread(() -> {
            try {
                while (true){
                    System.out.println("消费消息:" + queue.take());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();;
        consumer.start();

    }

}
