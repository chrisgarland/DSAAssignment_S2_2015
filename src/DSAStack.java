/**
 * Created by chrisgarland on 14/08/15.
 * <p/>
 * This class is responsible for implementing a stack
 * using an array.
 */
public class DSAStack
{
    int maxElements;
    int numElements;                                     //Returned in count()
    Object[] stack;


    /**
     * Default constructor creates Stack
     * with default size of 100
     */
    public DSAStack()
    {
        maxElements = 100;
        stack = new Object[maxElements];
        numElements = 0;
    }


    /**
     * Alternate constructor creates stack
     * with import as max size
     *
     * @param inMaxElements - int Used as max elements
     */
    public DSAStack( int inMaxElements )
    {
        if( inMaxElements < 0 || inMaxElements == 0 )
        {
            throw new IllegalArgumentException( "Max elements must be greater than 0" );
        }

        maxElements = inMaxElements;
        stack = new Object[maxElements];
        numElements = 0;
    }


    /**
     * Imports an object and pushes onto
     * stack.
     *
     * @param inVal - Object to be pushed
     */
    public void push( Object inVal )
    {
        if( inVal == null )                             //Assert valid Object provided
        {
            throw new IllegalArgumentException( "Provided value must not be null" );
        }
        else if( isFull() )                             //Assert stack is not full before pushing
        {
            throw new IllegalStateException( "Stack is full. Cannot push value" );
        }

        stack[numElements] = inVal;                   //Use numElements as index then increment by 1
        numElements++;
    }


    /**
     * Returns the topmost object
     * on the stack and decrements item
     * count.
     *
     * @return - Top of stack
     */
    public Object pop()
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Stack is empty. Cannot pop value" );
        }

        return stack[--numElements];                    //Decrement numElements by 1 before using it as index
    }


    /**
     * Returns topmost object
     * on the stack without decrementing
     * the count. Essentially just checking
     * the top value
     *
     * @return - Top of stack.
     */
    public Object top()
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Stack empty. Can't check top value" );
        }

        return stack[numElements - 1];                  //Last pushed value
    }


//Accessors:

    /**
     * @return - Number of elements in stack
     */
    public int count()
    {
        return numElements;
    }


    /**
     * @return - Max size of stack (array)
     */
    public int getMaxElements()
    {
        return maxElements;
    }


    /**
     * Uses count() to check whether stack
     * isEmpty.
     *
     * @return -
     */
    public boolean isEmpty()
    {
        boolean empty = false;

        if( count() == 0 )
        {
            empty = true;
        }

        return empty;
    }


    /**
     * Uses count() and getMaxelements()
     * to check whether stack is full.
     *
     * @return -
     */
    public boolean isFull()
    {
        boolean full = false;

        if( count() == getMaxElements() )
        {
            full = true;
        }

        return full;
    }
}
