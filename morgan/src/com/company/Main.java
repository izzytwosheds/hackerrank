package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        morgan(args);
    }

    private static void morgan(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            if (args != null && args.length > 0) {
                File file = new File(args[0]);
                if (file.exists()) {
                    scanner = new Scanner(file);
                }
            }

            File outFile = new File("/Users/izzat/Projects/hackerrank/Morgan/output12m.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);

            int T = scanner.nextInt();
            for (int t=0; t<T; t++) {
                String str1 = scanner.next();
                String str2 = scanner.next();

                int len1 = str1.length();
                int len2 = str2.length();
                StringBuilder stringBuilder = new StringBuilder(len1+len2);

                int pos1 = 0;
                int pos2 = 0;

                while (pos1 < len1 && pos2 < len2) {
                    int shift = 0;
                    while (pos1+shift<len1 && pos2+shift<len2
                            && str1.charAt(pos1+shift) == str2.charAt(pos2+shift)) {
                        shift++;
                    }

                    boolean pickFirst = true;
                    if (pos1+shift<len1 && pos2+shift<len2) {
                        // break the tie
                        pickFirst = str1.charAt(pos1+shift) < str2.charAt(pos2+shift);
                    } else {
                        // pick the string that still has letters in it
                        pickFirst = false;
                        shift--;
                    }

                    int p1 = pos1;
                    int p2 = pos2;
                    while (p1 <= pos1+shift && p2 <= pos2+shift) {
                        char ch1 = str1.charAt(p1);
                        char ch2 = str2.charAt(p2);
                        if (ch1 < ch2) {
                            stringBuilder.append(ch1);
                            p1++;
                        } else if (ch1 > ch2) {
                            stringBuilder.append(ch2);
                            p2++;
                        } else {
                            // use previous finding to break the tie
                            if (pickFirst) {
                                stringBuilder.append(ch1);
                                p1++;
                            } else {
                                stringBuilder.append(ch2);
                                p2++;
                            }
                        }
                    }

                    pos1 = p1;
                    pos2 = p2;
                }

                if (pos1 < len1) {
                    stringBuilder.append(str1.substring(pos1, len1));
                }

                if (pos2 < len2) {
                    stringBuilder.append(str2.substring(pos2, len2));
                }

                //System.out.println(stringBuilder.toString());
                fileOutputStream.write(stringBuilder.toString().getBytes());
                fileOutputStream.write('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
