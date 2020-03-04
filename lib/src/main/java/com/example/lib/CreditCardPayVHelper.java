package com.example.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * desc：信用卡（visa/master）支付辅助类 <br/>
 * time: 17/2/9 下午5:49 <br/>
 * author：王冕 <br/>
 * since V 5.7 <br/>
 */
public class CreditCardPayVHelper {

    private final static Pattern VISA_CARD_PATTERN = Pattern.compile("(^4|^6304[7-9])[0-9]*$");
    private final static Pattern MASTER_CARD_PATTERN = Pattern.compile("^(5[1-5]|2(2(2[1-9]|[3-9])|[3-6]|7([0-1]|20))|57164)[0-9]*$");
    private final static Pattern DISCOVER_CARD_PATTERN = Pattern.compile("^(6(011|01300|22(1(2[6-9]|[3-9])|[2-8]|9([0-1]|2[0-5]))|4[4-9]|5)|^3(0([0-5]|95)|8|9))[0-9]*$");
    private final static Pattern AMERICAN_EXPRESS_PATTERN = Pattern.compile("^3(4|7|24000|37941|5(6900|6904))[0-9]*$");
    private final static Pattern JCB_PATTERN = Pattern.compile("(^1800|^2131|^35(2[8-9]|[3-8]|(?!6900)))[0-9]*$");

    /**
     * 获取卡类型
     *
     * @param cardNumber 卡号
     */
    public static String getCardType(String cardNumber) {
//        if (TextUtils.isEmpty(cardNumber)) return "UNKNOWN_CARD";
//        if ("SAR".equals(KeepMem.getInstance().initParam.getOrderCurType())) {
//            if (isMadaCard(cardNumber)) {
//                return "MADA";
//            }
//        }
        if (isVisaCard(cardNumber)) {
            return "VISA";
        } else if (isMasterCard(cardNumber)) {
            return "MASTERCARD";
        } else if (isDiscoverCard(cardNumber)) {
            return "DISCOVER";
//        } else if (isAmericanExpress(cardNumber)) {
//            return "AE";
        } else if (isJCBCard(cardNumber)) {
            return "JCB";
        }
        return "UNKNOWN_CARD";
    }

    /**
     * Luhn算法校验信用卡号
     *
     * @param cardNumber 信用卡号
     * @return true：  正确；
     */
    public static boolean newLuhn(String cardNumber) {
//        if (TextUtils.isEmpty(cardNumber)) {
//            return false;
//        }

        String cardNo = cardNumber.replaceAll("-", "");
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(cardNo).reverse().toString();
        for (int i = 0; i < reverse.length(); i++) {
            int digit = Character.digit(reverse.charAt(i), 10);
            if (i % 2 == 0) {//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            } else {//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if (digit >= 5) {
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }

    /**
     * VisaCard
     */
    private static boolean isVisaCard(String cardNumber) {
        Matcher m = VISA_CARD_PATTERN.matcher(cardNumber.replaceAll("-", ""));
        return m.matches();
    }

    /**
     * MasterCard
     */
    private static boolean isMasterCard(String cardNumber) {
        Matcher m = MASTER_CARD_PATTERN.matcher(cardNumber.replaceAll("-", ""));
        return m.matches();
    }

    /**
     * DiscoverCard
     */
    private static boolean isDiscoverCard(String cardNumber) {
        Matcher m = DISCOVER_CARD_PATTERN.matcher(cardNumber.replaceAll("-", ""));
        return m.matches();
    }

    /**
     * AmericanExpress
     */
    public static boolean isAmericanExpress(String cardNumber) {
        Matcher m = AMERICAN_EXPRESS_PATTERN.matcher(cardNumber.replaceAll("-", ""));
        return m.matches();
    }

    /**
     * JCBCard
     */
    private static boolean isJCBCard(String cardNumber) {
        Matcher m = JCB_PATTERN.matcher(cardNumber.replaceAll("-", ""));
        return m.matches();
    }

    /**
     * Mada
     *
     * @return
     */
    private static boolean isMadaCard(String cardNumber) {
//        if (TextUtils.isEmpty(cardNumber))
//            return false;
        cardNumber = cardNumber.replaceAll("-", "");
        if (cardNumber.length() < 6)
            return false;
        String[] aa = new String[]{"588845", "440647", "440795", "446404", "457865", "588846", "493428", "539931", "558848", "557606", "417633", "468540", "468541", "468542", "468543", "446393", "588847", "400861", "409201", "458456", "484783", "462220", "455708", "588848", "455036", "486094", "486095", "486096", "504300", "440533", "489317", "489318", "489319", "445564", "401757", "410685", "432328", "428671", "428672", "428673", "446672", "543357", "434107", "431361", "521076", "588850", "529415", "535825", "543085", "524130", "554180", "549760", "588849", "524514", "529741", "537767", "535989", "536023", "513213", "585265", "588983", "588982", "589005", "508160", "531095", "530906", "532013", "588851", "422817", "422818", "422819", "428331", "483010", "483011", "483012", "589206", "419593", "439956", "439954"};
        cardNumber = cardNumber.substring(0, 6);
        for (String a : aa) {
            if (a.equals(cardNumber))
                return true;
        }
        return false;
    }


}
