/**
 * Created by chrisgarland on 15/09/15.
 * <p/>
 * All instances of DeadEnd, Rolling corridor and Yard
 * will extend this class
 */
public abstract class StockRoom
{
    private static final int DEFAULT_LENGTH = 10000;

    protected int maxElements;
    protected int numElements;
    protected Carton[] storageBank;


//Constructors: Called by subclasses

    /**
     * Default constructor.
     * Initialises all member fields to default values
     */
    public StockRoom()
    {
        numElements = 0;
        maxElements = DEFAULT_LENGTH;
        storageBank = new Carton[DEFAULT_LENGTH];
    }


    /**
     * Alternate constructor
     *
     * @param maxElements - Given by user
     */
    public StockRoom( int maxElements )
    {
        setMaxElements( maxElements );                          //Exceptions handled in mutators
        numElements = 0;
        storageBank = new Carton[maxElements];
    }


//Abstract Methods: Overridden in subclasses

    public abstract void insert( Carton inValue );

    public abstract Carton remove();

    public abstract Carton peek();

    public abstract String describe();

    public abstract String printCartons();


/*All following methods have common implementation for all subclasses*/

    /**
     * Inserts carton into Yard and returns the index
     *
     * @param inValue     -
     * @param returnIndex -
     * @return - Index
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public int insert( Carton inValue, boolean returnIndex ) throws IllegalArgumentException, IllegalStateException
    {
        boolean insertSuccessful = false;
        int index = 0;
        int retIndex = -1;

        if( inValue == null )
        {
            throw new IllegalArgumentException( "Null value provided" );
        }
        if( isFull() )
        {
            throw new IllegalStateException( "Yard is full. Cannot insert value" );
        }

        while( !( insertSuccessful ) )
        {
            if( storageBank[index] == null )
            {
                storageBank[index] = inValue;
                numElements++;
                insertSuccessful = true;

                if( returnIndex )
                {
                    retIndex = index;
                }
            }

            index++;
        }

        if( !( insertSuccessful ) )
        {
            throw new IllegalStateException( "Value was not inserted into Yard" );
        }

        return retIndex;
    }


//Accessors:

    /**
     * Accessor for maxElements
     *
     * @return - maxElements
     */
    public int getMaxElements()
    {
        return maxElements;
    }


    /**
     * Accessor for numElements
     *
     * @return - numElements
     */
    public int count()
    {
        return numElements;
    }


//Mutators:

    /**
     * Mutator for maxElements
     *
     * @param maxElements - Given by user
     * @throws IllegalArgumentException
     */
    protected void setMaxElements( int maxElements ) throws IllegalArgumentException
    {
        if( maxElements < 0 || maxElements == 0 )
        {
            throw new IllegalArgumentException( "Max elements must be greater than 0" );
        }

        this.maxElements = maxElements;
    }


//Supporting methods:

    /**
     * Uses count() to check whether stack
     * isEmpty.
     *
     * @return -
     */
    protected boolean isEmpty()
    {
        return count() == 0;
    }


    /**
     * Uses count() and getMaxelements()
     * to check whether stack is full.
     *
     * @return -
     */
    protected boolean isFull()
    {
        return count() == getMaxElements();
    }

}
