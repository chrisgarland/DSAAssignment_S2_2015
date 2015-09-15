/**
 * Created by chrisgarland on 15/09/15.
 */
public class Carton
{
    private int consignmentNote;
    private String productType;
    private String wholesalerName;
    private String warrantyDate;

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


//Accessors: Exceptions are handled in constructor

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


    /**
     * Purely for exception handling purposes.
     *
     * @param consignmentNote - Asserts int > 0 && < 1024.
     * @param productType - Asserts String not null && not empty.
     * @param wholesalerName - Asserts String not null && not empty.
     * @param warrantyDate - Asserts String not null && not empty.
     * @throws IllegalArgumentException
     */
    private void validateParameters( int consignmentNote,
                                     String productType,
                                     String wholesalerName,
                                     String warrantyDate ) throws IllegalArgumentException
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
}
