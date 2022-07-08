package com.erikrz.hackerrank.solutions.strings.anagrams;


import java.util.Scanner;

public class Solution {

    static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        char[] charsA = a.toUpperCase().toCharArray();
        java.util.Arrays.sort(charsA);
        String sortedA = new String(charsA);

        char[] charsB = b.toUpperCase().toCharArray();
        java.util.Arrays.sort(charsB);
        String sortedB = new String(charsB);

        return sortedA.equals(sortedB);
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
