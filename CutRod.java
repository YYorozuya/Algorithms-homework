import java.util.Arrays;

public class CutRod {
    /**
     * 求钢条最优切割方案
     * @param p  钢条长度对应价格的数组
     * @param n  问题规模
     * @param s  保存切割方案的数组
     * @return  规模为n最优方案的结果
     */
    public static int cut(int[] p, int n, int[] s) {
        int[] r = new int[n+1];
        return memoized(p, n, r, s);
    }

    /**
     * 自顶向下带备忘录递归求解最优切割
     * @param p  钢条长度对应价格的数组
     * @param n  问题规模
     * @param r  保存下标为对应规模的问题结果的数组
     * @param s  保存切割方案的数组
     * @return  规模为n最优方案的结果
     */
    public static int memoized(int[] p, int n, int[] r, int[] s) {
        if (r[n] > 0)
            return r[n];
        if (n == 0)
            return 0;

        int q = 0; //规模为n的子问题的解
        for (int i = 1; i <= n ; i++) {
            int sub = p[i]+memoized(p, n-i, r, s); //p[i]与规模为n-i的子问题的解求和
            if (q < sub) {
                q = sub;
                s[n] = i; //记录最优的切割位置
            }
        }
        r[n] = q; //备忘
        return q;
    }

    public static void main(String[] args) {
        int[] p = {0,1,5,8,9,10,17,17,20,24,30};
        int[] s = new int[p.length];
        int n = 7;
        System.out.println(cut(p,n,s));
        while (n>0) {
            System.out.print(s[n]+" ");
            n = n-s[n];
        }
    }
}
