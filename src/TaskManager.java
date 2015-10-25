import java.util.StringTokenizer;

/**
 * Created by chrisgarland on 25/10/15.
 */
public class TaskManager
{
    private DistributionCentre dc;
    private boolean added;


    public TaskManager( DistributionCentre distCentre )
    {
        dc = distCentre;
        added = false;
    }


    public void add( String taskLine )
    {
        StringTokenizer lineTok = new StringTokenizer( taskLine, ":", false );
        Carton cx;
        added = false;

        lineTok.nextToken();                                //pop front value off

        cx = getCarton( lineTok );

        if( cx.hasLifetimeWarranty() )
        {
            addToDead( cx );

            if( !added )
            {
                addToYard( cx );
            }
            if( !added )
            {
                addToRoller( cx );
            }
            if( !added )
            {
                throw new IllegalStateException( "Distribution Centre full" );
            }
        }
        else                                                //Has expiry date
        {
            addToRoller( cx );

            if( !added )
            {
                addToYard( cx );
            }
            if( !added )
            {
                addToRoller( cx );
            }
            if( !added )
            {
                throw new IllegalStateException( "Distribution Centre is full" );
            }
        }
    }

    public void search( String taskLine )
    {

    }

    public void ship( String taskLine )
    {

    }


    /**
     * Tries to find a Dead End with available space.
     * If one is found, the method inserts the Carton.
     * Carton is then mapped into the data structures
     * for later retrieval.
     *
     * @param cx - Carton
     */
    private void addToDead( Carton cx )
    {
        int ii = 0;

        while( ii < dc.getNumStockRooms() && !added )
        {
            if( dc.m_bank[ii] instanceof DeadEnd && !dc.m_bank[ii].isFull() )
            {
                insertCarton( cx, ii );
                mapCarton( cx );
            }

            ii++;
        }
    }


    /**
     * Tries to find a Rolling Corridor with available space.
     * If one is found, the method inserts the Carton.
     * Carton is then mapped into the data structures
     * for later retrieval.
     *
     * @param cx - Carton
     */
    private void addToRoller( Carton cx )
    {
        int ii = 0;

        while( ii < dc.getNumStockRooms() && !added )
        {
            if( dc.m_bank[ii] instanceof RollingCorridor && !dc.m_bank[ii].isFull() )
            {
                insertCarton( cx, ii );
                mapCarton( cx );
            }

            ii++;
        }
    }


    /**
     * Tries to find a Yard with available space.
     * If one is found, the method inserts the Carton.
     * Carton is then mapped into the data structures
     * for later retrieval.
     *
     * @param cx
     */
    private void addToYard( Carton cx )
    {
        int ii = 0;
        int colPosition;

        while( ii < dc.getNumStockRooms() && !added )
        {
            if( dc.m_bank[ii] instanceof Yard && !dc.m_bank[ii].isFull() )
            {
                colPosition = dc.m_bank[ii].insert( cx, true );
                cx.setRowPosition( ii );
                cx.setColumnPosition( colPosition );

                mapCarton( cx );
            }

            ii++;
        }
    }


    private void insertCarton( Carton cx, int index )
    {
        try
        {
            dc.m_bank[index].insert( cx );
            added = true;

            dc.incrementCartons();

            cx.setRowPosition( index );
            cx.setColumnPosition( dc.m_bank[index].count() - 1 );
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
        catch( IllegalStateException e )
        {
            System.out.println( e.getMessage() );
        }

    }

    private void mapCarton( Carton cx )
    {
        String tableKey = String.valueOf( cx.getConsignmentNote() );
        String prodKey = cx.getProductType();
        String sellerKey = cx.getWholesalerName();

        //Hash table
        dc.cartonMap.put( tableKey, cx );

        //Binary search trees
        dc.searchByProduct.insert( prodKey, cx );
        dc.searchByWholesaler.insert( sellerKey, cx );
    }


    private Carton getCarton( StringTokenizer lineTok )
    {
        int consignmentNote;
        String warrantyDate;
        String productType;
        String wholesalerName;

        Carton cx;

        consignmentNote = Integer.parseInt( lineTok.nextToken() );
        warrantyDate = lineTok.nextToken();
        productType = lineTok.nextToken();
        wholesalerName = lineTok.nextToken();

        cx = new Carton( consignmentNote, warrantyDate, productType, wholesalerName );

        return cx;
    }

    public boolean isAdded()
    {
        return added;
    }
}