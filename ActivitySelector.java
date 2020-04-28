import java.util.ArrayList;
import java.util.List;

public class ActivitySelector {


    /**
     * 获得一组活动的最大兼容活动子集,假定这组活动已经按结束时间从早到晚排序
     * @param s 保存活动开始时间的数组
     * @param f 保存活动结束时间的数组
     * @return 最大兼容活动子集中的活动编号集合（从0开始）
     */
    public static List<Integer> select(int[] s, int[] f) {
        int n = s.length;
        List<Integer> A = new ArrayList<>();
        A.add(0);
        //当前最早结束的活动编号
        int k = 0;
        for (int i = 1; i < n; i++)
            //如果活动i的开始时间晚于活动k的结束时间则将i加入到最大兼容活动子集中
            if (s[i] >= f[k]) {
                A.add(i);
                k = i;
            }
        return A;
    }


    public static void main(String[] args) {

        int[] s = {1,3,0,5,3,5,6,8,8,2,12};
        int[] f = {4,5,6,7,9,9,10,11,12,14,16};
        List<Integer> list = select(s,f);
        System.out.println(list);

    }
}
