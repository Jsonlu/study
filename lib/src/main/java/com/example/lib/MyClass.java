package com.example.lib;

import com.example.lib.lsb.ImageSteganography;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {

//    public static void main(String[] a){
//        try {
//            String aa = AES256Encryption.encrypt("abcd","JollyPayAESK!Q2wJollyPayAESK!Q2w","0000000000000000");
//            System.out.println(aa);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String... args) {
        create();
//        File imageFile = new File("1.png");
//        String hex = byte2Hex(File2byte(imageFile));
//        System.out.println(hex);

    }

    public static byte[] File2byte(File tradeFile) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    private static void create() {
        String key = "";
//
        ImageSteganography.toImg(key, "2.png");
        String a = ImageSteganography.fromImg("2.png");
        System.out.println(a);
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    private static boolean isMasterCard(String cardNumber) {
        Pattern MASTER_CARD_PATTERN = Pattern.compile("^(5[1-5]|2(2(2[1-9]|[3-9])|[3-6]|7([0-1]|20))|57164)[0-9]*$");
        Matcher m = MASTER_CARD_PATTERN.matcher(cardNumber.replaceAll("-", ""));
        return m.matches();
    }


    private static void aVoid() {
        String as = "SA4880000677608010071167";
        String strMod = as.substring(2, 4);
        String strCode = as.substring(0, 2);
        char xa = 'A';
        char[] xaa = strCode.toCharArray();
        strCode = (xaa[0] == xa) ? "10" : (xaa[0] - xa) + "";
        if (strCode.length() != 2) {
            strCode = "0" + strCode;
        }
        String strCode1 = (xaa[1] == xa) ? "10" : (xaa[1] - xa) + "";
        if (strCode1.length() != 2) {
            strCode1 = "0" + strCode1;
        }
        strCode += strCode1;

        System.out.println(strCode);

//        String a = as.substring(4, as.length()) + strCode;
        String a = "800006776080100711671810";

        String b = a + "00";

        System.out.println(b);
        BigDecimal aa = new BigDecimal(b);
        int mod = (aa.divideAndRemainder(BigDecimal.valueOf(97))[1]).intValue();

        mod = (97 + 1) - mod;
        System.out.println(mod);
        System.out.println(strMod);
    }
}
