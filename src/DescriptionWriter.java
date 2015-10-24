import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by chrisgarland on 24/10/15.
 */
public class DescriptionWriter
{
    FileOutputStream ostr = null;
    PrintWriter pw;
    DistributionCentre dc;


    public DescriptionWriter( String inFileName, DistributionCentre inDc )
    {
        dc = inDc;

        try
        {
            ostr = new FileOutputStream( outputFilename( inFileName ) );
            pw = new PrintWriter( ostr );
        }
        catch( FileNotFoundException e )
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private String outputFilename( String inFileName )
    {
        return null;
    }
}
