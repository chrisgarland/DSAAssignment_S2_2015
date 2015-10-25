import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by chrisgarland on 20/10/15.
 */
public class DistributionCentre
{
    private int numStockRooms, numCartons;
    private DSAQueue<String> geoQueue;                              //Holds tokens from the geo section of desc file
    private DSAQueue<String> cartonQueue;                           //Holds tokens from the carton section of file
    StockRoom[] m_bank;                                             //Master array for holding all stock rooms
    DSAHashTable cartonMap;                                         //For storing cartons as they are created
    BinarySearchTree searchByProduct;
    BinarySearchTree searchByWholesaler;


    /**
     * Default constructor initialises
     * member fields to default values
     */
    public DistributionCentre()
    {

        numStockRooms = 0;
        numCartons = 0;
        geoQueue = new DSAQueue<String>();
        cartonQueue = new DSAQueue<String>();
        m_bank = null;
        cartonMap = new DSAHashTable( 2048 );                       //Twice the size of max num cartons
        searchByProduct = new BinarySearchTree();
        searchByWholesaler = new BinarySearchTree();
    }

    /**
     * This method is responsible for reading the
     * warehouse description file. Stores the geometry
     * section in a geoQueue, and the carton section in
     * a cartonQueue. Creates a DescriptionReader object
     * to assist with reading the file.
     *
     * @param descriptionFile - Provided by user
     */
    public void readDescription( File descriptionFile )
    {
        DescriptionReader dr = new DescriptionReader( descriptionFile );

        geoQueue = dr.readGeoSection();                             //Individual tokens of the geometry section
        cartonQueue = dr.readCartonSection();                       //Individual tokens of the Carton section

        numStockRooms = dr.getNumStockRooms();

        dr.close();
    }


    /**
     * Responsible for creating cartons and storing them
     * inside a hash table. Also increments numCartons
     */
    public void receiveCartons()
    {
        while( !cartonQueue.isEmpty() )
        {
            Carton cx = getCarton();                                //Private method below

            try
            {
                String key = String.valueOf( cx.getConsignmentNote() );//Use (String)consignmentNote as key

                cartonMap.put( key, cx );                           //Map carton
                numCartons++;
            }
            catch( IllegalArgumentException e )
            {
                System.out.println( e.getMessage() );
            }
            catch( IllegalStateException e )
            {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Responsible for building the storage rooms.
     * Calls upon BankBuilder{} to do so. All storage
     * rooms are stored in m_bank (master array).
     *
     * @throws IllegalArgumentException
     */
    public void buildBanks() throws IllegalArgumentException
    {
        String temp;
        char bankType;

        m_bank = new StockRoom[numStockRooms];                      //Master array for storing stock rooms

        BankBuilder bankBuilder = new BankBuilder(geoQueue, cartonMap, m_bank, searchByProduct, searchByWholesaler);

        for( int index = 0; index < numStockRooms; index++ )
        {
            temp = geoQueue.dequeue().toUpperCase();                //Case insensitive
            bankType = temp.charAt( 0 );

            switch( bankType )
            {
                case 'D':
                    bankBuilder.buildDead( index );
                    break;

                case 'R':
                    bankBuilder.buildRoller( index );
                    break;

                case 'Y':
                    bankBuilder.buildYard( index );
                    break;

                default:
                    throw new IllegalArgumentException( "Error building Store Rooms" );

            }
        }
    }


    public void doTasks( File taskFile )
    {
        Scanner fileScanner = null;

        try
        {
            fileScanner = new Scanner( taskFile );
            fileScanner.useDelimiter( "[:]" );

            TaskManager taskManager = new TaskManager( this );
            String taskLine;
            char taskChar;

            while( fileScanner.hasNextLine() )
            {
                taskLine = fileScanner.nextLine();

                if( taskLine.equals( "" ) )
                {
                    break;
                }

                taskChar = taskLine.split( "[:]" )[0].charAt( 0 );

                switch( taskChar )
                {
                    case 'A':
                        try
                        {
                            taskManager.add( taskLine );
                        }
                        catch( IllegalStateException e )
                        {
                            System.out.println( e.getMessage() );
                        }
                        finally
                        {
                            break;
                        }

                    case 'S':
                        taskManager.search( taskLine );
                        break;

                    case 'R':
                        try
                        {
                            System.out.println( "===========================================\nShip/Remove:\n" );
                            taskManager.ship( taskLine );
                        }
                        catch( UnsupportedOperationException e )
                        {
                            System.out.println(e.getMessage());
                        }
                        finally
                        {
                            System.out.println( "\n===========================================" );
                            break;
                        }

                    default:
                        System.out.println("Error reading task file");
                }
            }
        }
        catch( FileNotFoundException e )
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if( fileScanner != null )
            {
                fileScanner.close();
            }

        }
    }


    public void describe( String inFileName )
    {
        DescriptionWriter dw = new DescriptionWriter( inFileName, this );
        dw.writeDescription();
    }


    /**
     * Responsible for extracting the data for a single
     * carton out of cartonQueue. Creates the carton and
     * returns it.
     *
     * @return - Newly created carton
     */
    private Carton getCarton()
    {
        //Data for instantiating a carton
        int consignmentNote = 0;
        String warrantyDate = null;
        String productType = null;
        String wholesalerName = null;

        Carton cx = null;

        try                                                     //Extract carton data
        {
            consignmentNote = Integer.parseInt( cartonQueue.dequeue() );
            warrantyDate = cartonQueue.dequeue();
            productType = cartonQueue.dequeue();
            wholesalerName = cartonQueue.dequeue();
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
        catch( IllegalStateException e )
        {
            System.out.println(e.getMessage());
        }

        try                                                     //Create carton
        {
            cx = new Carton( consignmentNote, productType, wholesalerName, warrantyDate );
        }
        catch( IllegalArgumentException e )
        {
            System.out.println(e.getMessage());
        }

        return cx;
    }


//Accessors:


    /**
     * @return - Number of stockrooms
     */
    public int getNumStockRooms()
    {
        return numStockRooms;
    }

    /**
     * @return - Number of cartons
     */
    public int getNumCartons()
    {
        return numCartons;
    }


//Mutators:

    public void incrementCartons()
    {
        numCartons++;
    }

}
