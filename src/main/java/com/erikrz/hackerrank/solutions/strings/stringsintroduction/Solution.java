package com.erikrz.hackerrank.solutions.strings.stringsintroduction;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        /* Enter your code here. Print output to STDOUT. */
        System.out.println(A.length() + B.length());
        int larger = A.compareTo(B);
        String largerString = (larger > 0) ? "Yes" : "No";
        System.out.println(largerString);
        A = A.replaceFirst(A.substring(0, 1), A.substring(0, 1).toUpperCase());
        B = B.replaceFirst(B.substring(0, 1), B.substring(0, 1).toUpperCase());
        System.out.println(A + " " + B);
    }
}




