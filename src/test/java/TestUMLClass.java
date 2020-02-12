import org.junit.*;
import static org.junit.Assert.*;
import shrugUML.*;


public class TestUMLClass
{
    @Test
    public void testClassDefaultCtor ()
    {
	ShrugUMLClass myClass = new ShrugUMLClass ();
	assertEquals (myClass.getName (), null);
    }

    @Test
    public void testClassNameCtor ()
    {
	ShrugUMLClass myClass = new ShrugUMLClass ("a");
	assertEquals (myClass.getName (), "a");
    }

    @Test
    public void testGetName ()
    {
	ShrugUMLClass myClass = new ShrugUMLClass ("a");
	assertEquals (myClass.getName (), "a");	
    }

    @Test
    public void testSetName ()
    {
	ShrugUMLClass myClass = new ShrugUMLClass ("a");
	assertEquals (myClass.getName (), "a");
	myClass.setName ("b");
	assertEquals (myClass.getName (), "b");
    }
}
