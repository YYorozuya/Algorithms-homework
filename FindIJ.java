class element{
    public element(int value, int pos) {
        this.value = value;
        this.pos = pos;
    }

    int value;
    int pos;
}

public class FindIJ {
    /**
     * 在数组A中找到两个相加结果为x的元素并打印这两个元素在数组中的位置
     * @param A 数组
     * @param x 相加结果
     */
    public static void find(int[] A, int x){
        element[] E = new element[A.length];
        for (int k = 0; k < A.length; k++) {
            E[k] = new element(A[k],k);
        }
        //排序
        InsertSort(E);
        int i = 0;
        int j = E.length-1;
        int found = 0;
        while (i<j && E[i].value<x/2){
            if (E[i].value + E[j].value>x)
                j--;
            else if (E[i].value + E[j].value<x)
                i++;
            else
            {
                found = 1;
                int p = j;
                while (E[i].value + E[p].value == x) {
                    System.out.println(E[i].pos + "," + E[j].pos);
                    p--;
                };
                i++;
            }
        }
        //未找到则打印-1,-1
        if (found == 0)
            System.out.println("-1,-1");
    }


    public static void InsertSort(element[] E){
        int i,j;
        element key;
        for(j=1; j < E.length; j++){
            key = E[j];
            i = j-1;
            while(i>=0 && E[i].value > key.value){
                E[i+1] = E[i];
                i--;
            }
            E[i+1] = key;
        }
    }

    public static void main(String[] args) {
        int[] a = {77,34,23,1,44,5,6,3,29,11,49};
        find(a,78);
    }
}
