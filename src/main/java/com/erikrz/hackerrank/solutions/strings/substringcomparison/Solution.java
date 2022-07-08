package com.erikrz.hackerrank.solutions.strings.substringcomparison;

import java.util.Scanner;

public class Solution {

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";

        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'
        smallest = "}";
        for (int i = 0; i < s.length() - k + 1; i++) {
            String temp = s.substring(i, i + k);
            int comparison = temp.compareTo(smallest);
            smallest = (comparison > 0) ? smallest : temp;
            comparison = temp.compareTo(largest);
            largest = (comparison > 0) ? temp : largest;
        }

        return smallest + "\n" + largest;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int k = scan.nextInt();
        scan.close();

        System.out.println(getSmallestAndLargest(s, k));
    }
}


