package P2168;

public class Main {
    public static void main(String[] args) {
        String src = "ncfjboqiealhkrpgd";
        String target = "aejcldbhpiogfqnkr";
        long all = factorial(17);
        long large = rank(src.toCharArray());
        long small = rank(target.toCharArray());
        System.out.println(all - large + small);
    }

    //利用康托展开计算一个排列在全排列中是第几大的数
    /*
        2143 在 {1,2,3,4}中是第几大的
        比2小的数有1个 1 * (n-1)!
        比1小的数有0个 0 * (n-2)!
        前两位为21 第三位比4小的数有1个 1 * (n-3)!
        前两位位214 第4位比3小的数有0个 0 * (n - 4)!
        未出现的数中比第i位小的数量 * (n-i)!
     */
    public static long rank(char[] str) {
        long res = 0;
        for (int i = 0; i < str.length; i++) {
            int temp = 0;
            for (int j = i + 1; j < str.length; j++) {
                if (str[j] < str[i]) {//统计未出现的数中比i小的数量
                    temp++;
                }
            }
            res += temp * factorial(str.length - i - 1);
        }
        return res;
    }

    public static long factorial(int target) {//计算阶乘
        long res = 1;
        for (int i = 2; i <= target; i++) {
            res *= i;
        }
        return res;
    }
}
