import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by chrisgarland on 21/10/15.
 *
 * Reads from description file
 */
public class DescriptionReader
{
    private static final int HASH = 35;
    private static final int PERCENT = 37;

    private int numStockRooms;
    private Scanner fileScanner;                                    //For breaking a file into individual lines


    /**
     * Alternate constructor initialises all
     * member fields.
     *
     * @param descriptionFile - Provided by user
     */
    public DescriptionReader( File descriptionFile )
    {
        try
        {
            fileScanner = new Scanner( descriptionFile );
            numStockRooms = 0;
        }
        catch( IOException e )
        {
            System.out.println( e.getMessage() );
        }
    }


    /**
     * Responsible for breaking the geometry section
     * into individual tokens and storing them in a queue.
     * Uses fileScanner to break file into lines, and lineTokenizer
     * to break each line into tokens.
     *
     * @return - Queue of individual tokens
     */
    public DSAQueue<String> readGeoSection()
    {
        DSAQueue<String> geoQueue = new DSAQueue<String>();         //Filled and returned by this method
        StringTokenizer lineTokenizer;                              //Breaks each line into individual tokens
        String line;                                                //Each line provided by fileScanner
        String token = null;                                        //Individual tokens of a line

        while( fileScanner.hasNextLine() && !"%".equals( token ) )  //Only read geometry section
        {
            line = fileScanner.nextLine();

            if( !line.isEmpty() )                                   //Ignore empty lines
            {
                lineTokenizer = new StringTokenizer( line, ": \\s", true );
                token = lineTokenizer.nextToken();

                do
                {
                    if( token.equals( "#" ) || token.equals( "%" ) )//Ignore comment lines
                    {
                        break;
                    }
                    if( token.equals( "D" ) || token.equals( "R" ) || token.equals( "Y" ) )
                    {
                        numStockRooms++;
                    }

                    //Private method below, catches exceptions
                    tokenEnqueue( token, geoQueue );                //Add token to geoQueue. Private method below

                    token = lineTokenizer.nextToken();              //Grab next token
                }
                while( lineTokenizer.hasMoreTokens() );

                if( !token.equals( "#" ) && !token.equals( "%" ) )
                {
                    tokenEnqueue( token, geoQueue );                //Add last line token to queue
                }
            }
        }

        return geoQueue;
    }

    /**
     * Responsible for breaking the carton section
     * into individual tokens and storing them in a queue.
     * Uses fileScanner to break file into lines, and lineTokenizer
     * to break each line into tokens.
     *
     * @return - Queue of individual tokens
     */
    public DSAQueue<String> readCartonSection()
    {
        DSAQueue<String> cartonQueue = new DSAQueue<String>();      //Filled and returned by method
        StringTokenizer lineTokenizer;                              //Breaks each line into individual tokens
        String line, token;                                         //Each line provided by fileScanner
        char checkToken;                                            //Used as a condition in an if statement

        while( fileScanner.hasNextLine() )
        {
            line = fileScanner.nextLine();

            if( !line.isEmpty() )                                   //Ignore empty lines
            {
                lineTokenizer = new StringTokenizer( line, ":", false );
                token = lineTokenizer.nextToken();
                checkToken = token.charAt( 0 );

                if( checkToken != '#' && checkToken != '%' )    //Ignore commenting lines
                {
                    do
                    {
                        tokenEnqueue( token, cartonQueue );             //Private method below

                        token = lineTokenizer.nextToken();

                    } while( lineTokenizer.hasMoreTokens() );

                    if( !token.equals( "#" ) && !token.equals( "%" ) )
                    {
                        tokenEnqueue( token, cartonQueue );             //Enqueue last token
                    }
                }
            }
        }

        return cartonQueue;
    }


    /**
     * Responsible for pushing a token into a queue
     *
     * @param token - Provided by caller
     * @param inQueue - Provided by caller
     */
    private void tokenEnqueue( String token, DSAQueue<String> inQueue )
    {
        try
        {
            inQueue.enqueue( token );
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


//Accessors:

    /**
     * @return - Number of stockrooms
     */
    public int getNumStockRooms()
    {
        return numStockRooms;
    }



//Cleanup:

    /**
     * Responsible for cleaning up resources
     */
    public void close()
    {
        fileScanner.close();
    }
}
