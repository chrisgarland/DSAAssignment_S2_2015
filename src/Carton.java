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
        validateParameters( consignmentNote, productType, wholesalerName, warrantyDate );

        this.consignmentNote = consignmentNote;
        this.productType = productType;
        this.wholesalerName = wholesalerName;
        this.warrantyDate = warrantyDate;
    }

    private void validateParameters( int consignmentNote, String productType, String wholesalerName, String warrantyDate )
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
