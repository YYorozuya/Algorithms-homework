import java.util.ArrayList;
import java.util.Random;

//优先队列
public class Priorityqueue {
    ArrayList<Integer> heap;

    public Priorityqueue() {
        this.heap = new ArrayList<Integer>();
    }

    private int left(int i){
        return 2*i+1;
    }
    private int right(int i){
        return 2*i+2;
    }
    private int parent(int i){
        return (i-1)/2;
    }

    public void exchange(int a ,int b){
        Integer temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    public void maxHeapify(int i){
        int l = left(i); //左节点
        int r = right(i); //右节点
        int largest; //找三个节点的最大值
        if (l<=heap.size()-1 && heap.get(l)>heap.get(i))
            largest = l;
        else
            largest = i;
        if (r<=heap.size()-1 && heap.get(r)>heap.get(largest))
            largest = r;
        if (largest != i){
            exchange(i, largest);
            maxHeapify(largest);
        }
    }


    //将索引为i的值增加到key
    public void increaseKey(int i, Integer key) {
        if (key < heap.get(i)) {
            System.out.println("New key is smaller than current key!");
            return;
        }

        heap.set(i, key);
        int p = parent(i);
        while (i>0 && heap.get(p)<heap.get(i)) {
            exchange(i, p);
            i = p;
            p = parent(i);
        }

    }

    //把元素x插入到队列中
    public void insert(Integer x){
        heap.add(x);
        increaseKey(heap.size()-1, x);
    }

    //返回最大元素
    public Integer maximum(){
        return heap.get(0);
    }

    //返回并去掉最大元素
    public Integer extractMax(){
        if (heap.size()<1)
        {
            System.out.println("Heap underflow!");
            return -1;
        }
        Integer max = heap.get(0);
        exchange(0, heap.size()-1);
        heap.remove(heap.size()-1);
        maxHeapify(0);
        return max;
    }


    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            a.add(rand.nextInt(100));
        Priorityqueue pq = new Priorityqueue();
        for (Integer i : a)
            pq.insert(i);
        for (Integer i : a)
            System.out.println(pq.extractMax());


    }
}
