public class FractionalKnapsack {


    /**
     * 贪心求解部分背包问题
     * @param v 物品的价值数组
     * @param w 物品的体积数组
     * @param W 背包的容量
     * @return 返回选择每个物品百分比的数组
     */
    public static double[] knapsackGreedy(int[] v, int[] w, int W) {
        int[] index = new int[v.length];
        //保存了物品索引的数组
        for (int i = 0; i < v.length; i++)
            index[i] = i;
        sort(index, v, w);

        double[] x = new double[v.length]; //保存每个物品在背包中的百分比

        int c = W; //背包剩余容量
        for (int i : index) {
            //装下w[i]
            if (w[i] < c) {
                x[i] = 1;
                c -= w[i];
            }
            //如果不能全部装下w[i]则将w[i]填满剩余背包
            else {
                x[i] = (double)c/w[i];
                break;
            }
        }
        return x;
    }

    /**
     * 按照商品的价值与体积之比从大到小进行插入排序
     * @param A 存放索引的数组
     * @param v 物品价格数组
     * @param w 物品体积数组
     */
    public static void sort(int[] A, int[] v, int[] w) {
        int i, j;
        int key;
        for (j = 1; j < A.length ; j++) {
            key = A[j];
            i = j-1;
            while (i>=0 && (double)(v[A[i]]/w[A[i]]) < (double)v[key]/w[key]) { //如果A[i]>key，则让key左移一位
                A[i+1] = A[i];
                i = i - 1;
            }
            A[i+1] = key;
        }
    }


    public static void main(String[] args) {
        int[] v = {1,6,18,22,28};
        int[] w = {1,2,5,6,7};
        int W = 11;
        double[] result = knapsackGreedy(v,w,11);
        for (double i : result)
            System.out.println(i);
    }
}
