/**
 * Created by chrisgarland on 20/10/15.
 */
public class DistributionCentre
{
    private int numStockRooms, numCartons;

    StockRoom[] m_bank;


    /**
     * Default constructor initialises
     * member fields to default values
     */
    public DistributionCentre()
    {
        numStockRooms = 0;
        numCartons = 0;
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
