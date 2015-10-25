import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
            ostr = new FileOutputStream( getOutputFilename( inFileName ) );
            pw = new PrintWriter( ostr );
        }
        catch( FileNotFoundException e )
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    public void writeDescription()
    {
        pw.println("# DC Geometry Section\n");

        for( int ii = 0; ii < dc.m_bank.length; ii++ )
        {
            pw.println(dc.m_bank[ii].describe());
        }

        pw.println("\n%\n");

        pw.println("# Carton Description Section\n");

        for( int ii = 0; ii < dc.m_bank.length; ii++ )
        {
            pw.print( dc.m_bank[ii].printCartons() );
        }

        pw.print( "\n# End of File" );

        closeStream();

        System.out.println("\n-output file written");
    }


    /**
     * Responsible for closing the file output stream.
     */
    private void closeStream()
    {
        pw.close();

        try
        {
            ostr.close();
        }
        catch( IOException e )
        {
            System.out.println("Error closing FileOutputStream" + e.getMessage());
        }
    }


    /**
     * Responsible for appending the filename.
     * Takes original filename and appends "-output"
     * at the end.
     *
     * @param inFileName - Provided by constructor
     * @return - Appended filename
     */
    private String getOutputFilename( String inFileName )
    {
        return inFileName.split( "[.]" )[0] + "-output";                      //Cuts off file extension if there is one
    }
}
