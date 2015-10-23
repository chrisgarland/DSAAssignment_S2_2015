/**
 * Created by chrisgarland on 3/09/15.
 */
public class DSAHashEntry
{
    private static final int NEVER_USED = 0;
    private static final int USED = 1;

    private String key;
    private Carton value;
    private int state;                                      //0 = never used; 1 = used; -1 = formerly used

    public DSAHashEntry()
    {
        key = "";
        setValue( null );
        setState( NEVER_USED );
    }


    public DSAHashEntry( String inKey, Carton inVal )
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


    public Carton getValue()
    {
        return value;
    }


    public int getState()
    {
        return state;
    }


//Mutators:


    public void setKey( String key )
    {
        this.key = key;
    }

    public void setValue( Carton value )
    {
        this.value = value;
    }


    public void setState( int state )
    {
        this.state = state;
    }
}
