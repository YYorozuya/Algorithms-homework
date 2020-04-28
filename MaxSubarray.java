public class MaxSubarray {

    /**
     * 获得包含数组中点元素的最大子数组的和
     * @param A 数组
     * @param low 最小位置
     * @param mid 中点位置
     * @param high 最大位置
     * @return [low..high]中包含中点的最大子数组的和
     */
    public static int[] maxCrossing(int[] A, int low, int mid, int high) {
        int maxLeft = 0, maxRight = 0;
        int leftSum = Integer.MIN_VALUE; //设定一个最小值
        int sum = 0;
        //找到A[i..mid]的最大值
        for (int i = mid; i >= low ; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = Integer.MIN_VALUE; //设定一个最小值
        sum = 0;
        //找到A[mid+1..high]的最大值
        for (int j = mid+1; j <= high; j++) {
            sum += A[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        int[] result = new int[3];
        result[0] = maxLeft;
        result[1] = maxRight;
        result[2] = leftSum + rightSum;

        return result;
    }


    /**
     * 求解最大子数组
     * @param A 待求解的数组
     * @param low 首元素位置
     * @param high 末尾元素位置
     * @return 数组[low..high]的最大子数组的结果
     */
    public static int[] find(int[] A, int low, int high) {
        int[] result = new int[3];
        if (low == high) {
            result[0] = low;
            result[1] = high;
            result[2] = A[low];
            return result;
        }
        else {
            int mid = (low+high)/2;
            int[] leftResult = find(A, low, mid);
            int[] rightResult = find(A, mid+1, high);
            int[] crossResult = maxCrossing(A, low, mid, high);
            //找三个结果的最大值
            if (leftResult[2] > rightResult[2] && leftResult[2] > crossResult[2])
                return leftResult;
            else if (rightResult[2] > leftResult[2] && rightResult[2] > crossResult[2])
                return rightResult;
            else
                return crossResult;

        }
    }

    public static void main(String[] args) {
        int[] a = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        int[] result = find(a,0,a.length-1);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }


}
