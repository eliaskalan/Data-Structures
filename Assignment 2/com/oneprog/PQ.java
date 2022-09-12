package oneprog;

public class PQ {
    private CityImpl[] heap;
    private int size;
    private int opcode=1;



    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SIZE = 4;
    public PQ() {
        this.heap =  new CityImpl[DEFAULT_CAPACITY + 1];
        this.size = 0;

    }

    public PQ(int k) {
        this.heap =  new CityImpl[2*k + 1];
        this.size = 0;

    }
    public PQ(int k,int opcode){
        this.heap =  new CityImpl[2*k + 1];
        this.size = 0;
        this.opcode=opcode;
    }

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }

    public void insert(CityImpl item) {
        if (size >=heap.length*0.75)
            resize();
        heap[++size] = item;
        swim(size);
    }


    public CityImpl max() {

        if (size == 0)
            return null;
        return heap[1];
    }
    public CityImpl min(){
        if(size==0)
            return heap[0];
        int end_layer=Library.log2(size);
        CityImpl min=heap[(int) Math.pow(2,end_layer)];
        for(int i = (int) Math.pow(2,end_layer); i< (int)Math.pow(2,end_layer+1)-1; i++){
            if(heap[i]==null)
                return min;
            if(min.getCovidCasesPerFiftyThousands()>heap[i].getCovidCasesPerFiftyThousands())
                min=heap[i];
        }
        return min;
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
        while (i != 1 && heap[i].compare(heap[parent],opcode)>0) {
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
    //TODO O(N)->O(LogN)
    public City remove(int id){
        CityImpl r=null;
        for(int i=1;i<=size();++i){
            if(heap[i].getID()==id){
                r=heap[i];
                heap[i]=heap[size];
                --size;
                int parent=i/2;
                if(i==1||heap[parent].compare(heap[i],opcode)<0){
                    sink(i);
                }else{
                    swim(i);
                }
                return r;
            }
        }
        return r;
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
                if (heap[left].compare(heap[right],opcode)<0)
                    max = right;
            }

            // If the heap condition holds, stop. Else swap and go on.
            // child smaller than parent
            if (heap[i].compare(heap[max],opcode)>=0)
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

}