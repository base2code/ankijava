package de.base2code.ankijava.utils;

public class Utils {
    public static int sha1Start(String text) {
        // calc sha1
        String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(text);
        return Integer.parseInt(sha1.substring(0, 8), 16);
    }
}
