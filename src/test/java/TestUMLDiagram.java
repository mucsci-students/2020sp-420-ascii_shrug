import org.junit.*;
import static org.junit.Assert.*;
import shrugUML.*;
import java.util.ArrayList;

public class TestUMLDiagram
{
    @Test
    public void testUMLNullCtor ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	assertEquals (testDiagram.nameInDiagram ("notAClass"), false);

    }

    @Test
    public void testUMLDiagramClassArrayCtor ()
    {
	ShrugUMLClass[] classes;
	classes = new ShrugUMLClass[]{new ShrugUMLClass ("Class1"),
				      new ShrugUMLClass ("Class2")};
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram (classes);
	assertEquals (testDiagram.nameInDiagram ("Class1"), true);
	assertEquals (testDiagram.nameInDiagram ("Class2"), true);
	
    }

    @Test
    public void testAddClass ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	assertEquals (testDiagram.addClass ("newClass"), true);    
    }

    @Test
    public void testRepeatAddClass ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	assertEquals (testDiagram.addClass ("newClass"), true);
	assertEquals (testDiagram.addClass ("newClass"), false);
    }

    @Test
    public void testValidRemoveClass ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	assertEquals (testDiagram.addClass ("newClass"), true);
	assertEquals (testDiagram.removeClass ("newClass"), true);
    }
	
    @Test
    public void testInvalidRemoveClass ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	assertEquals (testDiagram.removeClass ("newClass"), false);
    }

    @Test
    public void testValidNameInDiagram ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	testDiagram.addClass ("newClass");
	assertEquals (testDiagram.nameInDiagram ("newClass"), true);
    }

    @Test
    public void testInvalidNameInDiagram ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	testDiagram.addClass ("newClass");
	assertEquals (testDiagram.nameInDiagram ("newClass2"), false);
    }

    @Test
    public void testValidFindClass ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	testDiagram.addClass ("newClass");
	assertEquals (testDiagram.findClass ("newClass"),
		      testDiagram.getClasses ().get (0));
    }

    @Test
    public void testInvalidFindClass ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	testDiagram.addClass ("newClass");
	assertTrue (testDiagram.findClass ("newClass2") != testDiagram.getClasses ().get (0));
    }

    @Test
    public void testGetDiagram ()
    {
	ShrugUMLDiagram testDiagram = new ShrugUMLDiagram ();
	testDiagram.addClass ("a");
	testDiagram.addClass ("b");
	testDiagram.addClass ("c");
	ArrayList<ShrugUMLClass> diagram = testDiagram.getClasses ();
	assertTrue (diagram.get (0).getName () == "a");
	assertTrue (diagram.get (1).getName () == "b");
	assertTrue (diagram.get (2).getName () == "c");
    }
}
