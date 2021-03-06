/**
 * Created by chrisgarland on 15/09/15.
 */
public class Carton
{
    private static final String NO_EXPIRY = "0000-00-00";
    private static final int NO_POSITION = -1;

    private int consignmentNote;
    private String productType;
    private String wholesalerName;
    private String warrantyDate;
    private int rowPosition;
    private int columnPosition;

    /**
     * Alternate constructor calls validateParameters() before
     * initialising all member fields.
     *
     * @param consignmentNote - Initial value.
     * @param productType     - Initial value.
     * @param wholesalerName  - Initial value.
     * @param warrantyDate    - Initial value.
     */
    public Carton( int consignmentNote, String productType, String wholesalerName, String warrantyDate )
    {
        try
        {
            validateParameters( consignmentNote, productType, wholesalerName, warrantyDate );
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }

        setConsignmentNote( consignmentNote );
        setProductType( productType );
        setWholesalerName( wholesalerName );
        setWarrantyDate( warrantyDate );
        setRowPosition( NO_POSITION );
        setColumnPosition( NO_POSITION );
    }

//Accessors:

    public int getConsignmentNote()
    {
        return consignmentNote;
    }

    public String getProductType()
    {
        return productType;
    }

    public String getWholesalerName()
    {
        return wholesalerName;
    }


    public String getWarrantyDate()
    {
        return warrantyDate;
    }

    public int getRowPosition()
    {
        return rowPosition;
    }

    public int getColumnPosition()
    {
        return columnPosition;
    }

//Mutators: Exceptions are handled in constructor

    public void setConsignmentNote( int consignmentNote )
    {
        this.consignmentNote = consignmentNote;
    }


    public void setProductType( String productType )
    {
        this.productType = productType;
    }

    public void setWholesalerName( String wholesalerName )
    {
        this.wholesalerName = wholesalerName;
    }

    public void setWarrantyDate( String warrantyDate )
    {
        this.warrantyDate = warrantyDate;
    }

    public void setRowPosition( int rowPosition )
    {
        this.rowPosition = rowPosition;
    }

    public void setColumnPosition( int columnPosition )
    {
        this.columnPosition = columnPosition;
    }


//Public supporting methods:

    /**
     * If warrantyDate is "0000-00-00" (NO_EXPIRY),
     * instance has lifetime warranty, return true.
     * Else return false.
     *
     * @return - true/false
     */
    public boolean hasLifetimeWarranty()
    {
        return getWarrantyDate().equals( NO_EXPIRY );
    }


//Private supporting methods:

    /**
     * Purely for exception handling purposes.
     *
     * @param consignmentNote - Asserts int > 0 && < 1024.
     * @param productType     - Asserts String not null && not empty.
     * @param wholesalerName  - Asserts String not null && not empty.
     * @param warrantyDate    - Asserts String not null && not empty.
     * @throws IllegalArgumentException
     */
    private void validateParameters( int consignmentNote,
                                     String productType,
                                     String wholesalerName,
                                     String warrantyDate
    ) throws IllegalArgumentException
    {
        if( consignmentNote < 0 || consignmentNote == 0 )
        {
            throw new IllegalArgumentException( "Consignment Note must be greater than 0" );
        }
        if( consignmentNote == 1024 || consignmentNote > 1024 )
        {
            throw new IllegalArgumentException( "Consignment Note must be less than 1024" );
        }

        if( productType == null || productType.isEmpty() )
        {
            throw new IllegalArgumentException( "No Product Type provided" );
        }

        if( wholesalerName == null || wholesalerName.isEmpty() )
        {
            throw new IllegalArgumentException( "No Wholesaler Name provided" );
        }

        if( warrantyDate == null || warrantyDate.isEmpty() )
        {
            throw new IllegalArgumentException( "No Warranty Date provided" );
        }
    }

    @Override
    public String toString()
    {
        return  consignmentNote + ":" + warrantyDate + ":" + productType + ":" + wholesalerName;
    }
}
