import javax.naming.OperationNotSupportedException;

/**
 * Created by chrisgarland on 16/09/15.
 */
public class Yard extends StockRoom
{
    @Override
    public void insert( Object inValue ) throws IllegalArgumentException, IllegalStateException
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

    /**
     * Needs work
     * @return
     */
    @Override
    public Object remove()
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
    public Object peek()
    {
        return null;                            //Needs implementation
    }

    public Object remove( int index ) throws IllegalStateException
    {
        Object value;

        if( isEmpty() )
        {
            throw new IllegalStateException( "Yard is empty. Cannot remove element" );
        }

        value = storageBank[index];
        numElements--;

        return value;
    }
}
