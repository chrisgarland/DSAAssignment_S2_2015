/**
 * Created by chrisgarland on 15/09/15.
 *
 * Implemented using an array - to represent a
 * physical rolling corridor.
 */
public class RollingCorridor extends StockRoom
{
    /**
     * Default constructor creates Queue
     * with default size of 100
     */
    public RollingCorridor()
    {
        super();
    }


    /**
     * Alternate constructor creates storageBank
     * with import as max size
     *
     * @param inMaxElements - int used as max size
     */
    public RollingCorridor( int inMaxElements )
    {
        super( inMaxElements );
    }


    /**
     * Inserts Object into storageBank (array)
     * in incremental order, behind last
     * inserted object.
     *
     * @param inVal - Value to insert
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public void insert( Carton inVal ) throws IllegalArgumentException, IllegalStateException
    {
        if( inVal == null )                             //Asserts object is not null
        {
            throw new IllegalArgumentException( "Value provided must not be null" );
        }
        else if( isFull() )                             //Asserts storageBank is not full
        {
            throw new IllegalStateException( "Queue is full. Cannot enqueue value" );
        }

        storageBank[numElements] = inVal;                   //Insert object in next available slot then increment count
        numElements++;
    }


    /**
     * Returns the first inserted object,
     * following the FIFO rule before calling
     * shuffleDown()
     *
     * @return - First value
     * @throws IllegalStateException
     */
    public Carton remove() throws IllegalStateException
    {
        Carton val;

        if( isEmpty() )                                 //Asserts storageBank is not empty
        {
            throw new IllegalStateException( "Queue is empty. Cannot dequeue value" );
        }

        val = storageBank[0];

        numElements--;

        shuffleDown();                                  //Private method below

        return val;
    }


    /**
     * Returns the front value without affecting
     * the storageBank (array)
     *
     * @return - Front value
     * @throws IllegalStateException
     */
    public Carton peek() throws IllegalStateException
    {
        if( isEmpty() )                                 //Asserts storageBank is not empty
        {
            throw new IllegalStateException( "Queue is Empty. Cannot check front value" );
        }

        return storageBank[0];
    }


    /**
     * After first element has been dequeued,
     * this method shuffles all elements down an
     * index, overwriting the previous element
     */
    private void shuffleDown()
    {
        for( int ii = 0; ii < count(); ii++ )               //First element is 0, last is count() - 1
        {
            storageBank[ii] = storageBank[ii + 1];
        }
    }


    /**
     * For printing the geo section
     *
     * @return -
     */
    @Override
    public String describe()
    {
        String fullString = "R:";
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


    /**
     * For printing the pallet section
     *
     * @return
     */
    @Override
    public String printCartons()
    {
        String fullString = "";

        for( int ii = 0; ii < numElements; ii++ )
        {
            fullString += storageBank[ii].toString() + "\n";
        }

        return fullString;
    }
}
