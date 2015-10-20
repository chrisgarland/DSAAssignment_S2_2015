/**
 * Created by chrisgarland on 7/10/15.
 * <p/>
 * This is my implementation of a double-ended, doubly-linked list.
 * <p/>
 * NOTE: Contains DSAListNode at the end of the file as a private inner class.
 */
public class DSALinkedList<T>
{
    private DSAListNode<T> head;                               //Keeps track of front of list
    private DSAListNode<T> tail;                               //Keeps track of back of list
    private int count;                                      //Number of nodes in list


    /**
     * Default constructor initialises head and tail
     * to null
     */
    public DSALinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }


    /**
     * Imports a value. Creates a newNode with imported value and inserts
     * new node at front of list
     *
     * @param newValue - Given to DSAListNode
     */
    public void insertFirst( T newValue )
    {
        DSAListNode<T> newNode = new DSAListNode<T>( newValue );

        if( isEmpty() )                                     //Link head and tail to newNode
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.next = head;                            //Link newNode to first element
            head = newNode;                                 //Link head to newNode
            ( newNode.next ).prev = newNode;                //Maintain double link
        }

        count++;
    }


    /**
     * Imports a value. Creates a newNode with imported value and inserts
     * new node at back of list.
     *
     * @param newValue - Given to DSAListNode
     */
    public void insertLast( T newValue )
    {
        DSAListNode<T> newNode = new DSAListNode<T>( newValue );

        if( isEmpty() )                                     //Link head and tail to newNode
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.prev = tail;                            //Link newNode to last element
            tail = newNode;                                 //Link tail to newNode
            ( newNode.prev ).next = newNode;                //Maintain double link
        }

        count++;
    }


    /**
     * Returns value stored in first list node.
     *
     * @return - Front value
     * @throws IllegalStateException
     */
    public T peekFirst() throws IllegalStateException
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Cannot peekFirst. List is empty" );
        }

        return head.value;
    }


    /**
     * Returns value stored in last list node.
     *
     * @return - End value
     * @throws IllegalStateException
     */
    public T peekLast() throws IllegalStateException
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Cannot peekLast. List is empty" );
        }

        return tail.value;
    }


    /**
     * Un-links the first node and returns the value.
     *
     * @return - Value stored in first node
     * @throws IllegalStateException
     */
    public T removeFirst() throws IllegalStateException
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Cannot removeFirst. List is empty" );
        }

        T nodeValue = head.value;

        if( head.next != null )                             //Unlink node
        {
            ( head.next ).prev = null;
            head = head.next;
        }
        else                                                //No more nodes
        {
            head = null;
            tail = null;
        }

        count--;

        return nodeValue;
    }


    /**
     * Un-links last node in list and returns the value.
     *
     * @return - Value stored in last node
     * @throws IllegalStateException
     */
    public T removeLast() throws IllegalStateException
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Cannot removeLast. List is empty" );
        }

        T nodeValue = tail.value;

        if( tail.prev != null )                             //Unlink node
        {
            ( tail.prev ).next = null;
            tail = tail.prev;
        }
        else                                                //No more nodes
        {
            head = null;
            tail = null;
        }

        count--;

        return nodeValue;
    }


    /**
     * Accessor returns number of elements in list
     *
     * @return - count
     */
    public int getCount()
    {
        return count;
    }


    /**
     * If head.next is null. The list is empty
     *
     * @return - boolean
     */
    private boolean isEmpty()
    {
        return getCount() == 0;
    }


//DSAListNode:

    /**
     * Class made private as it is only used by DSALinkedList.
     * This serves as an extra layer of abstraction.
     * <p/>
     * DSAListNode: Each instance will be a node in the linked list.
     * Each node will contain a reference to the next node, a reference to the
     * previous node and the actual value.
     */
    private class DSAListNode<E>
    {
        //All fields made public so visible to DSALinkedList,
        //and eliminates the need for accessors/mutators
        public E value;
        public DSAListNode<E> next;
        public DSAListNode<E> prev;

        /**
         * Alternate constructor imports an object and initialises
         * all class fields
         *
         * @param inValue - Assigned to the value field
         */
        public DSAListNode( E inValue )
        {
            value = inValue;
            next = null;
            prev = null;
        }
    }
}
