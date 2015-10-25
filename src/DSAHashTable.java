/**
 * Created by chrisgarland on 3/09/15.
 *
 * Implemented using an array. max load factor
 * is 0.5 before resize().
 */
public class DSAHashTable
{
    private static final int NEVER_USED = 0;
    private static final int USED = 1;
    private static final int FORMERLY_USED = -1;
    private static final int FIRST_INDEX = 0;
    private static final double MAX_LOAD_FACTOR = 0.5;

    private DSAHashEntry[] m_hashTable;
    private double numElements;
    private double size;                                                 //Total length of array


    /**
     * Alternate constructor initialises all member fields.
     * Takes a size (int) and finds the next prime. Then creates
     * an array with the size of this prime.
     *
     * @param inSize - Provided by user. For the array size.
     */
    public DSAHashTable( int inSize )
    {
        if( inSize < 0 || inSize == 0 )
        {
            throw new IllegalArgumentException( "Size must be greater than 0" );
        }

        int actualSize = nextPrime( inSize );                           //Next prime after inSize

        m_hashTable = new DSAHashEntry[actualSize];                     //Actual size is a prime
        size = actualSize;

        initialiseTable();                                              //Fills table with unused entries. numEle = 0
    }


    /**
     * Wrapper method for put(DSAHashEntry).
     * Takes a key and a value to be stored (as parameters).
     * Creates a DSAHashEntry with the provided parameters.
     * Calls put(DSAHashEntry).
     *
     * @param key   - Provided by user
     * @param value - Provided by user
     * @throws IllegalStateException
     */
    public void put( String key, Carton value ) throws IllegalStateException
    {
        DSAHashEntry entry = null;

        if( containsKey( key ) )                                        //Duplicate keys not allowed
        {
            throw new IllegalStateException( "Duplicate key detected" );
        }

        try                                                             //Create new entry
        {
            entry = new DSAHashEntry( key, value );
        }
        catch( IllegalArgumentException e )                             //Exceptions thrown in DSAHashEntry.java
        {
            System.out.println( e.getMessage() );
        }

        if( currentLoadFactor() > MAX_LOAD_FACTOR )                     //50% full
        {
            reSize();
        }

        try
        {
            put( entry );                                               //Actual insertion happens here
        }
        catch( IllegalStateException e )                                //Key already exists in table
        {
            System.out.println( e.getMessage() );
        }
    }


    /**
     * @param entry -
     * @throws IllegalStateException
     */
    public void put( DSAHashEntry entry ) throws IllegalStateException
    {
        if( containsKey( entry.getKey() ) )
        {
            throw new IllegalStateException( "Duplicate key detected" );
        }

        int index = hash( entry.getKey() );

        while( m_hashTable[index].getState() == USED )                  //Collision occurred
        {
            checkDuplicateKey( index, entry.getKey() );                 //Throws exception
            index = stepHash( index );
        }

        m_hashTable[index] = entry;

        numElements++;
    }


    /**
     * Responsible for returning a value at a given key.
     * Does not remove an entry.
     *
     * @param key - Provided by user
     * @return - Value at given key
     * @throws IllegalArgumentException
     */
    public Carton get( String key ) throws IllegalArgumentException
    {
        Carton value = null;                                            //To be returned
        int index = hash( key );                                        //Initial index
        boolean found = false;                                          //while loop

        if( !containsKey( key ) )
        {
            throw new IllegalArgumentException( "Key: \"" + key + "\" not found. Possible error in description file" );
        }

        while( !found )                                                 //Loop until key is found
        {
            if( m_hashTable[index].getKey().equals( key ) )             //Key found
            {
                found = true;
                value = m_hashTable[index].getValue();                  //Grab value from current index
            }
            else                                                        //Collision
            {
                index = stepHash( index );                              //Keep going (Linear probe)
            }
        }

        return value;
    }


    /**
     * Responsible for returning a value at a given key.
     * Also effectively removes the entry from the hash table.
     *
     * @param key - Provided by user
     * @return - Value at provided key
     * @throws IllegalArgumentException
     */
    public Carton remove( String key ) throws IllegalArgumentException
    {
        Carton value = null;                                            //To be returned
        int index = hash( key );                                        //Initial index
        boolean found = false;                                          //while loop

        if( !containsKey( key ) )
        {
            throw new IllegalArgumentException(
                    "Key: \"" + key + "\" not found. Possible error in description file..." );
        }

        while( !found )                                                 //Loop until key is found
        {
            if( m_hashTable[index].getKey().equals( key ) )             //Key found
            {
                found = true;
                value = m_hashTable[index].getValue();                  //Grab value
                m_hashTable[index].setState( FORMERLY_USED );           //Unlink entry from table
                m_hashTable[index].setKey( "" );
                m_hashTable[index].setValue( null );
            }
            else                                                        //Collision
            {
                index = stepHash( index );                              //Keep going
            }
        }

        numElements--;
        return value;
    }


    /**
     * Responsible for hashing a (String) key. Does this
     * by splitting string into individual characters and
     * using the sum of their ASCII decimal values.
     *
     * @param key - Provided by put( DSAHashEntry )
     * @return - An index that is never larger than table length
     */
    private int hash( String key )
    {
        long index = 0;

        for( int ii = 0; ii < key.length(); ii++ )
        {
            index = ( 5 * index ) + key.charAt( ii );                   //Actual hashing gets done here.
        }

        return (int) index % getTableSize();                            //Never larger than table length
    }


    /**
     * Linear probing method.
     * If there is a collision, this function will probe
     * forward by one index.
     *
     * @return - Current index incremented by 1.
     */
    private int stepHash( int currentIndex )
    {
        int nextIndex;

        if( atEndOfTable( currentIndex ) )
        {
            nextIndex = FIRST_INDEX;                                    //Wrap around to start of table
        }
        else
        {
            nextIndex = currentIndex + 1;                               //Increment index by 1
        }

        return nextIndex;
    }


    /**
     * Checks whether the table contains a given
     * key.
     *
     * @param key - Provided by calling method
     * @return - Boolean
     */
    private boolean containsKey( String key )
    {
        boolean containsKey = false;
        int index = hash( key );

        if( m_hashTable[index].getState() != NEVER_USED )            //Loop until state = never used
        {
            if( m_hashTable[index].getKey().equals( key ) )             //Keys match
            {
                containsKey = true;
            }
            else
            {
                index = stepHash( index );                              //Keep going
            }
        }

        return containsKey;
    }

    /**
     * Responsible for returning the next prime
     * number. If inSize is a prime, will just
     * return inSize.
     *
     * @param startVal -
     * @return - Next prime number
     */
    private int nextPrime( int startVal )
    {
        int primeVal;
        boolean startValueIsEven = ( startVal % 2 ) == 0;               //Is start val even?

        if( startValueIsEven )
        {
            primeVal = startVal + 1;                                    //Increment even number (no primes are even)
        }
        else                                                            //Already an odd number
        {
            primeVal = startVal;
        }

        while( !isPrime( primeVal ) )
        {
            primeVal += 2;                                              //Skip even numbers
        }

        return primeVal;
    }


    /**
     * Responsible for checking whether an
     * int is a prime number.
     *
     * @param num - To be checked
     * @return - true/false
     */
    private boolean isPrime( int num )
    {
        boolean isPrime = true;                                         //Return value

        boolean numNotTwo = num != 2;                                   //Used as condition in if-else chain
        boolean numIsEven = ( num % 2 ) == 0;                           //Used as condition in if-else chain

        int sqrtNum = (int) Math.ceil( Math.sqrt( num ) );//take sqrt of num, round up to whole number and cast to (int)

        if( num < 2 )
        {
            isPrime = false;
        }
        else if( numNotTwo && numIsEven )
        {
            isPrime = false;
        }

        for( int ii = 3; ii <= sqrtNum; ii += 2 )                       //Do not need to check past sqrt of number
        {
            if( num % ii == 0 )
            {
                isPrime = false;
            }
        }

        return isPrime;
    }


    /**
     * Responsible for initialising hash table
     * by filling it with empty hash entries.
     */
    private void initialiseTable()
    {
        for( int ii = 0; ii < getTableSize(); ii++ )
        {
            m_hashTable[ii] = new DSAHashEntry();
        }

        numElements = 0;
    }


    /**
     * Determines whether the current index
     * is equal to max index.
     *
     * @param currentIndex -
     * @return -
     */
    private boolean atEndOfTable( int currentIndex )
    {
        int maxIndex = getTableSize() - 1;

        return currentIndex == maxIndex;
    }


    /**
     * @return - Current load factor
     */
    private double currentLoadFactor()
    {
        double currentLoadFactor = numElements / size;

        return currentLoadFactor;
    }


    /**
     * Checks for duplicate key
     *
     * @param index - Current index
     * @param key   - To be checked against
     */
    private void checkDuplicateKey( int index, String key )
    {
        boolean keysMatch = m_hashTable[index].getKey().equals( key );

        if( keysMatch )
        {
            throw new IllegalStateException( "Duplicate key detected" );
        }
    }


    /**
     * Responsible for resizing the table to twice the size(next prime).
     * Then re-populates new table with all entries
     */
    private void reSize()
    {
        int newSize = nextPrime( getTableSize() * 2 );
        size = newSize;

        DSAHashEntry[] oldTable = m_hashTable;
        m_hashTable = new DSAHashEntry[newSize];

        initialiseTable();

        for( int ii = 0; ii < oldTable.length; ii++ )
        {
            if( oldTable[ii].getState() == USED )
            {
                put( oldTable[ii] );
            }
        }
    }


//Accessors:

    public int count()
    {
        return (int) numElements;
    }


    public int getTableSize()
    {
        return (int) size;
    }
}

