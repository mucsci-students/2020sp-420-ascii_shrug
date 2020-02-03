import shrugUML.shrugUMLDiagram;

public class UMLDiagramTest
{
    public static void main (String[] args)
    {
	// Default ctor test
	shrugUMLDiagram myDiagram = new shrugUMLDiagram ();
	
	// Add test - on empty diagram
	if (myDiagram.addClass ("newClass") == true)
	    System.out.println ("Add test - empty diagram: Pass");
	// Add test - repeating name
	if (myDiagram.addClass ("newClass") == false)
	    System.out.println ("Add test - repeating name: Pass");
	// Add test - non empty diagram
	if (myDiagram.addClass ("class2") == true)
	    System.out.println ("Add test - non empty diagram: Pass");

	// Remove test - valid name
	if (myDiagram.removeClass ("class2") == true)
	    System.out.println ("Remove test - valid name: Pass");
	// Remove test - invalid name
	if (myDiagram.removeClass ("notAClass") == false)
	    System.out.println ("Remove test - invalid name: Pass");
       
    }
}
