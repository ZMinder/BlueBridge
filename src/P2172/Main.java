package P2172;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    //思路：如果数组中存在1 1的个数是m个 操作数就是n - m （1将左右的数变成1）
    //如果数组中不存在1 就去找最短的区间m m内的数最大公约数是1 使用二分去查找最短区间
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] split = br.readLine().split(" ");
        int nums = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(split[i]);
            nums += arr[i] == 1 ? 1 : 0;//统计数组中1的个数
        }
        if (nums > 0) {//数组中出现了1 每次将1周围的变成1
            System.out.println(n - nums);
        }

    }

    public static int solve(int[] nums) {//使用线段树

    }

    public static boolean check(int[] nums, int left, int right) {//判断一个区间内的最大公约数是否为1
        int gcd = gcd(nums[left], nums[left + 1]);
        for (int i = left + 2; i <= right && gcd != 1; i++) {
            gcd = gcd(gcd, nums[i]);
        }
        return gcd == 1;
    }

    public static int gcd(int a, int b) {//辗转相除法求最大公约数
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
