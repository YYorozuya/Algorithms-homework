import java.util.Random;

public class HeapSort {

    private static int left(int i){
        return 2*i+1;
    }
    private static int right(int i){
        return 2*i+2;
    }
    private static int parent(int i){
        return (i-1)/2;
    }

    static void exchange(int[] A, int a ,int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    /**
     * 使下标为i的根节点重新遵循最大堆的性质
     * @param A 保存堆的数组
     * @param i 根节点下标
     * @param heapsize 堆大小
     */
    static void maxHeapify(int[] A, int i, int heapsize){
        int l = left(i); //左节点
        int r = right(i); //右节点
        int largest; //找三个节点的最大值
        if (l<=heapsize-1 && A[l]>A[i])
            largest = l;
        else
            largest = i;
        if (r<=heapsize-1 && A[r]>A[largest])
            largest = r;
        if (largest != i){
            exchange(A, i, largest);
            maxHeapify(A, largest, heapsize);
        }
    }

    /**
     * 堆排序
     * @param A 待排序的数组
     */
    public static void sort(int[] A){
        //建立一个大顶堆
        int heapsize = A.length;
        for (int j = parent(A.length-1); j >=0 ; j--)
            maxHeapify(A, j, heapsize);

        //去掉堆顶元素并将其放在堆后面的第一个位置
        for (int i = A.length-1; i > 0 ; i--) {
            exchange(A, 0, i);
            heapsize--;
            maxHeapify(A, 0, heapsize);
        }

    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] a = new int[20];
        for (int i = 0; i < 20; i++)
            a[i] = rand.nextInt(200);
        sort(a);
        for (int i : a)
            System.out.print(i+" ");
    }

}
