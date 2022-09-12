package oneprog;

public class PQ_PartA {
    private CityImpl[] heap;
    private int size;




    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SIZE = 4;

    public PQ_PartA() {
        this.heap =  new CityImpl[DEFAULT_CAPACITY + 1];
        this.size = 0;
    }


    public int size(){
        return size;
    }

    public void insert(CityImpl item) {
        if (size >=heap.length*0.75)
            resize();
        heap[++size] = item;
    }



    public CityImpl getMax() {
        // Ensure not empty
        if (size == 0)
            return null;
        CityImpl root = heap[1];
        heap[1] = heap[size];
        size--;
        sink(1);
        return root;
    }

    private void swim(int i) {
        // if i is root (i==1) return
        if (i == 1)
            return;

        // find parent
        int parent = i / 2;

        // compare parent with child i
        while (i != 1 && heap[i].compare(heap[parent],1)>0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }

    }



    private void sink(int i) {
        // determine left, right child
        int left = 2 * i;
        int right = left + 1;

        // if 2*i > size, node i is a leaf return
        if (left > size)
            return;

        // while haven't reached the leafs
        while (left <= size) {
            // Determine the largest child of node i
            int max = left;
            if (right <= size) {
                if (heap[left].compare(heap[right],1)<0)
                    max = right;
            }

            // If the heap condition holds, stop. Else swap and go on.
            // child smaller than parent
            if (heap[i].compare(heap[max],1)>=0) {
                return;
            }else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

    private void swap(int i, int j) {
        CityImpl tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }


    private void resize() {
        CityImpl[] newHeap =  new CityImpl[heap.length + AUTOGROW_SIZE];

        // copy array
        //(notice: in the priority queue, elements are located in the array slots with positions in [1, size])
        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    void sort() {
        PQ_PartA pq = new PQ_PartA();
        for (int i= 0; i <= this.size(); i++) {
            pq.insert(this.heap[i]);
        }
        for (int i = 1; i >= size; i++) {
            this.heap[i] = pq.getMax();
        }
    }

    void print(int k){
        for(int i=1; i<= k; ++i){
            System.out.println(this.heap[i]);
        }
    }
}