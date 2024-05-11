package P2170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int CONVERT = 1024;
    public static long gb = 0;
    public static long mb = 0;
    public static long kb = 0;
    public static long b = 0;

    public static void convert() {//换算
        kb += b / CONVERT;
        b %= CONVERT;
        mb += kb / CONVERT;
        kb %= CONVERT;
        gb += mb / CONVERT;
        mb %= CONVERT;
    }

    public static void solveArray(int size, String[] split) {//处理输入的数组的形式
        for (int i = 0; i < split.length; i++) {
            int start = split[i].indexOf("[");
            int end = split[i].indexOf("]");
            long nums = Long.parseLong(split[i].substring(start + 1, end));
            b += nums * size;
        }
        convert();
    }

    public static void solveString(String[] split) {//处理字符串
        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            int start = str.indexOf("\"");
            int end = str.substring(start + 1).indexOf("\"") + start;
            b += end - start;
        }
        convert();
    }

    public static void solveVar(int size, int nums) {//处理普通变量定义形式
        b += nums * size;
        convert();
    }

    public static void out() {//输出
        if (gb != 0) {
            System.out.print(gb + "GB");
        }
        if (mb != 0) {
            System.out.print(mb + "MB");
        }
        if (kb != 0) {
            System.out.print(kb + "KB");
        }
        if (b != 0) {
            System.out.print(b + "B");
        }
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());//T条语句
            for (int i = 0; i < T; i++) {
                String input = br.readLine();//输入数据
                if (input.charAt(0) == 'i') {//int
                    int index = input.indexOf(" ");//找到第一个空格的位置
                    String sub = input.substring(0, index);
                    if (sub.contains("[]")) {//数组
                        solveArray(4, input.substring(index + 1).split(","));
                    } else {
                        solveVar(4, input.split(",").length);
                    }
                } else if (input.charAt(0) == 'l') {//long
                    int index = input.indexOf(" ");//找到第一个空格的位置
                    String sub = input.substring(0, index);
                    if (sub.contains("[]")) {//数组
                        solveArray(8, input.substring(index + 1).split(","));
                    } else {
                        solveVar(8, input.split(",").length);
                    }
                } else {//string
                    solveString(input.substring(7).split(","));
                }
            }
            out();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
