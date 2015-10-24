import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chrisgarland on 26/08/15.
 */
public class RollingCorridorUnitTest
{
    private final Integer INTEGER_1 = 1;
    private final Integer INTEGER_2 = 2;
    private final String NAME_STRING = "Chris Garland";

    RollingCorridor default_queue;
    RollingCorridor alternate_queue;

    @Before
    public void setUp() throws Exception
    {
        default_queue = new RollingCorridor();
        alternate_queue = new RollingCorridor( 10 );

//        default_queue.insert( INTEGER_1 );
//        default_queue.insert( INTEGER_2 );
//        default_queue.insert( NAME_STRING );
    }

    @After
    public void tearDown() throws Exception
    {
        default_queue = null;
        alternate_queue = null;
    }

    @Test
    public void testDequeue() throws Exception
    {
        assertEquals( 3, default_queue.count() );

        assertEquals( INTEGER_1, default_queue.remove() );
        assertEquals( INTEGER_2, default_queue.remove() );

        assertEquals( 1, default_queue.count() );
    }

    @Test
    public void testPeek() throws Exception
    {
        assertEquals( INTEGER_1, default_queue.peek() );
        assertEquals( 3, default_queue.count() );
    }

    @Test
    public void testGetMaxElements() throws Exception
    {
        assertEquals( 100, default_queue.getMaxElements() );
        assertEquals( 10, alternate_queue.getMaxElements() );
    }

    @Test
    public void testCount() throws Exception
    {
        assertEquals( 3, default_queue.count() );
        assertEquals( 0, alternate_queue.count() );
    }

    @Test
    public void testIsEmpty() throws Exception
    {
        assertFalse( default_queue.isEmpty() );
        assertTrue( alternate_queue.isEmpty() );
    }

    @Test
    public void testIsFull() throws Exception
    {
        fill_Alternate_Queue();
        assertTrue( alternate_queue.isFull() );

        assertFalse( default_queue.isFull() );

    }

    private void fill_Alternate_Queue()
    {
        for( Integer ii = 0; ii < 10; ii++ )
        {
//            alternate_queue.insert( ii );
        }
    }
}