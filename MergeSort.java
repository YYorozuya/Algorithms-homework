
import java.util.Random;

public class MergeSort {

    /**
     * 将p~q和q~r两个有序数组合并为一个数组
     * @param A 待操作的数组
     * @param p 部分数组的首元素位置
     * @param q 中点元素位置
     * @param r 末尾元素位置
     */
    public static void merge(int[] A, int p, int q, int r) {
        int n1 = q-p+1;
        int n2 = r-q;
        int[] L = new int[n1];
        int[] R = new int[n2];

        if (n1 >= 0)
            System.arraycopy(A, p, L, 0, n1); //将数组A中p~q的部分拷贝到L

        if (n2 >= 0)
            System.arraycopy(A, q+1, R, 0, n2); //q+1~r的部分拷贝到R

        int k = p;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                A[k++] = L[i++];
            else
                A[k++] = R[j++];
        }

        while (i < n1) //将剩下的部分拷贝到合并的序列中
            A[k++] = L[i++];
        while (j < n2)
            A[k++] = R[j++];
    }


    /**
     * 归并排序
     * @param A 待排序数组
     * @param p 数组首元素位置
     * @param r 数组末尾元素位置
     */
    public static void sort(int[] A, int p, int r) {
        if (p < r) {
            int q = (p+r)/2;
            sort(A, p, q);
            sort(A, q+1, r);
            merge(A, p, q, r);
        }
    }


    public static void main(String[] args) {
        Random rand = new Random();
        int[] a = new int[20];
        for (int i = 0; i < 20; i++)
            a[i] = rand.nextInt(200);

        sort(a, 0, a.length-1);

        for (int i: a)
            System.out.println(i);

    }
}
