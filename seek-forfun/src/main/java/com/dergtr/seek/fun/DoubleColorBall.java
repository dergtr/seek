package com.dergtr.seek.fun;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DoubleColorBall {


    public static void main(String[] args) {


        int[] red = randomBall(6, 1, 33);
        int[] blue = randomBall(1, 1, 16);

        System.out.println(Arrays.toString(red));
        System.out.println(Arrays.toString(blue));


    }

    private static int[] randomBall(int targetCount, int min, int max) {
        return randomBall(targetCount, min, max, null);
    }

    private static int[] randomBall(int targetCount, int min, int max, Set<Integer> exclude) {
        Set<Integer> ballSet = new HashSet<>();
        Random random = new Random();
        while (ballSet.size() < targetCount) {
            int ballNum = random.nextInt(max - min + 1) + min;
            if (exclude == null || !exclude.contains(ballNum)) {
                ballSet.add(ballNum);
            }
        }
        int[] ball = new int[targetCount];
        int index = 0;
        for (Integer ballNum : ballSet) {
            ball[index] = ballNum;
            index++;
        }
        return ball;
    }


}
