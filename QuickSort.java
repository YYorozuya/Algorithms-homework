import java.util.Random;

public class QuickSort {

    static void exchange(int[] A, int a ,int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    /**
     * 对数组的一部分进行划分
     * @param A 待操作的数组
     * @param p 首元素位置
     * @param r 末尾元素位置
     * @return 划分之后pivot的位置
     */
    static int partition(int[] A, int p, int r) {
        Random rand = new Random();
        int k = p + rand.nextInt(r-p); //随机在p~r之间找一个pivot
        exchange(A, k, r);
        int x = A[r];
        int i = p - 1;
        //保持p~i的部分小于pivot, i~j的部分大于pivot
        for (int j = p; j < r; j++)
            if (A[j] < x)
                exchange(A, ++i, j);

        exchange(A, ++i, r);
        return i; //返回pivot的位置
    }


    /**
     * 快速排序
     * @param A 待排序数组
     * @param p 数组首元素
     * @param r 数组末尾元素
     */
    public static void sort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            sort(A, p, q-1);
            sort(A, q+1, r);
        }
    }


    public static void main(String[] args) {
        Random rand = new Random();
        int[] a = new int[50];
        for (int i = 0; i < 50; i++)
            a[i] = rand.nextInt(2000);
        QuickSort.sort(a,0,a.length-1);
        for (int i : a)
            System.out.print(i + " ");
    }

}
