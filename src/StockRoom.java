/**
 * Created by chrisgarland on 15/09/15.
 */
public abstract class StockRoom
{
    private static final int DEFAULT_LENGTH = 100;

    protected int maxElements;
    protected int numElements;
    protected Object[] storageBank;

//Constructors:

    public StockRoom()
    {
        numElements = 0;
        maxElements = DEFAULT_LENGTH;
        storageBank = new Object[DEFAULT_LENGTH];
    }


    public StockRoom( int maxElements )
    {
        if( maxElements < 0 || maxElements == 0 )
        {
            throw new IllegalArgumentException( "Max elements must be greater than 0" );
        }

        setMaxElements( maxElements );
        numElements = 0;
        storageBank = new Object[maxElements];
    }


//Abstract Methods:

    public abstract void insert( Object inValue );

    public abstract Object remove();

    public abstract Object peek();


//Accessors:

    public int getMaxElements()
    {
        return maxElements;
    }


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


//Mutators:

    public void setMaxElements( int maxElements )
    {
        this.maxElements = maxElements;
    }
}
