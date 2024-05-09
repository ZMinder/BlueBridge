package join_root_plant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static class Union {
        HashMap<Integer, Integer> parent;//记录每个集合的根节点
        HashMap<Integer, Integer> size;//记录该头头率领的集合元素个数

        public Union(int n) {//初始化
            parent = new HashMap<>();
            size = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                parent.put(i, i);//一开始自己作为一个集合，根节点就是自己
                size.put(i, 1);//集合元素个数为1
            }
        }

        //寻找所在集合的根节点
        public int findHead(int node) {
            Stack<Integer> stack = new Stack<>();
            while (parent.get(node) != node) {//根节点的value就是自己
                stack.push(node);
                node = parent.get(node);//node指向自己的老大
            }
            while (!stack.isEmpty()) {//路径压缩
                Integer pop = stack.pop();
                parent.put(pop, node);
            }
            return node;//返回根节点
        }

        //判断两个元素是否在同一个集合
        public boolean isSameSet(int a, int b) {
            if (parent.containsKey(a) && parent.containsKey(b)) {
                return findHead(a) == findHead(b);//根据根节点是否一致来判断是否在同一个集合
            }
            return false;
        }

        //合并两个元素所在的集合
        public void merge(int a, int b) {
            //如果不在parent中 说明一开始初始化就没放进来
            if (!parent.containsKey(a) || !parent.containsKey(b)) {
                return;
            }
            int af = findHead(a);//a所在集合的根节点
            int bf = findHead(b);//b所在集合的根节点
            if (af != bf) {//不在一个集合才合并
                //集合数量少的节点认集合数量多的为根节点
                int large = size.get(af) > size.get(bf) ? af : bf;
                int small = large == af ? bf : af;
                //更新parent
                parent.put(small, large);
                //更新size
                size.put(large, size.get(small) + size.get(large));
                size.remove(small);
            }
        }
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String[] split = br.readLine().split(" ");
            int m = Integer.parseInt(split[0]);//行数
            int n = Integer.parseInt(split[1]);//列数
            int k = Integer.parseInt(br.readLine());//下面会输入k行
            Union union = new Union(m * n);
            for (int i = 0; i < k; i++) {
                split = br.readLine().split(" ");
                int a = Integer.parseInt(split[0]);
                int b = Integer.parseInt(split[1]);
                union.merge(a, b);//合并a、b所在的集合
            }
            System.out.println(union.size.size());//最后保留根节点的个数即为最初植物的数量
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
