import java.io.*;

/**
 * Created by chrisgarland on 24/10/15.
 */
public class DSAAssignment
{
    public static void main( String[] args )
    {
        try
        {
            String filename = args[0];
            File desc = new File( filename );
            DistributionCentre d1 = new DistributionCentre();

            d1.readDescription( desc );
            d1.receiveCartons();
            d1.buildBanks();
            d1.describe( filename );
        }
        catch(ArrayIndexOutOfBoundsException e )
        {
            System.out.println("Must supply 1 file: Description ");
        }
    }
}
