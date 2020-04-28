import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 表示图中的一条边，u、v表示两个顶点，weight表示边的权重
 */
class Edge implements Comparable<Edge>{
    int u;
    int v;
    int weight;
    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return u + "-" + v + " "+ weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(this.weight, e.weight);
    }
}

public class Kruskal {

    /**
     * kruskal算法求解最小生成树
     * @param G 图的邻接矩阵
     * @return 返回最小生成树的所有边
     */
    public static List<Edge> mst(int[][] G) {
        int n = G.length;

        //记录每个节点分别属于哪颗树，初始条件下每个节点为独立一棵树
        int[] setNum = new int[n];
        for (int i = 0; i < n; i++)
            setNum[i] = i;

        //从邻接矩阵中获取所有的边并按权重非递减排序
        List<Edge> allEdges = new ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                if (G[i][j] != Integer.MAX_VALUE )
                    allEdges.add(new Edge(i, j, G[i][j]));
        Collections.sort(allEdges);

        List<Edge> mst = new ArrayList<>(); //保存最小生成树的列表

        for (Edge e : allEdges)
            //如果这条边的两个节点不属于同一棵树，则这条边对于集合mst是安全的
            if (setNum[e.u] != setNum[e.v]) {
                mst.add(e);
                int temp = setNum[e.v];
                //将两个节点对应的两棵树合为一棵树
                for (int i = 0; i < setNum.length; i++) {
                    if (setNum[i] == temp)
                        setNum[i] = setNum[e.u];
                }
            }
        return mst;
    }


    public static void main(String[] args) {
        int inf = Integer.MAX_VALUE;
        int[][] G = {
                {0,4,inf,inf,inf,inf,inf,8,inf},
                {4,0,8,inf,inf,inf,inf,11,inf},
                {inf,8,0,7,inf,4,inf,inf,2},
                {inf,inf,7,0,9,14,inf,inf,inf},
                {inf,inf,inf,9,0,10,inf,inf,inf},
                {inf,inf,4,14,10,0,2,inf,inf},
                {inf,inf,inf,inf,inf,2,0,1,6},
                {8,11,inf,inf,inf,inf,1,0,7},
                {inf,inf,2,inf,inf,inf,6,7,0},
        };

        List<Edge> list = mst(G);
        System.out.println(list);
    }

}


