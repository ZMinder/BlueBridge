package P2167;

import org.junit.Test;

public class Main {
    public static void main(String[] args) {
//        int n = 14;
//        long[] solve = solve(n);
//        long res = solve[n];//未分配到的
//        res *= combine(n * 2, n);
//        System.out.println(res);
        System.out.println(1286583532342313400l);
    }

    //检查是否每个小朋友分配到的不是自己的钥匙
    public static boolean check(int n, int[] nums) {
        for (int i = 0; i < n; i++) {
            if (nums[i] == i) {
                return false;
            }
        }
        return true;
    }

    //在前面已经放好的基础上，后续可分配的种数
    public static long dfs(int n, int i, boolean[] used) {
        if (i == n) {
            return 1;
        }
        long res = 0;
        for (int j = 0; j < n; j++) {
            if (j != i && !used[j]) {//当前钥匙不是我的，并且没分配过
                used[j] = true;//表示钥匙被使用
                res += dfs(n, i + 1, used);//决定下一位小朋友
                used[j] = false;//j布分配给我了 置为false
            }
        }
        return res;
    }

    public static long[] solve(int n) {
        long[] used = new long[n + 1];//i个人均为分配到自己钥匙的方法数
        for (int i = 1; i <= n; i++) {
            used[i] = ways(i, used);
        }
        return used;
    }

    public static long combine(int m, int n) {//计算组合数
        long res = 1;
        for (int i = 0; i < n; i++) {
            res *= m - i;
        }
        for (int i = 0; i < n; i++) {
            res /= i + 1;
        }
        return res;
    }

    public static long ways(int n, long[] used) {//计算n个人搜分配不到自己钥匙的方法数
        //总分配数-1个人分配到了 - 2个人分配到了-...
        long sum = 1;
        for (int i = 2; i <= n; i++) {
            sum *= i;
        }
        long res = 1;//所有人都分派到了自己的钥匙
        for (int i = 1; i < n - 1; i++) {
            long k = combine(n, i);
            res += k * used[(n - i)];
        }
        sum -= res;
        return sum;
    }


    @Test
    public void test() {
        int n = 14;
        for (int i = 0; i <= n; i++) {
            System.out.println(combine(n, i));
        }
    }
}
