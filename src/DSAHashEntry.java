/**
 * Created by chrisgarland on 3/09/15.
 */
public class DSAHashEntry
{
    private static final int NEVER_USED = 0;
    private static final int USED = 1;

    private String key;
    private Object value;
    private int state;                                      //0 = never used; 1 = used; -1 = formerly used

    public DSAHashEntry()
    {
        key = "";
        setValue( null );
        setState( NEVER_USED );
    }


    public DSAHashEntry( String inKey, Object inVal )
    {
        if( inKey.isEmpty() )
        {
            throw new IllegalArgumentException( "No key provided" );
        }
        if( inVal == null )
        {
            throw new IllegalArgumentException( "Value is null" );
        }

        key = inKey;
        setValue( inVal );
        setState( USED );
    }


//Accessors:

    public String getKey()
    {
        return key;
    }


    public Object getValue()
    {
        return value;
    }


    public int getState()
    {
        return state;
    }


//Mutators:

    public void setValue( Object value )
    {
        this.value = value;
    }


    public void setState( int state )
    {
        this.state = state;
    }
}
