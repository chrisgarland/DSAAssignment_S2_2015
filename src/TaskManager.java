import java.util.StringTokenizer;

/**
 * Created by chrisgarland on 25/10/15.
 */
public class TaskManager
{
    private DistributionCentre dc;


    public TaskManager(DistributionCentre distCentre)
    {
        dc = distCentre;
    }


    public void add( String taskLine )
    {
        StringTokenizer lineTok = new StringTokenizer( taskLine, ":", false );
        Carton cx;
        boolean added = false;

        lineTok.nextToken();                                //pop front value off

        cx = getCarton( lineTok );

    }

    public void search( String taskLine )
    {

    }

    public void ship( String taskLine )
    {

    }


    private Carton getCarton( StringTokenizer lineTok )
    {
        int consignmentNote = 0;
        String warrantyDate = null;
        String productType = null;
        String wholesalerName = null;

        Carton cx;

        consignmentNote = Integer.parseInt( lineTok.nextToken() );
        warrantyDate = lineTok.nextToken();
        productType = lineTok.nextToken();
        wholesalerName = lineTok.nextToken();

        cx = new Carton( consignmentNote, warrantyDate, productType, wholesalerName );

        return cx;
    }
}
