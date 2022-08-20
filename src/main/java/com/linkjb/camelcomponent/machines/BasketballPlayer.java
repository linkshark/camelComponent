package com.linkjb.camelcomponent.machines;

/**
 * @ClassName BasketballPlayer
 * @Description TODO
 * @Author shark
 * @Data 2022/7/14 16:32
 **/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasketballPlayer {
    public static List<Integer> nums = new ArrayList<>();

    public static void main(String[] args) {
        int[] players = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        for (int player : players) {
            nums.add(player);
            sum += player;
        }
        Collections.sort(nums);
        List<Integer> A = new ArrayList<>();
        int countA = 0;
        List<Integer> B = new ArrayList<>();
        int countB = 0;
        while (nums.size() > 0) {
            int index = findMostNearAverage(sum / nums.size());
            if (A.size() > B.size() || (A.size() == B.size() && countA > countB)) {
                B.add(nums.get(index));
                countB += nums.get(index);
            } else {
                A.add(nums.get(index));
                countA += nums.get(index);
            }
            nums.remove(index);
        }

        System.out.println(Math.abs(sum - countA * 2));
    }

    private static int findMostNearAverage(int average) {
        int index = 0;
        int minDelta = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            if (Math.abs(average - nums.get(i)) < minDelta) {
                minDelta = Math.abs(average - nums.get(i));
                index = i;
            }
        }
        return index;
    }

}