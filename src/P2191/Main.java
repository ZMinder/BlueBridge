package P2191;

import java.util.Scanner;

/**
 * 这天，小明在整理他的卡牌。
 * <p>
 * 他一共有 n 种卡牌，第 i 种卡牌上印有正整数数 i(i ∈ [1, n])，且第 i 种卡牌 现有 ai 张。
 * <p>
 * 而如果有 n 张卡牌，其中每种卡牌各一张，那么这 n 张卡牌可以被称为一套牌。
 * 小明为了凑出尽可能多套牌，拿出了 m 张空白牌，他可以在上面写上数i，
 * 将其当做第 i 种牌来凑出套牌。然而小明觉得手写的牌不太美观，决定第 i 种牌最多手写 bi 张。
 * <p>
 * 请问小明最多能凑出多少套牌？
 *
 * @author 86176
 */
public class Main {
    public static class Data {
        public int remained;//种类
        public int cards;//张数

        @Override
        public String toString() {
            return "Data{" +
                    "remained=" + remained +
                    ", cards=" + cards +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//n种卡牌
        long m = scan.nextLong();//m张空白牌
        Data[] cards = new Data[n];//现有的牌
        for (int i = 0; i < n; i++) {
            cards[i] = new Data();
            cards[i].cards = scan.nextInt();
        }
        for (int i = 0; i < n; i++) {
            cards[i].remained = scan.nextInt();
        }
        long res = getRes(cards, n, m);
        System.out.println(res);
    }

    //二分去查找可以凑成多少套牌
    public static long getRes(Data[] cards, int n, long m) {
        long left = 0;
        long right = 4 * n;
        long res = 0;
        while (left <= right) {//二分去试探能凑出的最大套数
            long mid = left + (right - left) / 2;
            if (check(cards, mid, m)) {
                res = Math.max(res, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static boolean check(Data[] cards, long target, long m) {
        //要凑出target套 需要多少白板 然后与提供的白板数量对比
        long required = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].cards + cards[i].remained < target) {//凑不出来target
                return false;
            }
            if (cards[i].cards < target) {
                required += (target - cards[i].cards);
            }
        }
        return required <= m;
    }
}