package com.erikrz.hackerrank.solutions.introduction.endoffile;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        long n = 0;
        while (scanner.hasNext()) {
            n += 1;
            System.out.println(n + " " + scanner.nextLine());
        }
    }
}
