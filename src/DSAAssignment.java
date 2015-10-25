import java.io.*;

/**
 * Created by chrisgarland on 24/10/15.
 *
 * Contains main()
 */
public class DSAAssignment
{
    public static void main( String[] args )
    {
        try
        {
            String descFilename = args[0];
            String taskFilename = args[1];

            File desc = new File( descFilename );
            File tasks = new File( taskFilename );

            DistributionCentre d1 = new DistributionCentre();

            d1.readDescription( desc );
            d1.receiveCartons();
            d1.buildBanks();
            d1.doTasks( tasks );
            d1.describe( descFilename );
        }
        catch( ArrayIndexOutOfBoundsException e )
        {
            System.out.println( "Must supply 2 files: Description and Task" );
        }
    }
}
