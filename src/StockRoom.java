/**
 * Created by chrisgarland on 15/09/15.
 * <p/>
 * All instances of DeadEnd, Rolling corridor and Yard
 * will extend this class
 */
public abstract class StockRoom
{
    private static final int DEFAULT_LENGTH = 100;

    protected int maxElements;
    protected int numElements;
    protected Object[] storageBank;


//Constructors: Called by subclasses

    /**
     * Default constructor.
     * Initialises all member fields to default values
     */
    public StockRoom()
    {
        numElements = 0;
        maxElements = DEFAULT_LENGTH;
        storageBank = new Object[DEFAULT_LENGTH];
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
        storageBank = new Object[maxElements];
    }


//Abstract Methods: Overridden in subclasses

    public abstract void insert( Object inValue );

    public abstract Object remove();

    public abstract Object peek();


/*All following methods have common implementation for all subclasses*/

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
     * Uses count() and getMaxelements()
     * to check whether stack is full.
     *
     * @return -
     */
    protected boolean isFull()
    {
        boolean full = false;

        if( count() == getMaxElements() )
        {
            full = true;
        }

        return full;
    }
}
