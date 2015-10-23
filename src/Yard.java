import javax.naming.OperationNotSupportedException;

/**
 * Created by chrisgarland on 16/09/15.
 */
public class Yard extends StockRoom
{
    public Yard( int maxCapacity )
    {
        super( maxCapacity );
    }


    @Override
    public void insert( Carton inValue ) throws IllegalArgumentException, IllegalStateException
    {
        boolean insertSuccessful = false;
        int index = 0;

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
            }

            index++;
        }

        if( !( insertSuccessful ) )
        {
            throw new IllegalStateException( "Value was not inserted into Yard" );
        }
    }


    public void initialInsert( int index, Carton value )
    {
        storageBank[index] = value;
    }


    public int put( Carton inValue )
    {
        boolean insertSuccessful = false;
        int index = 0;

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
            }

            index++;
        }

        if( !( insertSuccessful ) )
        {
            throw new IllegalStateException( "Value was not inserted into Yard" );
        }

        return index;
    }


    /**
     * Needs work
     * @return
     */
    @Override
    public Carton remove()
    {
        try
        {
            throw new OperationNotSupportedException( "Operation not supported. Must provide index" );
        }
        catch( OperationNotSupportedException e )
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Carton peek()
    {
        return null;                            //Needs implementation
    }

    public Carton remove( int index ) throws IllegalStateException
    {
        Carton value;

        if( isEmpty() )
        {
            throw new IllegalStateException( "Yard is empty. Cannot remove element" );
        }

        value = storageBank[index];
        numElements--;

        return value;
    }
}
