import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestDriver
{
    public static void main (String[] args)
    {
	// Run UMLDiagram tests
	Result diagramResult = JUnitCore.runClasses(TestUMLDiagram.class);
	
	for (Failure failure : diagramResult.getFailures()) {
	    System.out.println(failure.toString());
	}

	// Run UMLClass tests
	Result classResult = JUnitCore.runClasses(TestUMLClass.class);
	
	for (Failure failure : classResult.getFailures()) {
	    System.out.println(failure.toString());
	}
	
    }
}
