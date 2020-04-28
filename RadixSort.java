import java.util.Random;

public class RadixSort {
    //获得一个数的十进制位
    public static int getRadix(int x, int r) {
        return (int) ((x/Math.pow(10,r-1))%10);
    }


    /**
     * 计数排序（假设每个输入元素都在[0,k-1]之间, 比较每个输入元素的十进制第r位）
     * @param A 待排序的数组
     * @param k 数组中的元素的界限
     * @param r 按照第r位排序
     */
    public static int[] countingSort(int[] A, int k, int r) {
        int[] c = new int[k];
        int[] b = new int[A.length];

        for (int i : A)
            c[getRadix(i,r)]++;

        for (int i = 1; i < k; i++)
            c[i] = c[i] + c[i-1];

        for (int j = A.length-1; j >= 0; j--) {
            b[c[getRadix(A[j],r)]-1] = A[j]; //此处应注意要-1
            c[getRadix(A[j],r)]--;
        }
        return b;
    }


    /**
     * 基数排序
     * @param A 待排序的数组
     * @param d 数组中整数的最大位数
     */
    public static void sort(int[] A, int d) {

        for (int i = 1; i <= d; i++) {
            int[] b = countingSort(A,10,i);
            System.arraycopy(b, 0, A, 0, A.length);
        }
    }



    public static void main(String[] args) {
        Random random = new Random();
        int[] a = new int[30];

        for (int i = 0; i < 30; i++)
            a[i] = random.nextInt(10000);

        RadixSort.sort(a,4);
        for (int i: a) {
            System.out.print(i + " ");
        }
    }
}
