package com.erikrz.hackerrank.solutions.introduction.ifelse;

import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String ans;
        if (n % 2 == 1 || (n >= 6 && n <= 20)) {
            ans = "Weird";
        } else {
            ans = "Not Weird";
        }
        System.out.println(ans);
        scanner.close();
    }
}

