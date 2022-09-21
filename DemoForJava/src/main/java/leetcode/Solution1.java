package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author sunny
 */
public class Solution1 {

    public static void main(String[] args) {
        int[][] a = new int[2][2];
        Arrays.sort(a, Comparator.comparingInt(item -> item[0]));
    }

}
