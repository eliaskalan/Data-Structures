public class List {

    private Node<Suspect> head = null;
    private Node<Suspect> tail = null;


    public List() {
    }


    public boolean isEmpty() {
        return head == null;
    }


    public void insertAtFront(Suspect data) {
        Node<Suspect> n = new Node<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
    }


    public void insertAtBack(Suspect data) {
        Node<Suspect> n = new Node<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
    }



    public Suspect removeFromFront() {
        if (isEmpty())
            return null;
        Suspect data = head.getData();

        if (head == tail)
            head = tail = null;
        else
            head = head.getNext();

        return data;
    }


    public Suspect removeFromBack()  {
        if (isEmpty())
            return null;

        Suspect data = tail.getData();

        if (head == tail)
            head = tail = null;
        else {
            Node<Suspect> iterator = head;
            while (iterator.getNext() != tail)
                iterator = iterator.getNext();

            iterator.setNext(null);
            tail = iterator;
        }

        return data;
    }


    public String toString() {
        if (isEmpty()) {
            return "There is no Suspect with this last name";
        }

        Node current = head;

        StringBuilder ret = new StringBuilder();

        // while not at end of list, output current node's data

        while (current != null) {
            ret.append(current.data.toString());

            if (current.getNext() != null)
                ret.append("\n");

            current = current.next;
        }


        return ret.toString();
    }
}
