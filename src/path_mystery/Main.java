package path_mystery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    //检查所有箭靶是否全部用完
    public static boolean check(int n, int[] row, int[] col) {
        for (int i = 0; i < n; i++) {
            if (row[i] != 0 || col[i] != 0) {
                return false;
            }
        }
        return true;
    }

    //使用dfs遍历地图
    public static boolean dfs(int n, int[][] map, int[] row, int[] col,
                              int i, int j, Stack<Integer> stack) {
        //判断上一步走的是否合理 越界
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return false;
        }
        if (row[j] < 1 || col[i] < 1 || map[i][j] == 1) {//走过了 或者 箭靶数目小于1 上一步走的不合理
            return false;
        }
        row[j]--;
        col[i]--;
        map[i][j] = 1;//标记为已经走过
        //上一步移动到当前位置 当前位置箭靶减1
        if (i == n - 1 && j == n - 1 && check(n, row, col)) {//走到了右下角
            stack.push(i * n + j);//将最后一个位置加入
            return true;
        }
        //尝试往四个方向走
        //记录从当前位置是否能到右下角
        boolean flag = dfs(n, map, row, col, i - 1, j, stack);//上
        if (!flag) {
            flag = dfs(n, map, row, col, i + 1, j, stack);//下
        }
        if (!flag) {
            flag = dfs(n, map, row, col, i, j - 1, stack);//左
        }
        if (!flag) {
            flag = dfs(n, map, row, col, i, j + 1, stack);//右
        }
        if (flag) {//如果从当前位置能够到达右下角
            stack.push(i * n + j);//将当前位置加入栈
        } else {
            //从当前位置走不通 加回来
            row[j]++;
            col[i]++;
            map[i][j] = 0;//标记为未访问
        }
        return flag;
    }


    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());//地图大小n*n
            int[] row = new int[n];//横向靶子
            int[] col = new int[n];//纵向靶子
            //数据输入
            String[] split = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                row[i] = Integer.parseInt(split[i]);
            }
            split = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                col[i] = Integer.parseInt(split[i]);
            }

            //处理 dfs 使用stack记录路径
            Stack<Integer> path = new Stack<>();
            dfs(n, new int[n][n], row, col, 0, 0, path);
            while (!path.isEmpty()) {
                Integer res = path.pop();
                System.out.print(res + " ");
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
