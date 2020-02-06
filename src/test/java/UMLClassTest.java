import shrugUML.ShrugUMLClass;

public class UMLClassTest
{
    public static void main (String[] args)
    {

	
	// Default ctor test
	ShrugUMLClass myClass = new ShrugUMLClass ();
	if (myClass.getName () == null)
	    System.out.println ("Default ctor: Pass");

	// Name ctor test
	myClass = new ShrugUMLClass ("myClass");
	if (myClass.getName () == "myClass")
	    System.out.println ("Name ctor: Pass");

	// getName test
	if (myClass.getName () == "myClass")
	    System.out.println ("getName (): Pass");

	// setName test
	myClass.setName ("myClass2");
	if (myClass.getName () == "myClass2")
	    System.out.println ("setName (): Pass");
		
	    
    }
}
