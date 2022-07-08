package com.erikrz.hackerrank.solutions.strings.tagcontentextractor;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while (testCases > 0) {
            String line = in.nextLine();

            //Write your code here
            int current = 0;
            boolean valid = false;
            for (int position = 0; position < line.length(); position++) {
                int tagStart = line.indexOf("<", current);
                if (tagStart < 0) {
                    break;
                }
                int tagEnd = line.indexOf(">", tagStart);
                if (tagEnd < 0) {
                    break;
                }
                String tag = line.substring(tagStart + 1, tagEnd);

                if (tag.length() == 0 || tag.charAt(0) == '/') {
                    current = tagEnd + 1;
                    continue;
                }
                int closingTag = line.indexOf("</" + tag + ">");
                if (closingTag >= 1) {
                    String candidate = line.substring(tagEnd + 1, closingTag);
                    if (candidate.length() > 0 && !candidate.contains("<")) {
                        System.out.println(candidate);
                        valid = true;
                    }
                }
                current = tagEnd + 1;
            }
            if (!valid) {
                System.out.println("None");
            }

            testCases--;
        }
    }
}



