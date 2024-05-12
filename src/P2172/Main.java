package P2172;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //思路：如果数组中存在1 1的个数是m个 操作数就是n - m （1将左右的数变成1）
    //如果数组中不存在1 就去找最短的区间m m内的数最大公约数是1 使用二分去查找最短区间  利用线段树
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
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
            int res = solve(arr);
            System.out.println(res);
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

    /*
    线段树 - 与区间有关 每个节点与一个区间存在联系 节点的值一般是该区间内题目所要求的结果
    假如总的区间为0-n
    根节点区间即为0-n
    左节点区间为0-n/2 右节点区间为 n/2+1-n
    左节点的左节点区间为0-n/2/2 左节点的右节点区间为n/2/2+1-n/2
    ......
    直到最后一层区间内的数只有一个
     */

    public static int solve(int[] nums) {//使用线段树
        int[] tree = new int[2 * nums.length];//线段树
        build(nums, tree, 0, 0, nums.length - 1);//构建线段树
        if (tree[0] != 1) {
            return -1;//如果0~n-1区间的最大公约数不是1 说明不存在区间的最大公约数为1 无法达到题目要求
        }
        return -1;//没写完暂时返回-1
    }

    /**
     * 通过递归生成线段树
     *
     * @param nums  数据数组
     * @param tree  存储线段树的数组 利用完全二叉树的规定 节点的值为该区间的最大公约数
     * @param index 当前节点下标
     * @param left  该节点的区间左边界
     * @param right 该节点区间的右边界
     */
    public static void build(int[] nums, int[] tree, int index, int left, int right) {
        if (left == right) {//区间内只存在一个值 意味着来到了叶子节点
            tree[index] = nums[left];//叶子节点的值就是数据本身
        } else {
            int mid = left + (right - left) / 2;//区间中点
            int l = 2 * index + 1;//当前节点左节点的下标
            int r = 2 * index + 2;//当前节点右节点下标
            build(nums, tree, l, left, mid);//递归去填写左子节点的值
            build(nums, tree, r, mid + 1, right);//递归去填写右子节点的值
            //填写当前节点的值 左区间的最大公约数和右区间的最大公约数再取个最大公约数
            tree[index] = gcd(tree[l], tree[r]);
        }
    }

    /**
     * 查询区间start-end的最大公约数
     *
     * @param tree  线段树数组
     * @param index 当前节点下标
     * @param left  当前节点线段树的区间左边界
     * @param right 当前节点线段树的区间右边界
     * @param start 查询区间的左边界
     * @param end   查询区间的右边界
     * @return 查询区间的最大公约数
     */
    public static int query(int[] tree, int index, int left, int right, int start, int end) {
        if (start <= left && end >= right) {//查询区间大于当前节点的区间
            //最终结果由当前区间的结果加上其他区间的结果整合得到
            return tree[index];//返回当前节点覆盖区间的结果
        }
        int mid = left + (right - left) / 2;//当前节点区间的中间点
        int l = 2 * index + 1;//左子节点下标
        int r = 2 * index + 2;//右子节点下标
        if (end <= mid) {//查询区间在左区间内
            return query(tree, l, left, mid, start, end);//在左区间查询;
        } else if (left > mid) {//查询区间在右区间内
            return query(tree, r, mid + 1, right, start, end);//在右区间查询
        }
        //两个区间都有一部分
        int leftVal = query(tree, l, left, mid, start, end);//在左区间查询
        int rightVal = query(tree, r, mid + 1, right, start, end);//在右区间查询
        return gcd(leftVal, rightVal);//返回两个区间最大公约数的最大公约数
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
