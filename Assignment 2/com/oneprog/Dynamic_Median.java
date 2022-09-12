package oneprog;

public class Dynamic_Median {
    private PQ maxqueue = new PQ(4, 1);
    private PQ minqueue = new PQ(4, -1);


    private boolean isEmpty() {
        return maxqueue.isEmpty() && minqueue.isEmpty();
    }

    public void insert(CityImpl n) {
        if (isEmpty()) {
            minqueue.insert(n);
        } else {
            if (n.compare(median(), 1) <= 0) {
                maxqueue.insert(n);
            }else{
                 minqueue.insert(n);
            }
        }
        rebalance();
    }

    private void rebalance() {
        if(Math.abs(maxqueue.size()-minqueue.size())>1){
            if(maxqueue.size() >minqueue.size()){
                minqueue.insert(maxqueue.getMax());
            }else{
                maxqueue.insert(minqueue.getMax());
            }
        }
    }

    public CityImpl median(){
        if(maxqueue.size()==minqueue.size()){
            if(maxqueue.max().getCovidCasesPerFiftyThousands()>minqueue.max().getCovidCasesPerFiftyThousands()){
                return maxqueue.max();
            }else{
                return minqueue.max();
            }
        }
        else if(maxqueue.size()>minqueue.size()){
            return maxqueue.max();
        }
        else{
            return minqueue.max();
        }
    }
    public void print(){
         System.out.println(median().getCovidCasesPerFiftyThousands());
    }
}
