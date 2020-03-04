package com.example.lib.lsb;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Author:JsonLu
 * DateTime:2019/9/24 14:53
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class ImageSteganography {
    /**
     * Save data into image.
     *
     * @param data      - the saved data
     * @param imagePath image path(only support png and jpg)
     * @return true: succeed false: failed
     */
    public static boolean toImg(String data, String imagePath) {
        File imageFile = new File(imagePath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imageFile);
            BufferedImage image = Utils.streamToImage(inputStream);

            final int imageLength = image.getHeight() * image.getWidth();
            final int startingOffset = Utils.calculateStartingOffset(null, imageLength);

            // hide text
            Steganography steganography = new Steganography();
            steganography.encode(image, data, startingOffset);
            return ImageIO.write(image, ImageUtils.getFileExt(imageFile), imageFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't find file " + imagePath, e);
        } catch (IOException | SteganographyException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


    /**
     * Get the data from image.
     *
     * @param imagePath image path.
     * @return String text
     */
    public static String fromImg(String imagePath) {
        File imageFile = new File(imagePath);
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(imageFile);
            BufferedImage image = Utils.streamToImage(inputStream);

            final int imageLength = image.getWidth() * image.getHeight();
            final int startingOffset = Utils.calculateStartingOffset(null, imageLength);

            Steganography steganography = new Steganography();
            return steganography.decode(image, startingOffset);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't find file " + imagePath, e);
        } catch (IOException | SteganographyException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


    private static String encryptDecrypt(String input) {
        char[] key = {'N', 'E', 'T', 'S', 'E', 'C', 'K', 'I', 'T'};
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }
        return output.toString();
    }

}

