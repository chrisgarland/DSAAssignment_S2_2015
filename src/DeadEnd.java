/**
 * Created by chrisgarland on 15/09/15.
 * <p/>
 * This class is responsible for implementing a stack
 * using an array.
 */
public class DeadEnd extends StockRoom
{
    /**
     * Default constructor creates Stack
     * with default size of 100
     */
    public DeadEnd()
    {
        super();
    }


    /**
     * Alternate constructor creates stack
     * with import as max size
     *
     * @param inMaxElements - int Used as max elements
     */
    public DeadEnd( int inMaxElements )
    {
        super( inMaxElements );                           //Exceptions handled in parent class
    }


    /**
     * Imports an object and pushes onto
     * storageBank.
     *
     * @param inVal - Carton to be pushed
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    @Override
    public void insert( Carton inVal ) throws IllegalArgumentException, IllegalStateException
    {
        if( inVal == null )                             //Assert valid Carton provided
        {
            throw new IllegalArgumentException( "Provided value must not be null" );
        }
        else if( isFull() )                             //Assert storageBank is not full before pushing
        {
            throw new IllegalStateException( "Stack is full. Cannot push value" );
        }

        storageBank[numElements] = inVal;                   //Use numElements as index then increment by 1

        container.add( inVal );

        numElements++;
    }


    /**
     * Returns the topmost object
     * on the storageBank and decrements item
     * count.
     *
     * @return - Top of storageBank
     * @throws IllegalStateException
     */
    @Override
    public Carton remove() throws IllegalStateException
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Stack is empty. Cannot pop value" );
        }

        return storageBank[--numElements];                    //Decrement numElements by 1 before using it as index
    }


    /**
     * Returns topmost object
     * on the storageBank without decrementing
     * the count. Essentially just checking
     * the top value
     *
     * @return - Top element
     * @throws IllegalStateException
     */
    @Override
    public Carton peek() throws IllegalStateException
    {
        if( isEmpty() )
        {
            throw new IllegalStateException( "Stack empty. Can't check top value" );
        }

        return storageBank[numElements - 1];                  //Last pushed value
    }


    @Override
    public String describe()
    {
        String fullString = "D:";
        int numColonsToAdd = getMaxElements() - ( count() + 1 );
        int ii;

        for( ii = 0; ii < count(); ii++ )
        {
            fullString += storageBank[ii].getConsignmentNote();
            fullString += ":";
        }

        for( ii = 0; ii < numColonsToAdd; ii++ )
        {
            fullString += ":";
        }

        return fullString;
    }
}
