package com.test;

import java.io.IOException;

/**
 * Author:JsonLu
 * DateTime:2019/11/5 14:46
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Main {
    public static void main(String... args) {
        int size = args.length;
        if (size > 1) {
            String type = args[0];
            if ("0".equals(type) && size >= 2) {
                String path = args[1];
                try {
                    String a = Coded.read(path);
                    System.out.println(a);
                } catch (IOException e) {
                    System.err.println("file is not exist!");
                }
            } else if ("1".equals(type) && size >= 3) {
                String path = args[1];
                String content = args[2];
                try {
                    Coded.add(content, path);
                } catch (IOException e) {
                    System.err.println("file is not exist!");
                }
            } else {
                System.err.println("Illegal parameter");
            }
        } else {
            System.err.println("Illegal parameter");
        }
    }
}
