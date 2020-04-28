public class MatrixChainOrder {

    /**
     * 自底向上求解矩阵链乘法问题
     * @param p 给定n个矩阵的链，矩阵Ai的规模为p[i-1]*p[i]
     * @param s 记录加括号的位置，s的维度应为p.length
     * @return  完成p的链乘需要的最小代价
     */
    public static int order(int[] p, int[][] s) {
        int n = p.length-1; //数组p记录了n个矩阵的信息
        int[][] m = new int[n+1][n+1]; //保存计算代价的二维数组，这里为了与数组p中的i对应，数组的维度比书中的大1，m的下标也从1开始

        //单一矩阵相乘代价为0
        for (int i = 1; i <= n; i++)
            m[i][i] = 0;

        //矩阵链长度为2~n-1的子问题
        for (int l = 2; l <= n; l++) {
            //求解每一个长度为l的子问题
            for (int i = 1; i <= n-l+1; i++) {
                //i为该子问题矩阵链的第一个矩阵，j为该子问题矩阵链的最后一个矩阵
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE; //先设定为最大值
                //求解在位置k处加括号的方案
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        return m[1][n];
    }


    /**
     * 打印最优的方案
     * @param s 调用order得到的二维数组结果
     * @param i 子问题的第一个矩阵位置
     * @param j 子问题的最后一个矩阵位置
     */
    public static void printOptimal(int[][] s, int i, int j) {
        if (i == j)
            System.out.print("A"+i);
        else {
            System.out.print("(");
            printOptimal(s, i, s[i][j]);
            printOptimal(s, s[i][j]+1, j);
            System.out.print(")");
        }
    }


    public static void main(String[] args) {
        int[] p = {30,35,15,5,10,20,25};
        int[][] s = new int[p.length][p.length];

        System.out.println(order(p,s));
        printOptimal(s,1,6);
    }
}
