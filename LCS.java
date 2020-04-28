public class LCS {

    /**
     * 自底向上计算最大公共子序列的长度
     * @param X 序列X
     * @param Y 序列Y
     * @param b 传入数组用来保存LCS的构造结果
     * @return 最大公共子序列的长度
     */
    public static int lengthOfLCS(String X, String Y, char[][] b) {
        char[] x = X.toCharArray();
        char[] y = Y.toCharArray();

        int m = x.length;
        int n = y.length;
        int[][] c = new int[m+1][n+1]; //c[i][j]保存Xi和Yj的LCS的长度

        //长度为0的字符串与其它字符串的最大公共子序列长度为0
        for (int i = 1; i <= m; i++)
            c[i][0] = 0;
        for (int j = 0; j <=n ; j++)
            c[0][j] = 0;

        //求解每一个c[i][j]的值
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i-1] == x[j-1]) { //当字符串X的第i个元素与串Y的第j个元素相等时
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = '↖';
                }
                else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];
                    b[i][j] = '↑';
                }
                else {
                    c[i][j] = c[i][j-1];
                    b[i][j] = '←';
                }
            }
        }
        return c[m][n];
    }


    /**
     * 打印所求得的最大公共子串
     * @param b LCS的构造结果
     * @param X 字符串X
     * @param i 子问题c[i,j]的i
     * @param j 子问题c[i,j]的j
     */
    public static void printLCS(char[][] b, char[] X, int i, int j) {
        if (i==0 || j==0)
            return;
        if (b[i][j] == '↖') {
            printLCS(b, X, i-1, j-1);
            System.out.print(X[i-1]);
        }
        else if (b[i][j] == '↑')
            printLCS(b, X, i-1, j);
        else
            printLCS(b, X, i, j-1);
    }



    public static void main(String[] args) {
        String x = "ABCBDAB";
        String y = "BDCABA";
        char[][] b = new char[x.length()+1][y.length()+1];
        System.out.println(lengthOfLCS(x,y,b));
        printLCS(b,x.toCharArray(),x.length(),y.length());
    }
}
