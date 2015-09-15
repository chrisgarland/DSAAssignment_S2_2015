/**
 * Created by chrisgarland on 15/09/15.
 */
public abstract class StockRoom
{
    protected int maxElements;
    protected int numElements;

//Constructors:


    public StockRoom()
    {
        maxElements = 100;
        numElements = 0;
    }


    public StockRoom(int maxElements)
    {
        if( maxElements < 0 || maxElements == 0 )
        {
            throw new IllegalArgumentException( "Max elements must be greater than 0" );
        }

        setMaxElements( maxElements );
    }


//Abstract Methods:

    public abstract void insert(Object inValue);

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
