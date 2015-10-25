import java.util.StringTokenizer;

/**
 * Created by chrisgarland on 25/10/15.
 * <p/>
 * Responsible for managing all tasks
 */
public class TaskManager
{
    private DistributionCentre dc;
    private boolean added;


    /**
     * Alternate constructor
     *
     * @param distCentre -
     */
    public TaskManager( DistributionCentre distCentre )
    {
        dc = distCentre;
        added = false;
    }


    /**
     * Responsible for adding cartons
     *
     * @param taskLine
     */
    public void add( String taskLine )
    {
        StringTokenizer lineTok = new StringTokenizer( taskLine, ":", false );
        Carton cx;
        added = false;

        lineTok.nextToken();                                //Move past first token: "A"

        cx = getCarton( lineTok );

        System.out.println( "============\nAdd:\n" );

        if( cx.hasLifetimeWarranty() )
        {
            addToDead( cx );                                //priority

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
            addToRoller( cx );                              //priority

            if( !added )
            {
                addToYard( cx );
            }
            if( !added )
            {
                addToDead( cx );
            }
            if( !added )
            {
                throw new IllegalStateException( "Distribution Centre is full" );
            }
        }

        System.out.println( "\n============" );
    }

    /**
     * Responsible for searching
     *
     * @param taskLine -
     */
    public void search( String taskLine )
    {
        StringTokenizer lineTok = new StringTokenizer( taskLine, ":", true );
        CartonSearcher searcher;
        String date = "";
        String product = "";
        String seller = "";
        String token;
        boolean hasConsignment = false;
        boolean hasDate = false;
        boolean hasProduct = false;
        boolean hasSeller = false;
        int consignment = -1;

        lineTok.nextToken();                                //Move past first token: "S"
        lineTok.nextToken();                                //Move past second token: ":"

        try
        {
            token = lineTok.nextToken();
            if( !token.equals( ":" ) )
            {
                consignment = Integer.valueOf( token );
                hasConsignment = true;
                lineTok.nextToken();
            }

            token = lineTok.nextToken();
            if( !token.equals( ":" ) )
            {
                date = token;
                hasDate = true;
                lineTok.nextToken();
            }

            token = lineTok.nextToken();
            if( !token.equals( ":" ) )
            {
                product = token;
                hasProduct = true;
                lineTok.nextToken();
            }

            if( lineTok.hasMoreTokens() )
            {
                seller = lineTok.nextToken();
                hasSeller = true;
            }
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
        catch( IllegalStateException e )
        {
            System.out.println( e.getMessage() );
        }

        searcher = new CartonSearcher( dc );

        System.out.println( "===========================================\nSearch:\n" );

        if( hasConsignment )
        {
            searcher.consignmentSearch( consignment );
        }

        if( hasProduct && !hasDate )
        {
            searcher.productSearch( product );
        }
        else if( hasProduct && hasDate )
        {
            searcher.productSearch( product, date );
        }

        if( hasSeller && !hasDate )
        {
            searcher.sellerSearch( seller );
        }
        else if( hasSeller && hasDate )
        {
            searcher.sellerSearch( seller, date );
        }

        System.out.println( "\n===========================================" );
    }

    /**
     * Unimplemented
     *
     * @param taskLine
     */
    public void ship( String taskLine )
    {
        throw new UnsupportedOperationException( "Operation has not been implemented..." );
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

                System.out.print( cx.getRowPosition() + ":" + cx.getColumnPosition() );

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

                System.out.print( cx.getRowPosition() + ":" + cx.getColumnPosition() );

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

                System.out.print( cx.getRowPosition() + ":" + cx.getColumnPosition() );

                mapCarton( cx );
            }

            ii++;
        }
    }


    /**
     * Does the actual inserting.
     *
     * @param cx    -
     * @param index -
     */
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


    /**
     * Places carton in hassh table and two binary trees
     * for later retrieval.
     *
     * @param cx - Carton to be placed
     */
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


    /**
     * Creates and returns a carton
     *
     * @param lineTok -
     * @return -
     */
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

        cx = new Carton( consignmentNote, productType, wholesalerName, warrantyDate );

        return cx;
    }

    public boolean isAdded()
    {
        return added;
    }
}
