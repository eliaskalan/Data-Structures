//Is from second homework
public class PQ {
    private Suspect[] heap;
    private int size;
    private int opcode=1;



    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SIZE = 4;
    public PQ() {
        this.heap =  new Suspect[DEFAULT_CAPACITY + 1];
        this.size = 0;

    }

    public PQ(int k) {
        this.heap =  new Suspect[2*k + 1];
        this.size = 0;

    }
    public PQ(int k,int opcode){
        this.heap =  new Suspect[2*k + 1];
        this.size = 0;
        this.opcode=opcode;
    }

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }

    public void insert(Suspect item) {
            if (size >= heap.length * 0.75)
                resize();
            heap[++size] = item;
            swim(size);
    }

    public Suspect remove(int id){
        Suspect r=null;
        for(int i=1;i<=size();++i){
            if(heap[i].key()== id){
                r=heap[i];
                heap[i]=heap[size];
                --size;
                int parent=i/2;
                if(i==1||heap[parent].getSavings() < heap[i].getSavings()){
                    sink(i);
                }else{
                    swim(i);
                }
                return r;
            }
        }
        return r;
    }



    public Suspect max() {

        if (size == 0)
            return null;
        return heap[1];
    }
    public Suspect min(){
        if(size==0)
            return heap[0];
        int end_layer=Library.log2(size);
        Suspect min=heap[(int) Math.pow(2,end_layer)];
        for(int i = (int) Math.pow(2,end_layer); i< (int)Math.pow(2,end_layer+1)-1; i++){
            if(heap[i]==null)
                return min;
            if(min.getSavings()>heap[i].getSavings())
                min=heap[i];
        }
        return min;
    }


    public Suspect getMax() {
        // Ensure not empty
        if (size == 0)
            return null;
        Suspect root = heap[1];
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
        while (i != 1 && heap[i].getSavings() > heap[parent].getSavings()) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }

    }
    /*
     * remove(int id) return NULL if the element was not found
     * or the element if it was found
     *
     */
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
                if (heap[left].getSavings() < heap[right].getSavings())
                    max = right;
            }

            // If the heap condition holds, stop. Else swap and go on.
            // child smaller than parent
            if (heap[i].getSavings() > heap[max].getSavings())
                return;
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

    private void swap(int i, int j) {
        Suspect tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }


    private void resize() {
        Suspect[] newHeap =  new Suspect[heap.length + AUTOGROW_SIZE];

        // copy array
        //(notice: in the priority queue, elements are located in the array slots with positions in [1, size])
        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }


}
