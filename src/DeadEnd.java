/**
 * Created by chrisgarland on 15/09/15.
 * <p/>
 * This class is responsible for implementing a stack
 * using an array.
 */
public class DeadEnd extends StockRoom
{
    Object[] stack;


    /**
     * Default constructor creates Stack
     * with default size of 100
     */
    public DeadEnd()
    {
        super();
        stack = new Object[maxElements];
        numElements = 0;
    }


    /**
     * Alternate constructor creates stack
     * with import as max size
     *
     * @param inMaxElements - int Used as max elements
     */
    public DeadEnd( int inMaxElements )
    {
        super(inMaxElements);                           //Exceptions handled in parent class
        stack = new Object[maxElements];
    }


    /**
     * Imports an object and pushes onto
     * stack.
     *
     * @param inVal - Object to be pushed
     */
    @Override
    public void insert( Object inVal )
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
    @Override
    public Object remove()
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
    @Override
    public Object peek()
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Stack empty. Can't check top value" );
        }

        return stack[numElements - 1];                  //Last pushed value
    }

}
