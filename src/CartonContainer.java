import sun.tools.asm.CatchData;

/**
 * Created by chrisgarland on 25/10/15.
 */
public class CartonContainer
{
    private static final int DEFAULT_SIZE = 1024;

    private Carton[] container;
    private int numElements;


    public CartonContainer()
    {
        numElements = 0;
        container = new Carton[DEFAULT_SIZE];
    }


    public void insert( Carton value )
    {
        if( isFull() )
        {
            throw new IllegalStateException( "Cannot insert into CartonContainer when full" );
        }

        container[numElements] = value;
        numElements++;

        if( !value.hasLifetimeWarranty() && numElements > 1 )
        {
            sort();                                         //By warranty date
        }

    }


    /**
     * Implemented using insertion sort
     * Reverse sorts container array by warranty date.
     */
    private void sort()
    {
        Carton temp;
        int ii;

        for( int nn = 1; nn < numElements; nn++ )      //Start inserting at element 1
        {
            ii = nn;                                        //Start from last item and go backwards
            temp = container[ii];

            while( ii > 0 && container[ii - 1].getWarrantyDate().compareTo( temp.getWarrantyDate() ) < 0 ) //Insert into sub-array left of nn. (A[ii - 1] > temp to keep stable sort)
            {
                container[ii] = container[ii - 1];                          //Swap
                ii = ii - 1;
            }

            container[ii] = temp;                                   //New temp val
        }
    }

    public Carton[] getContainer()
    {
        return container;
    }

    private boolean isFull()
    {
        return numElements == DEFAULT_SIZE;
    }

    private boolean isEmpty()
    {
        return numElements == 0;
    }
}
