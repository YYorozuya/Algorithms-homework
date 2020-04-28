import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BucketSort {

    /**
     * 桶排序
     * @param A 一个所有元素都在0-1之间的数组
     */
    public static void sort(List<Double> A) {
        List<List<Double>> buckets = new ArrayList<>();
        int n = buckets.size();
        for (int i = 0; i < A.size(); i++)
            buckets.add(new ArrayList<>()); //创建n个桶
        for (Double a : A)
            buckets.get((int)(a*n)).add(a); //将每个元素放在对应的桶里

        A.clear();

        for (List<Double> bucket : buckets) {
            Collections.sort(bucket); //使用Colleections的方法对每个桶的元素进行排序
            A.addAll(bucket); //将所有的桶中的元素顺序列出来
        }
    }


    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Double> a = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            a.add(rand.nextDouble());
        sort(a);
        System.out.println(a);
    }
}
