import java.io.*;
public class RandomizedBST implements MafiaInterface{

    private TreeNode root;
    private int size;
    public void insert(Suspect item) {
            root = insertAsRoot(item, root);
    }
    private TreeNode insertAsRoot(Suspect item, TreeNode root){
        if(root == null) {
            root = new TreeNode(item);
            ++size;
            return root;
        }
        if(root.getItem().key() == item.key()){
            System.out.println("There is already a Suspect with AFM: " + item.key());
            return root;
        }

        if(Math.random() * (root.calN() + 1) < 1.0){
            ++size;
            return insertT(item, root);
        }
        if(item.key() < root.getItem().key()){
            root.setLeft(insertAsRoot(item, root.getLeft()));
        }else{
            root.setRight(insertAsRoot(item, root.getRight()));
        }
        return root;
    }

    private TreeNode rotR(TreeNode root){
        TreeNode x = root.getLeft();
        root.setLeft(x.getRight());
        x.setRight(root);
        return x;
    }

    private TreeNode rotL(TreeNode root){
        TreeNode x = root.getRight();
        root.setRight(x.getLeft());
        x.setLeft(root);
        return x;
    }

    private TreeNode insertT(Suspect item, TreeNode root){
        if(root == null){
            return new TreeNode(item);
        }
        if(item.key() < root.getItem().key()){
            root.setLeft(insertT(item, root.getLeft()));
            root = rotR(root);
        }else{
            root.setRight(insertT(item, root.getRight()));
            root = rotL(root);
        }
        return root;
    }

    public void load(String filename) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(filename));
        String string;
        while ((string = buffer.readLine()) != null) {
            String[] words = Library.splitSentenceByWords(string);
            this.insert(new Suspect(Integer.parseInt(words[0]), words[1], words[2], Double.parseDouble(words[3]), Double.parseDouble(words[4])));
        }
    }




    public void updateSavings(int AFM, double savings)
    {
        searchByAFMRoot(AFM, root).setSavings(savings);
    }


    public Suspect searchByAFM(int AFM) {
        return searchByAFMRoot(AFM, root);
    }

    private Suspect searchByAFMRoot(int AFM,  TreeNode root){
        if (root == null){
            return null;
        }
        if(root.getItem().key() == AFM){
            return root.getItem();
        } else if(AFM < root.getItem().key()){
           return  searchByAFMRoot(AFM, root.getLeft());
        } else {
            return searchByAFMRoot(AFM, root.getRight());
        }
    }


    public List searchByLastName(String last_name) {
        List list = new List();
        returnLastNames(last_name, root, list);
        return list;
    }

    private void returnLastNames(String lastName, TreeNode n, List list){
        if (n == null)
            return;
        if(n.getItem().getLastName().equals(lastName)){
            list.insertAtBack(n.getItem());
        }
        returnLastNames(lastName, n.getLeft(), list);
        returnLastNames(lastName, n.getRight(), list);
    }


    public void remove(int AFM) {

        TreeNode current =root;
        TreeNode parentC=null;
        while(true)
        {
            if(current==null)
                return;
            if(current.getItem().key() == AFM) {
                --size;
                break;
            }
            parentC=current;
            if(AFM > current.getItem().key()) {
                current = current.getRight();
            }else {
                current = current.getLeft();
            }
        }
        TreeNode replace = null;
        if(current.getLeft()==null)
            replace=current.getRight();
        else if(current.getRight()==null)
            replace=current.getLeft();
        else{
            TreeNode findCurrent=current.getRight();
            while (true){
                if(findCurrent.getLeft()!=null)
                    findCurrent=findCurrent.getLeft();
                else break;
            }
            remove(findCurrent.getItem().key());
            findCurrent.setLeft(current.getLeft());
            findCurrent.setRight(current.getRight());
        }
        if(parentC==null)
            root=replace;
        else{
            if(parentC.getLeft()==current)
                parentC.setLeft(replace);
            if(parentC.getRight()==current)
                parentC.setRight(replace);
        }
    }

    public double getMeanSavings() {
        double amount = getSumAmounts(root);
        return  amount/size;
    }

    private double getSumAmounts(TreeNode n){
        if (n == null)
            return 0;

        return n.getItem().getSavings() + getSumAmounts(n.getLeft()) +  getSumAmounts(n.getRight());


    }

    public void printTopSuspects(int k) {
        PQ pq = new PQ(k);
        preOrderTopSuspects(root, pq, k);
        Library.printResultPQ(pq, k);
    }
    
    private void preOrderTopSuspects(TreeNode n, PQ pq, int k){
        if (n == null)
            return;

        if(n.getItem().getTaxedIncome() < 3000) {
            if (pq.size() <= k) {
                pq.insert(n.getItem());
            } else {
                if (n.getItem().getTaxedIncome() < pq.min().getTaxedIncome()) {
                    pq.remove(pq.min().key());
                    pq.insert(n.getItem());
                }
            }
        }

        preOrderTopSuspects(n.getLeft(), pq, k);
        preOrderTopSuspects(n.getRight(), pq, k);
    }

    public void printByAFM() {
        printPreOrder();
    }

    public void printPreOrder(){
        preOrder(this.root);
    }
    private void preOrder(TreeNode n){
        if (n == null)
            return;

        preOrder(n.getLeft());
        System.out.println(n.getItem() + " ");
        preOrder(n.getRight());
    }



    public class TreeNode {
        Suspect item ;
        TreeNode left; // pointer to left subtree
        TreeNode right; // pointer to right subtree

        public TreeNode(Suspect item){
            this.item = item;
        }

        Suspect getItem(){
            return item;
        }

        TreeNode getLeft(){
            return left;
        }

        TreeNode getRight(){
            return right;
        }

        public void setLeft(TreeNode left){

            this.left = left;
        }

        public void setRight(TreeNode right){
            this.right =right;
        }


        public int calN(){
            TreeNode current = this;
            int r = 0;
            int l = 0;
            if(current.getLeft() != null){
                l = current.getLeft().calN() + 1;
            }
            if(current.getRight() != null){
                r = current.getRight().calN() + 1;
            }
            return l+r;
        }
    }
}
