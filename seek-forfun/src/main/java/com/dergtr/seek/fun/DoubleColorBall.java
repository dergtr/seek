package com.dergtr.seek.fun;

import java.util.*;

public class DoubleColorBall {


    public static void main(String[] args) {
        int[] red = randomBall(6, 1, 33);
        int[] blue = randomBall(1, 1, 16);
        System.out.println(Arrays.toString(red));
        System.out.println(Arrays.toString(blue));
    }

    private static int[] randomBall(int targetCount, int min, int max) {
        int[] ballPool = new int[max - min + 1];
        for (int i = 0; i < ballPool.length; i++) {
            ballPool[i] = min + i;
        }
        int[] ball = new int[targetCount];
        Random random = new Random();
        for (int i = 0; i < targetCount; i++) {
            int ballIndex = random.nextInt(ballPool.length);
            ball[i] = ballPool[ballIndex];
            System.arraycopy(ballPool, ballIndex + 1, ballPool, ballIndex, ballPool.length - ballIndex - 1);
            ballPool = Arrays.copyOf(ballPool, ballPool.length - 1);
        }
        Arrays.sort(ball);
        return ball;
    }


}
