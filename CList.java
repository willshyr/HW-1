//Yu-chi Chang, William Shyr
//ychang64, wshyr1
//EN.600.226
//p1

//head & tail are connected, but no sentinel nodes
//empty list: a null list reference
//a list w/ a single member (size = 1): head, curr, tail, next, prev point
//to itself a list w/ n nodes: like DList w/o sentinels, except the head and
//tail would reference
//each other.

//HW: implement class CList<E> which implements the interface List<E> using
//a circular
//double linked list implementation.

///no sentinels --> special cases (empty, one item)
//be careful with the append() (general, special cases)
//inserting places an element at curr, to the right of the cursor
//(between nodes)
//this action does not change the cursor according to the text
//implementations of the
//List interface, meaning that the newly inserted value is now current.
//i.e. insert() puts the new node before curr val & moves curr item left
//to the new
//value (do not confuse the concept of cursor or curr node.)

//remove() removes the current item & places the current position reference
//on the
//next item (if there is one)


/**
 * Doubly Linked List implementation of the List interface.
 * Uses sentinel nodes at the head & tail, and an inner Node class.
 * This is only a partial implementation, loosely based on OpenDSA version.
 *
 * This version differs notably from the DSA version in that the curr
 * data member refers to the node *before* the cursor, whereas in OpenDSA
 * the curr data member refers to the node *after* the cursor.
 *
 * @author Joanne Selinski
 * @param <T> the type of the List
 */
public class CList<T> implements List<T> {

    /**
     * Inner doubly linked Node class for convenience.
     * Note that the generic type is implied since we are within DLList<T>.
     */
    public final class Node {

        /** The data in the element. */
        private T data;
        /** The left neighbor node. */
        private Node prev;
        /** The right neighbor node. */
        private Node next;

        /**
         * Make a node.
         * @param item the data to put in it
         * @param p the link to the previous node
         * @param n the link to the next node
         */
        public Node(T item, Node p, Node n) {
            this.data = item;
            this.prev = p;
            this.next = n;
        }

        /**
         * @return data from node
         */
        public T getData() {
            return this.data;
        }
        /**
         * @param d the data to be set to node
         */
        public void setData(T d) {
            this.data = d;
        }
        /**
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /**
         * @return the previous node
         */
        public Node getPrev() {
            return this.prev;
        }
        /**
         * @param n set the next node
         */
        public void setNext(Node n) {
            this.next = n;
        }
        /**
         * @param p set the previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }
    }

    /** Head node. */
    private Node head;
    /** Tail node. */
    private Node tail;
    /** Number of actual data nodes in list. */
    private int size;
    /** Current node (think of as a cursor between nodes). */
    private Node curr;

    /**
     * Create an empty list w/o sentinels.
     */
    public CList() {
        this.clear(); //code reuse
    }

    /**
     * Remove all contents from the list, so it is once again empty.
     */
    public void clear() {
        this.size = 0;
        // this.head = null;
        // this.tail = null;
        // this.head.next = this.tail;
        // this.curr = this.head;  // because insert will insert after curr
    }
    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(T t) {
        Node n = new Node(t, this.curr, this.curr.next);
        n.prev.next = n;   // connect left neighbor
        n.next.prev = n;   // connect right neighbor
        this.size++;       // curr still point to the original node
        return true;
    }

    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert2(T t) {
        if (this.isEmpty()) { //empty list
            this.curr = new Node(t, this.curr, this.curr);
            this.head = this.curr;
            this.tail = this.curr;
        } else if (this.size == 1) {  // size = 1
            this.curr = new Node(t, this.curr, this.curr);
            // link curr.prev to the new element
            this.curr.prev.next = this.curr;
            this.tail = this.curr;
        } else if (this.size == 2) {
            //this.tail is the same as this.curr.next????
            this.curr = new Node(t, this.curr, this.curr.next);
            this.curr.prev.next = this.curr;
            this.curr.next.prev = this.curr;
            this.tail = this.curr;
        } else {
            this.curr = new Node(t, this.curr, this.curr.next);
            this.curr.prev.next = this.curr;
            this.curr.next.prev = this.curr;
            this.tail = this.curr;
        }
        this.size++;
        return true;
    }

    /**
     * Append a value at the end of the list.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append(T t) {
        Node temp = this.curr;        // hold onto original position
        this.curr = this.tail.prev;   // move to before the tail sentinel
        this.insert(t);               // code reuse!
        this.curr = temp;             // restore cursor to original position
        return true;
    }

    /**
     * Append a value at the end of the list.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append2(T t) {
        if (this.isEmpty() || this.size == 1) {  // empty list
            this.insert2(t);  // code reuse
        } else {
            Node temp = this.curr;        // hold onto original position
            this.curr = this.tail;        // move curr to the tail
            this.insert2(t);              // code reuse!
            this.tail = this.curr;        // move taill to curr
            this.curr = temp;             // restore cursor to original position
        }
        this.size++;
        return true;
    }
//remove() removes the current item & places curr on the
//next item (if there is one)

    /**
     * Remove and return the current element (one to right of cursor).
     * @return the value of the element removed, null if list is empty
     */
    public T remove() {
        if (this.curr.next == this.tail) {
            return null;
        }
        T val = this.curr.next.data;
        this.curr.next = this.curr.next.next;  // bypass node being deleted
        this.curr.next.prev = this.curr;       // bypass it in other direction
        this.size--;
        return val;
    }

    /**
     * Remove and return the current element (one to right of cursor).
     * @return the value of the element removed, null if list is empty
     */
    public T remove2() {
        if (this.isEmpty()) {
            return null; //nothing is removed
        } else if (this.size == 1) {
            T val = this.curr.next.data;
            this.clear();
            return val;
        } else {
            T val = this.curr.data;
            this.curr.next = this.curr.next.next;
            this.curr.next.prev = this.curr;
            this.size--;
            return val;
        }
    }

    /**
     * Return the current element (data to right of cursor).
     * @return the value of the current element, null if none
     */
    public T getValue2() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.curr.next.data;  //curr or curr.next??
        }
    }

    /**
     * Return the number of elements in the list.
     * @return the length of the list
     */
    public int length() {
        return this.size;
    }

    /**
     * @return true if list is empy, false otherwise
     */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return the size of List
     */
    public int size() {
        return this.size;
    }


    /* ---------- METHODS BELOW THIS LINE ARE NOT IMPLEMENTED ------------ */

    /**
     * Set the current position to the start of the list.
     */
    public void moveToStart() {
        if (this.isEmpty()) {
            this.curr = null;
        } else {
            this.curr = this.head;
        }
    }

    /**
     * Set the current position to the end of the list.
     */
    public void moveToEnd() {
        if (this.isEmpty()) {
            this.curr = null;
        } else {
            this.curr = this.tail;
        }
    }

    /**
     * Move the current position one step left,
     * no change if already at beginning.
     */
    public void prev() {
        if (this.curr != this.head) {
            this.curr = this.curr.prev;
        } else { //head == curr
            System.out.println("Cursor is at the front.");
        }    // next and prev cannot move beyond the original start/end
    }

    /**
     * Move the current position one step right, no change if already at end.
     */
    public void next() {
        if (this.curr != this.tail) {
            this.curr = this.curr.next;
        } else {
            System.out.println("Cursor is at the end.");
        }
    }

    /**
     * Return the position of the current element.
     * @return the current position in the list
     */
    public int currPos() {
        Node temp = this.head;
        int i = 0;
        for (i = 0; this.curr != temp; i++) {
            temp = temp.next;
        }
        return i;
    }

    /**
     * Set the current position.
     * @param pos the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos >= this.size)) { // pos = size -> append()
            return false;
        }
        this.curr = this.head; // head's position = 0
        for (int i = 0; i < pos; i++) {  // cannot move beyond the end
            this.curr = this.curr.next; // update curr
        }
        return true;
    }

    /**
     * Return true if current position is at end of the list.
     * @return true if the current position is the end of the list
     */
    public boolean isAtEnd() {
        return this.curr == this.tail;
    }

    /**
     * @return current element value. Note that null gets returned if curr
     * is at the tail
     */
    public T getValue() {
        return this.curr.data;
    }

    /**
     * @return current node
     */
    public Node getCurr() {
        return this.curr;
    }

    /**
     * changes current node to the next node even when it's at the tail.
     */
    public void cnext() {
        this.curr = this.curr.next;
    }

    /**
     * changes current node to the next node even when it's at the head.
     */
    public void cprev() {
        this.curr = this.curr.prev;
    }
    /**
     * @return the list data
     */
    public String toString() {
        String result = "(";
        this.moveToStart();
        for (int i = 0; i < this.size; i++) {
            result += this.curr.data;   // calls toString implicitly
            if (i < this.size - 1) {
                result += ", ";
                this.next();
            }

        }
        result += ")";
        return result;
    }
}
