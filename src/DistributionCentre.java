import java.io.File;

/**
 * Created by chrisgarland on 20/10/15.
 */
public class DistributionCentre
{
    private int numStockRooms, numCartons;
    private DSAQueue<String> geoQueue;
    private DSAQueue<String> cartonQueue;
    StockRoom[] m_bank;


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
        numCartons = dr.getNumCartons();

        dr.close();
    }


    public void receiveCartons()
    {
        DSAHashTable cartonMap = new DSAHashTable( 2048 );

        while( !cartonQueue.isEmpty() )
        {
            Carton cx = getCarton();
        }
    }


    /**
     * Responsible for extraxting the data for a single
     * carton out of cartonQueue. Creates the carton and
     * returns it.
     *
     * @return - Newly created carton
     */
    private Carton getCarton()
    {
        //Data for instantiating a carton
        int consignmentNote = 0;
        String productType = null;
        String wholesalerName = null;
        String warrantyDate = null;

        Carton cx = null;

        try                                                     //Extract carton data
        {
            consignmentNote = Integer.parseInt( cartonQueue.dequeue() );
            productType = cartonQueue.dequeue();
            wholesalerName = cartonQueue.dequeue();
            warrantyDate = cartonQueue.dequeue();
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

}
