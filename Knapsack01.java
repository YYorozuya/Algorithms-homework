import java.util.Arrays;

public class Knapsack01 {
    /**
     * 自底向上求解01背包问题
     * @param v 商品的价值数组
     * @param w 商品的体积数组
     * @param W 背包的容量
     * @return 保存问题结果的二维数组
     */
    public static int[][] knapsackDP(int[] v, int[] w, int W) {
        int[][] c = new int[v.length+1][W+1];
        // 拿0件物品的子问题的解为0
        for (int i = 0; i <= W; i++)
            c[0][i] = 0;

        // 拿j件物品时的子问题的解
        for (int j = 1; j <= v.length; j++) {
            c[j][0] = 0; //背包容量为0的子问题的解为0
            //拿j件物品同时背包容量为k的子问题的解
           for (int k = 1; k <= W; k++) { //此循环中的w[j-1]与v[j-1]表示第j个物品的体积和价值
                if (w[j-1] <= k) {
                    c[j][k] = Math.max(c[j-1][k-w[j-1]] + v[j-1], c[j-1][k]);
                }
                else
                    c[j][k] = c[j-1][k];
           }
        }
        return c;
    }


    /**
     * 回溯法打印最优方案选择的物品
     * @param c 获得的结果数组
     * @param v 物品价值数组
     * @param w 物品体积数组
     */
    public static void printSolution(int[][] c, int[] v, int[] w) {
        int i = c.length-1;
        int j = c[0].length-1;
        while (i>0 && j>0) {
            if (j >= w[i-1] && c[i-1][j-w[i-1]]+v[i-1] > c[i-1][j]) { //w[i-1]与v[i-1]表示第i个物品的体积和价值
                System.out.println(i);
                j = j-w[i-1];
                i = i - 1;
            }
            else
                i--;
        }
    }

    public static void main(String[] args) {
        int[] v = {15,16,18,25,14};
        int[] w = {3,4,6,10,7};
        int W = 10;
        int[][] c = knapsackDP(v,w,W);
        System.out.println(c[v.length][W]);
        for(int i = 1; i <= v.length;i++) {
            for (int j = 1; j <= W; j++)
                System.out.print(c[i][j]+ " ");
            System.out.println();
        }
        printSolution(c,v,w);
    }


}
