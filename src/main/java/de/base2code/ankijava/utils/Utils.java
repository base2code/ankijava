package de.base2code.ankijava.utils;

public class Utils {
    public static int sha1Start(String text) {
        // calc sha1
        String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(text);
        String sub = sha1.substring(0, 8);
        System.out.printf("sha1: %s\n", sub);
        return Integer.parseInt(sub, 16);
    }
}
