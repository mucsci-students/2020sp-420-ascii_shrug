import org.junit.*;
import static org.junit.Assert.assertEquals;
import shrugUML.*;

public class TestJunit
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
	
}
