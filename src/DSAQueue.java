/**
 * Created by chrisgarland on 13/10/15.
 *
 * Implementation of a Queue using a Linked List
 */
public class DSAQueue<T>
{
    int maxElements;
    int numElements;                                                     //Returned in count()
    DSALinkedList<T> queue;


    /**
     * Default constructor creates Queue
     * with default size of 100
     */
    public DSAQueue()
    {
        maxElements = 100;
        queue = new DSALinkedList<T>();
        numElements = 0;
    }


    /**
     * Alternate constructor creates queue
     * with import as max size
     *
     * @param inMaxElements - int used as max size
     */
    public DSAQueue( int inMaxElements )
    {
        if( inMaxElements < 0 || inMaxElements == 0 )   //Assert valid argument
        {
            throw new IllegalArgumentException( "maxElements must be greater than 0" );
        }

        maxElements = inMaxElements;
        queue = new DSALinkedList<T>();
        numElements = 0;
    }


    /**
     * Inserts Object at the back of the
     * queue (list).
     *
     * @param inVal - Value to insert
     */
    public void enqueue( T inVal )
    {
        if( inVal == null )                             //Asserts object is not null
        {
            throw new IllegalArgumentException( "Value provided must not be null" );
        }
        else if( isFull() )                             //Asserts queue is not full
        {
            throw new IllegalStateException( "Queue is full. Cannot enqueue value" );
        }

        queue.insertLast( inVal );                   //Insert object at end of list
        numElements++;
    }


    /**
     * Returns and removes the first list element,
     * following the FIFO rule
     *
     * @return - First value
     */
    public T dequeue()
    {
        T val;

        if( isEmpty() )                                 //Asserts queue is not empty
        {
            throw new IllegalStateException( "Queue is empty. Cannot dequeue value" );
        }

        val = queue.removeFirst();
        numElements--;

        return val;
    }


    /**
     * Returns the front value without affecting
     * the queue (list)
     *
     * @return - Front value
     */
    public T peek()
    {
        if( isEmpty() )                                 //Asserts queue is not empty
        {
            throw new IllegalStateException( "Queue is Empty. Cannot check front value" );
        }

        return queue.peekFirst();
    }


    /**
     * Accessor for maxElements
     *
     * @return -
     */
    public int getMaxElements()
    {
        return maxElements;
    }


    /**
     * Accessor for numElements
     *
     * @return -
     */
    public int count()
    {
        return numElements;
    }


    /**
     * Checks to see whether queue is empty
     *
     * @return -
     */
    public boolean isEmpty()
    {
        return count() == 0;
    }


    /**
     * Checks to see whether queue is full
     *
     * @return -
     */
    public boolean isFull()
    {
        return count() == getMaxElements();
    }
}

