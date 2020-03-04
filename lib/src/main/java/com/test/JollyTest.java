package com.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Author:JsonLu
 * DateTime:2019/10/12 18:37
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class JollyTest {

    static String v2 = "MIICXgIBAAKBgQCrW98l4bG+PRV/lF5KJ+jA7RO0mXqyigr2IxBPKRpqn3mLqBGR\n" +
            "zc6uqk8XbeOalftdcUiXKEgIzLKdPcIIrcT+5vXiFmD3Sjb0+TqXBW0U3HPdX0Ek\n" +
            "+7Wo0wy47WPK21wfHyI0PKygfdiwzNRM5nBNiHZ8ozbWhEnCF99V6GeaJwIDAQAB\n" +
            "AoGAfGrYOskEJPx0/4zaaT1Q4cNA64AEdl2EV/JdhRxy8beYqK3/E746stc1Ve2T\n" +
            "tEnxmIcIDbhRlRILHeAZYfPJc5mL5tZzH9oWGNO/2Gj/bxnygJU4Ma2pxhsipMzl\n" +
            "cQKVJoC3CJIBgyIBTHl7DBex2ZJF/LRCLdYRtI0DsEvcygECQQDhL+VLnwbuAYAt\n" +
            "/qzBmGUdKK67RSQtjC6F7U+MQNTvAVQIIG9wICYUCryTpLVJq0w5gpfcx8bLPvET\n" +
            "OOooKlCPAkEAws5r0l1zTUj7BlLrQBZSTPi/Ym5Hh/Ibwnzk5+G5R7zf+cDQoRxK\n" +
            "BI0k+O4kVl4iKyVlEHfUirvpIJTgC+s46QJBAKSODn4zSKoWGKgDyhRym7kyKHWa\n" +
            "8fQC02B06UNxgOjizJhfs295XroYmnM90mhyL/J6tyTULDYL2bkVRy9wtIsCQQC8\n" +
            "5aa91Ooibfsj9bGycjKZ+rayPpdUJptuaucYRtP/0REqQCLNpv3LDgZk758CtMLT\n" +
            "La6jWl8Zq/7yHgpm5E9JAkEApqmSw0CVncD8K+Nn9PqBbLyK1Dh1HLCJow+MmS41\n" +
            "7TAv6op8ThBb6NzVfYIQm+eh8/wctS8iyVgKpQl/ibpN8Q==";


    static String v3 = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKJVgHnl75w3Pecz7yZdkh4cSvSfQo/vIY6B3wrgJx2f0ouFyGsyiebNpciNSYYUMtU0hCGUtETztyyB6nQqaKnusvn7OWoq7pq6gzD3r6gfLFl3P3h/sVjwFM8Tgl4p/3gohQKlOyUgBqo39VsQWsj470EbT6xIVXkk9Ho6YpSDAgMBAAECgYEAm/A8gVxzBwI98dn+skESeuA81K4kN7XD62wwQPdAPAHqM+LouqkKtW75ASVP6n9+sTZIcyWw4ceoPxXxjXOro4Ij3gXCXB8B8kaeRJCC0CkykzFLygLdMjRgw/PMi6S+lPPJf5AekK7s/hrE8buSEdeGx80xihbm8t6EUaxfvEECQQDMClMmTEFvwH7Plp158uoLqKERPRFs0ophw3BUBQ++ESvjLYc35y5QKXr8pS+iyYCbVSS2hbmkOECi516RnJ9jAkEAy6xIRfVeoThPfERimg3bMhY2+Dum9U1/entqUZS05tlWPoZNG7kXlcsJOmXk9ItR1Xbyi+kHu08H7MTZ+dwQYQJAO/Q4tIMpoyWj3Wl7Qk8PWHenREJ2cUx4HvvncLjABHBVPf6twc5rPGDD46LCGYouAPaGwB8RCmEBFRz5G74y0wJBAIHDq2x/L6JyORpIi7XxXBWmV7GCeBZUIQNNfGir4TIviKZfdONT4zX53VBKuTwhQ6tIlLIVb2qkqLzukp6B2iECQQCgtpVsfsE1ofaY0+Cikg78hfVJFyIuoqMM6CZhTFAc2atV1hYuFcnDz557rr1sS24DA/iendux6xsWDOIEEr8D";


    public static void main(String... args) throws IOException {
        write();
        Coded1.add(v3, "tmp1.png");
        System.out.println(Coded1.read("tmp1.png").endsWith(v3));
    }

    public static void write(String... args) throws IOException {
        int width = 51, height = 51;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int r = 255, g = 0, b = 0;
        for (int w = 0; w < width; w++) {
            int rgb = new Color(r, g, b).getRGB();
            int imageNewValue = (rgb & 0xFFFFFFFE) | 1;
            for (int h = 0; h < height; h++)
                img.setRGB(w, h, imageNewValue);
        }
        ImageIO.write(img, "png", new File("tmp1.png"));
    }


}

