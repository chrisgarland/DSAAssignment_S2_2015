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
            File desc = new File( args[0] );
            DistributionCentre d1 = new DistributionCentre();
            d1.readDescription( desc );
            d1.receiveCartons();
            d1.buildBanks();

            for( int ii = 0; ii < d1.m_bank.length; ii++ )
            {
                System.out.println( d1.m_bank[ii].describe() );
            }

            System.out.println("\n%\n");

            for( int ii = 0; ii < d1.m_bank.length; ii++ )
            {
                System.out.print(d1.m_bank[ii].printCartons());
            }
        }
        catch(ArrayIndexOutOfBoundsException e )
        {
            System.out.println("Must supply 1 file: Description ");
        }
    }
}
