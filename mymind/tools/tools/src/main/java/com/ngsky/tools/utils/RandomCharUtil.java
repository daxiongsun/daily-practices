package com.ngsky.tools.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * <dl>
 * <dt>RandomCharUtil</dt>
 * <dd>Description: 随机生成字符工具(包括中文、字符、英文、数字、特殊符号等)</dd>
 * <dd>CreateDate: 12/9/2018 3:52 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class RandomCharUtil {

    //随机数生成器
    private static Random random;
    //种子值
    private static long seed;

    //静态代码块，初始化种子值及随机数生成器
    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    //私有构造函数，禁止实例化
    private RandomCharUtil() {
    }

    /**
     * 设置种子值
     *
     * @param s 随机数生成器的种子值
     */
    public static void setSeed(long s) {
        seed = s;
        random = new Random(seed);
    }

    /**
     * 获取种子值
     *
     * @return long 随机数生成器的种子值
     */
    public static long getSeed() {
        return seed;
    }

    /**
     * 随机返回0到1之间的实数 [0,1)
     *
     * @return double 随机数
     */
    public static double uniform() {
        return random.nextDouble();
    }

    /**
     * 随机返回0到N-1之间的整数 [0,N)
     *
     * @param N 上限
     * @return int 随机数
     */
    public static int uniform(int N) {
        return random.nextInt(N);
    }

    /**
     * 随机返回0到1之间的实数 [0,1)
     *
     * @return double 随机数
     */
    public static double random() {
        return uniform();
    }

    /**
     * 随机返回a到b-1之间的整数 [a,b)
     *
     * @param a 下限
     * @param b 上限
     * @return int 随机数
     */
    public static int uniform(int a, int b) {
        return a + uniform(b - a);
    }

    /**
     * 随机返回a到b之间的实数
     *
     * @param a 下限
     * @param b 上限
     * @return double 随机数
     */
    public static double uniform(double a, double b) {
        return a + uniform() * (b - a);
    }

    /**
     * 返回随机字符串，同时包含数字、大小写字母
     *
     * @param len 字符串长度，不能小于3
     * @return String 随机字符串
     */
    public static String randomStr(int len) {
        if (len < 3) {
            throw new IllegalArgumentException("字符串长度不能小于3");
        }
        //数组，用于存放随机字符
        char[] chArr = new char[len];
        //为了保证必须包含数字、大小写字母
        chArr[0] = (char) ('0' + uniform(0, 10));
        chArr[1] = (char) ('A' + uniform(0, 26));
        chArr[2] = (char) ('a' + uniform(0, 26));


        char[] codes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '!', '~', '@', '#', '$', '%', '^', '&', '*', '(', ')', '[', ']', '{', '}', '+', '=', '-', '?', '<', '>', ';', '"'};
        //charArr[3..len-1]随机生成codes中的字符
        for (int i = 3; i < len; i++) {
            chArr[i] = codes[uniform(0, codes.length)];
        }

        //将数组chArr随机排序
        for (int i = 0; i < len; i++) {
            int r = i + uniform(len - i);
            char temp = chArr[i];
            chArr[i] = chArr[r];
            chArr[r] = temp;
        }

        return new String(chArr);
    }

    /**
     * 返回随机中文
     *
     * @param len 字符串长度
     * @return String 随机字符串
     */
    private static String randomChar(int len) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String str = "";
            int heightPos; //
            int lowPos;
            Random random = new Random();
            heightPos = (176 + Math.abs(random.nextInt(39)));
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(heightPos)).byteValue();
            b[1] = (Integer.valueOf(lowPos)).byteValue();
            try {
                str = new String(b, "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                System.out.println("错误");
            }
            ret.append(str);
        }
        return ret.toString();
    }

    /**
     * 返回随机中文
     *
     * @return String 随机字符串
     */
    private static char randomChar() {
        String str = "";
        int heightPos; //
        int lowPos;
        Random random = new Random();
        heightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(heightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str.charAt(0);
    }

    /**
     * 返回随机字符串
     *
     * @param isCzn   是否包含中文
     * @param isCen   是否包含英文
     * @param isCsign 是否包含符号
     * @return String 随机字符串
     */
    public static String randomAllStr(boolean isCzn, boolean isCen, boolean isCsign) {
        Random random = new Random();
        int len = Math.abs(random.nextInt(10));
        int znLen = Math.abs(random.nextInt(10));

        StringBuilder randomTmp = new StringBuilder();

        //数组，用于存放随机字符
        char[] chArr = new char[len];
        //为了保证必须包含数字、大小写字母

        char[] codes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '!', '~', '@', '#', '$', '%', '^', '&', '*', '(', ')', '[', ']', '{', '}', '+', '=', '-', '?', '<', '>', ';', '"'};
        //charArr[3..len-1]随机生成codes中的字符
        for (int i = 3; i < len; i++) {
            chArr[i] = codes[uniform(0, codes.length)];
        }

        //将数组chArr随机排序
        for (int i = 0; i < len; i++) {
            int r = i + uniform(len - i);
            char temp = chArr[i];
            chArr[i] = chArr[r];
            chArr[r] = temp;
            randomTmp.append(chArr).append(randomChar(znLen));
        }
        return randomTmp.toString();
    }

    /**
     * 单元测试
     * 运行： java RandomStr 4  (生成长度为4的字符串)
     */
    public static void main(String[] args) {

        String path = randomAllStr(false, false, false);
        System.out.println(path);
    }

}
