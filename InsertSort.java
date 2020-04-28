import java.util.Random;

public class InsertSort {

    /**
     * 插入排序
     * @param A 待排序的数组
     */
    public static void sort(int[] A) {
        int i, j;
        int key;
        for (j = 1; j < A.length ; j++) {
            key = A[j];
            i = j-1;
            while (i>=0 && A[i]>key) { //如果A[i]>key，则让key左移一位
                A[i+1] = A[i];
                i = i - 1;
            }
            A[i+1] = key;
        }
    }


    public static void main(String[] args) {
        Random rand = new Random();
        int[] a = new int[20];
        for (int i = 0; i < 20; i++)
            a[i] = rand.nextInt(200);
        sort(a);
        for (int i: a)
            System.out.print(i+" ");
    }
}
