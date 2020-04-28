public class Hanoi {
    /**
     * 解决指定规模的汉诺塔问题
     * @param n 问题的规模
     * @param from 圆盘所在石柱
     * @param buffer 圆盘移动到目标石柱的辅助石柱
     * @param to 圆盘移动的目标石柱
     */
    public static void move(int n, String from, String buffer, String to) {
        if (n == 1)
            System.out.println(from+"移动到"+to);
        else {
            move(n-1, from, to, buffer); //先将n-1个圆盘通过to从from移到buffer
            move(1, from, buffer, to); //将一个圆盘从from移到to
            move(n-1, buffer, from, to); //再将这n-1个圆盘通过from从buffer移到to
        }

    }

    public static void main(String[] args) {
        move(4,"石柱1","石柱2","石柱3");
    }
}
