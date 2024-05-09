package path_mystery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //使用dfs遍历地图
    public static void dfs(int[][] map, int[] row, int[] col) {

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

            //处理 dfs
            int[][] map = new int[n][n];//地图信息 1表示路径
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
