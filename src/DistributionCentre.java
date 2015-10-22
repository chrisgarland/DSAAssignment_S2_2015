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

        DSAQueue<String> geoQueue = dr.readGeoSection();
        DSAQueue<String> cartonQueue = dr.readCartonSection();

        numStockRooms = dr.getNumStockRooms();
        numCartons = dr.getNumCartons();

        dr.close();
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
