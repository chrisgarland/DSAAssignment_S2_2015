/**
 * Created by chrisgarland on 25/10/15.
 */
public class CartonContainer
{
    private Carton[] container;
    private int maxCapacity;
    private int numElements;


    public CartonContainer( int inMaxCapacity )
    {
        if( inMaxCapacity < 0 || inMaxCapacity == 0 )
        {
            throw new IllegalArgumentException( "Capacity of Carton Container must be greater than 0" );
        }

        maxCapacity = inMaxCapacity;
        container = new Carton[maxCapacity];
        numElements = 0;
    }


    public void add( Carton cx )
    {
        container[numElements] = cx;
        numElements++;
    }

//Getters/Setters:

    public int getMaxCapacity()
    {
        return maxCapacity;
    }

    public void setMaxCapacity( int maxCapacity )
    {
        this.maxCapacity = maxCapacity;
    }

    public int getNumElements()
    {
        return numElements;
    }

    public void setNumElements( int numElements )
    {
        this.numElements = numElements;
    }


    @Override
    public String toString()
    {
        String fullString = "";

        for( int ii = 0; ii < getMaxCapacity(); ii++ )
        {
               fullString += container[ii].toString() + "\n";
        }

        return fullString;
    }
}
