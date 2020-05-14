package com.inteagle.tcpserver.util;

import javax.xml.bind.DatatypeConverter;

public class ByteHexUtil {

    public static byte[] stringToByteArr(String str) {
        byte[] byteArray = str.getBytes();
        return byteArray;
    }

    /**
     * byte[]数组转换为16进制的字符串
     *
     * @param bytes 要转换的字节数组
     * @return 转换后的结果
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 十六进制转换字符串
     *
     * @param s Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff
                        & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 功能描述:hex串转为byte
     *
     * @param hexStr
     * @return byte[]
     * @author IVAn
     * @date 2020/1/16 15:36
     */
    public static byte[] hexStr2Bytes(String hexStr) {
        if (null == hexStr || hexStr.length() < 1) {
            return null;
        }
        int byteLen = hexStr.length() / 2;
        byte[] result = new byte[byteLen];
        char[] hexChar = hexStr.toCharArray();
        for (int i = 0; i < byteLen; i++) {
            result[i] = (byte) (Character.digit(hexChar[i * 2], 16) << 4
                    | Character.digit(hexChar[i * 2 + 1], 16));
        }
        return result;
    }


    /**
     * 16进制转换成为string类型字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 字符串转化成为16进制字符串
     * @param s
     * @return
     */
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    public static void main(String[] args) {
        String hexStr = "68656c6c6f";
        System.out.println("ret----" + strTo16(hexStr));
    }

}
