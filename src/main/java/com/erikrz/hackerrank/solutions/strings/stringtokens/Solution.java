package com.erikrz.hackerrank.solutions.strings.stringtokens;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        // Write your code here.
        if (s.trim().length() == 0) {
            System.out.println(0);
            return;
        }

        String[] words = s.trim().split("[^A-Za-z]+");
        System.out.println(words.length);
        for (String word : words) {
            System.out.println(word);
        }
        scan.close();
    }
}


