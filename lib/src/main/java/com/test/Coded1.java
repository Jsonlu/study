package com.test;

import java.awt.Color;
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
public class Coded1 {

    public static String read(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedImage secretImg = ImageIO.read(new File(path));
        int width = secretImg.getWidth(), height = secretImg.getHeight();
        int s = 0;
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                int rgb = secretImg.getRGB(w, h);
                builder.append((((rgb & 0xFF0000) >> 16) & 1));
                builder.append((((rgb & 0xFF00) >> 8) & 1));
                if (++s % 3 != 0)
                    builder.append((((rgb & 0xFF)) & 1));
            }
        }
        String aaa = builder.toString();
        int size = width * height;
        byte[] vvv = new byte[size];
        for (int i = 0; i < size; i++) {
            String ab = builder.substring(i * 8, i * 8 + 8);
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

                int red = (rgb & 0xFF0000) >> 16;
                int green = (rgb & 0xFF00) >> 8;
                int blue = (rgb & 0xFF);
                if (s != v2.length() * 3) {
                    int c = s % 3;
                    int d = s++ / 3;
                    char as = v2.charAt(d);
                    red = (red & 0xFE) | (as >> (7 - c * 3) & 1);
                    green = (green & 0xFE) | (as >> (6 - c * 3) & 1);
                    blue = (blue & 0xFE) | (as >> (5 - c * 3) & 1);
                } else {
                    red = (red & 0xFE) | 1;
                    green = (green & 0xFE) | 1;
                    blue = (blue & 0xFE) | 1;
                }
                Color color = new Color(red, green, blue);

                secretImg.setRGB(w, h, color.getRGB());

            }
        }
        ImageIO.write(secretImg, "png", f);
        return f.getPath();
    }
}
