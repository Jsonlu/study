package com.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Author:JsonLu
 * DateTime:2019/11/1 10:19
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Coded {

    public static String read(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedImage secretImg = ImageIO.read(new File(path));
        int width = secretImg.getWidth(), height = secretImg.getHeight();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                int rgb = secretImg.getRGB(w, h);
                builder.append(rgb & 1);
            }
        }
        String aaa = builder.toString();
        int size = width * height;
        byte[] vvv = new byte[size];
        for (int i = 0; i < size; i++) {
            String ab = aaa.substring(i * 8, i * 8 + 8);
            if ("11111111".equals(ab)) {
                byte[] b1 = new byte[i];
                System.arraycopy(vvv, 0, b1, 0, i);
                vvv = b1;
                break;
            }
            int a = Integer.parseInt(ab, 2);
            vvv[i] = (byte) a;
        }
        return new String(vvv);
    }

    public static String add(String v2, String path) throws IOException {
        int s = 0;
        File f = new File(path);
        BufferedImage secretImg = ImageIO.read(f);
        int width = secretImg.getWidth(), height = secretImg.getHeight();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                int rgb = secretImg.getRGB(w, h);
                int imageNewValue = (rgb & 0xFFFFFFFE);
                if (s != v2.length() * 8) {
                    int c = s % 8;
                    int d = s++ / 8;
                    char as = v2.charAt(d);
                    imageNewValue = imageNewValue | (as >> (7 - c) & 1);
                } else {
                    imageNewValue = imageNewValue | 1;
                }
                secretImg.setRGB(w, h, imageNewValue);

            }
        }
        ImageIO.write(secretImg, "png", f);
        return f.getPath();
    }
}
