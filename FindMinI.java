public class FindMinI {


    /**
     * 找到一个数组第i小的数
     * @param A
     * @param i
     * @return 返回数组A第i小的数
     */
    public static int find(int[] A, int i){
        int heapsize = A.length;
        for (int j = (A.length-2)/2; j >= 0 ; j--)
            minheapify(A,j,heapsize);
        int key = A.length-i;
        for (int k = A.length-1; k >= key ; k--) {
            exchange(A,0,k);
            heapsize--;
            minheapify(A,0,heapsize);
        }
        return A[key];
    }

    public static void minheapify(int[] A, int i, int heapsize){
        int l = 2*i+1;
        int r = 2*i+2;
        int smallest;
        if (l<=heapsize-1 && A[l]<A[i])
            smallest = l;
        else
            smallest = i;
        if (r<=heapsize-1 && A[r]<A[smallest])
            smallest = r;
        if (smallest != i){
            exchange(A,i,smallest);
            minheapify(A,smallest,heapsize);
        }

    }


    public static void exchange(int[] arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void main(String[] args) {
        int[] a = {77,34,23,1,44,5,6,3,29,11,49};
        System.out.println(find(a,1));
        System.out.println(find(a,2));
        System.out.println(find(a,3));
        System.out.println(find(a,4));
        System.out.println(find(a,11));
    }
}


