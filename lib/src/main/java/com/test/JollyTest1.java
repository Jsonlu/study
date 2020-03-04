package com.test;

import java.security.SecureRandom;

/**
 * Author:JsonLu
 * DateTime:2019/10/12 18:37
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class JollyTest1 {


    private static final char[] RANGE = {'!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\'', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'};

    public static void main(String... args) throws Exception {
        byte[] iv = getRandom();
        String ivs = new String(iv);
        System.out.println(ivs);
        byte[] aa = ivs.getBytes();
        System.out.println(ivs.length());
    }

    public static byte[] getRandom() {
        byte[] s = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(s);
//        for (int i = 0; i < 16; i++) {
//            s[i] = (byte) RANGE[random.nextInt(RANGE.length)];
//        }
        return s;
    }


}

