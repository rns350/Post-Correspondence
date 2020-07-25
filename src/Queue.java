/**This class implements an extremely basic Queue, with functionality to Enqueue, 
 * to Dequeue, and to check whether or not the Queue is empty.  It is implemented
 * through a singularly linked list with references to the head and tail for O(1)
 * enqueueing and dequeueing.  Unfortunately, it does not include error checking.
 * 
 * @author Reed Schick
 * @version 1.0
 */
public class Queue<E>{

    /**Internal Node class for representing the Queue's data.  Stores the data, E, 
     * and a reference to the next node in the list
     * 
     * @param <E> the type of object that the Node will hold.
     */
    private class Node<E>{
        E data; //data stored by the node
        Node<E> next; //the next node in the list

        /**Node constructor.  Takes data type E and creates a Node holding the data
         * 
         * @param E The data to be stored.
         */
        public Node(E data){
            this.data = data;
        }
    }

    private Node<E> head; //The head of the list
    private Node<E> tail; //The tail of the list
    private int size; //The size of the list

    /**Creates a new node to store the incoming data type E and adds
     * it to the end of the queue.
     * 
     * @param data the data to be added to the end of the queue
     */
    public void enqueue(E data){
        Node<E> incoming = new Node<E>(data); //Create new node

        //empty list case - make both head and tail the new node.
        if(size == 0){
            head = incoming;
            tail = incoming;
        }

        //list of size one. Connect the head to the new element and make it the new tail
        if(size == 1){
            head.next = incoming;
            tail = incoming;
        }

        //list of size > 1.  Connect the tail to the new element and make it the new tail
        else{
            tail.next = incoming;
            tail = incoming;
        }

        //increase the size of the Queue
        size ++;    
    }

    /**Returns the next element in line from the Queue and returns it.
     * 
     * @return the next element in line in the Queue
     */
    public E dequeue(){
        E toReturn = head.data; //we want to return the head, as it has the least recently added.
        head = head.next; //set new head.
        size --; //reduce size
        return toReturn;
    }

    /**Checks if the Queue is empty, and returns a boolean with this information
     * 
     * @return true if the Queue is empty, false otherwise.
     */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
}