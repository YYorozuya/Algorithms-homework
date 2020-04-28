import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *  表示图上的一个顶点
 */
class Vertex implements Comparable<Vertex>{
    int id; //顶点的标号
    int pre; //顶点在目前的最短路径的前驱顶点
    int weight; //目前最短路径的权重
    public Vertex(int id, int pre, int weight) {
        this.id = id;
        this.pre = pre;
        this.weight = weight;
    }

    @Override
    public int compareTo(Vertex e) {
        return Integer.compare(this.weight, e.weight);
    }
}

public class Dijkstra {
    /**
     * 使用Dijkstra算法获得最短路径
     * @param G 图的邻接矩阵
     * @param source 源节点的编号
     * @return 一个存放最短路径上每个节点信息的List
     */
    public static List<Vertex> shortest (int[][] G, int source) {
        int n = G.length;
        Vertex[] v = new Vertex[n]; //保存所有顶点的数组，通过v[id]访问每个顶点
        List<Vertex> s = new ArrayList<>(); //保存已在最短路径的s集合中的顶点
        PriorityQueue<Vertex> q = new PriorityQueue<>(); //优先队列保存v-s集合中的顶点

        //初始化三个集合
        for (int i = 0; i < n; i++) {
            Vertex vi = new Vertex(i,-1,Integer.MAX_VALUE);
            if (i == source) {
                vi.weight = 0;
                s.add(vi);
            }
            else
                q.offer(vi);
            v[i] = vi;
        }

        //每次将q中的一个顶点加入到s中
        for (int i = 1; i < n; i++) {
            Vertex u = s.get(s.size()-1);
            //访问顶点u可以直接到达的所有顶点并对这些顶点进行松弛操作
            for (int j = 0; j < n; j++) {
                if (G[u.id][j] != Integer.MAX_VALUE && u.id != j) {
                    if (u.weight+G[u.id][j] < v[j].weight) {
                        q.remove(v[j]);
                        v[j].pre = u.id;
                        v[j].weight = u.weight + G[u.id][j];
                        q.offer(v[j]); //更新优先队列
                    }
                }
            }
            s.add(q.poll());
        }
        return s;
    }



    public static void main(String[] args) {
        int inf = Integer.MAX_VALUE;
        int[][] G = {
                {0,10,inf,inf,5},
                {inf,0,1,inf,2},
                {inf,inf,0,4,inf},
                {7,inf,6,0,inf},
                {inf,3,9,2,0}
        };
        List<Vertex> list = shortest(G,0);

        for (Vertex v : list)
            System.out.println("id:"+v.id +"  pre:"+v.pre +"  distance:"+ v.weight);
    }
}
