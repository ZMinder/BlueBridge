package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int MAX = 100005;
    public static int[] nums = new int[MAX];//存储n个数

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]);//n个数
            int k = Integer.parseInt(split[1]);//窗口大小
            split = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {//读取数据
                nums[i] = Integer.parseInt(split[i]);
            }
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
