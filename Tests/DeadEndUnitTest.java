import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chrisgarland on 26/08/15.
 */
public class DeadEndUnitTest
{
    private final Integer INTEGER_1 = 1;
    private final Integer INTEGER_2 = 2;
    private final String NAME_STRING = "Chris Garland";

    DeadEnd default_stack;
    DeadEnd alternate_stack;

    @Before
    public void setUp() throws Exception
    {
        default_stack = new DeadEnd();
        alternate_stack = new DeadEnd( 10 );

//        default_stack.insert( INTEGER_1 );
//        default_stack.insert( INTEGER_2 );
//        default_stack.insert( NAME_STRING );
    }

    @After
    public void tearDown() throws Exception
    {
        default_stack = null;
        alternate_stack = null;
    }


    @Test
    public void testPop() throws Exception
    {
        assertEquals( 3, default_stack.count() );

        assertEquals( NAME_STRING, default_stack.remove() );
        assertEquals( INTEGER_2, default_stack.remove() );

        assertEquals( 1, default_stack.count() );
    }

    @Test
    public void testTop() throws Exception
    {
        assertEquals( NAME_STRING, default_stack.peek() );

        assertEquals( 3, default_stack.count() );
    }

    @Test
    public void testCount() throws Exception
    {
        assertEquals( 3, default_stack.count() );
        assertEquals( 0, alternate_stack.count() );
    }

    @Test
    public void testGetMaxElements() throws Exception
    {
        assertEquals( 100, default_stack.getMaxElements() );
        assertEquals( 10, alternate_stack.getMaxElements() );
    }

    @Test
    public void testIsEmpty() throws Exception
    {
        assertFalse( default_stack.isEmpty() );
        assertTrue( alternate_stack.isEmpty() );
    }

    @Test
    public void testIsFull() throws Exception
    {
        fill_Alternate_Stack();
        assertTrue( alternate_stack.isFull() );

        assertFalse( default_stack.isFull() );
    }

    private void fill_Alternate_Stack()
    {
        for( Integer ii = 0; ii < 10; ii++ )
        {
//            alternate_stack.insert( ii );
        }
    }
}