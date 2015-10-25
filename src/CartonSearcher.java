/**
 * Created by chrisgarland on 26/10/15.
 *
 * Supporting class. Uses the data structures to find
 * appropriate cartons.
 */
public class CartonSearcher
{
    DistributionCentre dc;


    /**
     * Alternate constructor
     *
     * @param inCentre -
     */
    public CartonSearcher( DistributionCentre inCentre )
    {
        dc = inCentre;
    }


    /**
     * Responsible for searching by consignment note
     *
     * @param consignment - key
     */
    public void consignmentSearch( int consignment )
    {
        Carton cx;
        String key = String.valueOf( consignment );

        try
        {
            cx = dc.cartonMap.get( key );

            System.out.println(cx.getRowPosition() + ":" + cx.getColumnPosition() + ":" + cx.toString());
        }
        catch( IllegalStateException e )
        {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Gets product array from product tree.
     * Prints out all cartons of a given product.
     *
     * @param product -
     */
    public void productSearch( String product )
    {
        int ii = 0;
        Carton[] productArray;

        try
        {
            productArray = dc.searchByProduct.find( product );

            while( productArray[ii] != null )
            {
                Carton cx = productArray[ii];

                System.out.println( cx.getRowPosition() + ":" + cx.getColumnPosition() + ":" + cx.toString() );

                ii++;
            }
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }

    }


    /**
     * Gets product array from product tree.
     * Prints out all cartons of a given product,
     * where has exp. date and exp. date < given date.
     *
     * @param product -
     * @param date    - Find where exp. date is < this date
     */
    public void productSearch( String product, String date )
    {
        int ii = 0;
        Carton[] productArray;

        try
        {
            productArray = dc.searchByProduct.find( product );

            while( productArray[ii] != null && !productArray[ii].hasLifetimeWarranty() )
            {
                Carton cx = productArray[ii];

                if( date.compareTo( cx.getWarrantyDate() ) >= 0 )
                {
                    System.out.println( cx.getRowPosition() + ":" + cx.getColumnPosition() + ":" + cx.toString() );
                }

                ii++;
            }
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
    }


    /**
     * Gets product array from product tree.
     * Prints out all cartons of a given wholesaler.
     *
     * @param seller -
     */
    public void sellerSearch( String seller )
    {
        int ii = 0;
        Carton[] productArray;

        try
        {
            productArray = dc.searchByWholesaler.find( seller );

            while( productArray[ii] != null )
            {
                Carton cx = productArray[ii];

                System.out.println( cx.getRowPosition() + ":" + cx.getColumnPosition() + ":" + cx.toString() );

                ii++;
            }
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
    }


    /**
     * Gets product array from product tree.
     * Prints out all cartons of a given wholesaler,
     * where has exp. date and exp. date < given date.
     *
     * @param seller -
     * @param date   - Find where exp. date is < this date
     */
    public void sellerSearch( String seller, String date )
    {
        int ii = 0;
        Carton[] productArray;

        try
        {
            productArray = dc.searchByWholesaler.find( seller );

            while( productArray[ii] != null && !productArray[ii].hasLifetimeWarranty() )
            {
                Carton cx = productArray[ii];

                if( date.compareTo( cx.getWarrantyDate() ) >= 0 )
                {
                    System.out.println( cx.getRowPosition() + ":" + cx.getColumnPosition() + ":" + cx.toString() );
                }

                ii++;
            }
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
    }

}
