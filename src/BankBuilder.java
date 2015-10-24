/**
 * Created by chrisgarland on 23/10/15.
 */
public class BankBuilder
{
    DSAQueue<String> geoQueue;
    DSAHashTable cartonMap;
    StockRoom[] m_bank;

    public BankBuilder( DSAQueue<String> inQueue, DSAHashTable inTable, StockRoom[] inRoom )
    {
        geoQueue = inQueue;
        cartonMap = inTable;
        m_bank = inRoom;
    }


    /**
     * Responsible for building a dead-end stock room.
     * It then inserts appropriate cartons, and places
     * complete stock room within the master array, recording
     * the carton's position.
     *
     * @param index - Provided by DistributionCentre{}
     */
    public void buildDead( int index )
    {
        int capacity = 0;
        DSAQueue<Carton> tempDead = new DSAQueue<Carton>();             //Temporarily storing cartons
        DeadEnd dead;                                                   //The actual stockroom
        Carton cx;

        try
        {
            while( !geoQueue.isEmpty() && frontNotALetter() )           //Fill tempQueue
            {
                if( geoQueue.peek().equals( ":" ) )                     //For every ":", increment capacity
                {
                    geoQueue.dequeue();
                    capacity++;
                }
                else                                                    //Token is a consignment note
                {
                    cx = cartonMap.remove( geoQueue.dequeue() );        //Extract carton from hash table
                    tempDead.enqueue( cx );                             //insert into temp queue
                }
            }

            dead = new DeadEnd( capacity );                             //Create the actual stockroom

            while( !tempDead.isEmpty() )
            {
                cx = tempDead.dequeue();                                //Extract from temp queue
                dead.insert( cx );                                      //Fill stockroom with appropriate cartons

                cx.setRowPosition( index );                             //Master array index
                cx.setColumnPosition( dead.count() -1 );                //Stockroom index

                //Insert updated carton back into hash table
                String key = String.valueOf( cx.getConsignmentNote() );
                cartonMap.put( key, cx );
            }

            m_bank[index] = dead;                                       //Insert Stockroom into master array
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
        catch( IllegalStateException e )
        {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Responsible for building a dead-end stock room.
     * It then inserts appropriate cartons, and places
     * complete stock room within the master array, recording
     * the carton's position.
     *
     * @param index - Provided by DistributionCentre{}
     */
    public void buildRoller( int index )
    {
        int capacity = 0;
        DSAQueue<Carton> tempRoller = new DSAQueue<Carton>();           //Temporarily storing cartons
        RollingCorridor roller;                                         //The actual stockroom
        Carton cx;

        try
        {
            while( !geoQueue.isEmpty() && frontNotALetter() )           //Fill tempQueue
            {
                if( geoQueue.peek().equals( ":" ) )                     //For every ":", increment capacity
                {
                    geoQueue.dequeue();
                    capacity++;
                }
                else                                                    //Token is a consignment note
                {
                    cx = cartonMap.remove( geoQueue.dequeue() );        //Extract carton from hash table
                    tempRoller.enqueue( cx );                           //insert into temp queue
                }
            }

            roller = new RollingCorridor( capacity );                   //Create the actual stockroom

            while( !tempRoller.isEmpty() )
            {
                cx = tempRoller.dequeue();                              //Extract from temp queue
                roller.insert( cx );                                    //Fill stockroom with appropriate cartons

                cx.setRowPosition( index );                             //Master array index
                cx.setColumnPosition( roller.count() -1 );              //Stockroom index

                String key = String.valueOf( cx.getConsignmentNote() );
                cartonMap.put( key, cx );
            }

            m_bank[index] = roller;                                     //Insert Stockroom into master array
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
        catch( IllegalStateException e )
        {
            System.out.println(e.getMessage());
        }
    }


    public void buildYard( int index )
    {
        int capacity = 0;
        int indexOfYard = 0;
        DSAQueue<String> tempYard = new DSAQueue<String>();
        Yard yard;
        String token;
        Carton cx;

        try
        {
            while( !geoQueue.isEmpty() && frontNotALetter() )
            {
                token = geoQueue.dequeue();

                if( token.equals( ":" ) )
                {
                    capacity++;
                }

                tempYard.enqueue( token );
            }

            yard = new Yard( capacity );

            while( !tempYard.isEmpty() )
            {
                token = tempYard.dequeue();

                if( token.equals( ":" ) )
                {
                    if( tempYard.isEmpty() || tempYard.peek().equals( ":" ) )
                    {
                        indexOfYard++;
                    }
                }
                else
                {
                    cx = cartonMap.remove( token );

                    yard.initialInsert( indexOfYard, cx );

                    cx.setRowPosition( index );
                    cx.setColumnPosition( indexOfYard );

                    cartonMap.put( token, cx );

                    indexOfYard++;
                }
            }

            m_bank[index] = yard;
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
        catch( IllegalStateException e )
        {
            System.out.println(e.getMessage());
        }
    }


    private boolean frontNotALetter()
    {
        return !geoQueue.peek().equals( "D" ) && !geoQueue.peek().equals( "R" ) && !geoQueue.peek().equals( "Y" );
    }
}
