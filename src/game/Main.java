package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    //求窗口最大值和最小值 分别计算累加和 相减*(n-k+1)即为结果
    public static double solve(int n, int k, int[] nums, int[] max, int[] min) {
        LinkedList<Integer> maxList = new LinkedList<>();//存储最大的
        LinkedList<Integer> minList = new LinkedList<>();//存储最小的
        long minSum = 0;
        long maxSum = 0;
        for (int right = 0; right < n; right++) {
            if (!maxList.isEmpty() && right - maxList.peekFirst() == k) {//窗口内达到了k个数
                maxList.pollFirst();//将最大值pop出来
            }
            if (!minList.isEmpty() && right - minList.peekFirst() == k) {//窗口内达到了k个数
                minList.pollFirst();//将最大值pop出来
            }
            int cur = nums[right];//当前要加入窗口的数
            while (!maxList.isEmpty() && nums[maxList.peekLast()] <= cur) {//将小于等于cur的pop出来
                maxList.pollLast();
            }
            while (!minList.isEmpty() && nums[minList.peekLast()] >= cur) {//将大于等于cur的pop出来
                minList.pollLast();
            }
            maxList.addLast(right);//将cur加入窗口
            minList.addLast(right);
            if (right >= k - 1) {//窗口结算
                max[right - k + 1] = maxList.peekFirst();//决定最大值
                min[right - k + 1] = minList.peekFirst();//决定最小值
                minSum += nums[minList.peekFirst()];
                maxSum += nums[maxList.peekFirst()];
            }
        }
        return (maxSum - minSum) / (double) (n - k + 1);
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]);//n个数
            int k = Integer.parseInt(split[1]);//窗口大小
            split = br.readLine().split(" ");
            int[] nums = new int[n];//存储n个数
            for (int i = 0; i < n; i++) {//读取数据
                nums[i] = Integer.parseInt(split[i]);
            }
            int[] max = new int[n - k + 1];//窗口最大值
            int[] min = new int[n - k + 1];//窗口最小值
            double res = solve(n, k, nums, max, min);
            System.out.printf("%.1f", res);
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
