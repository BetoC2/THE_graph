import exceptions.NullObjectReceivedException;
import exceptions.WrongGraphMethodException;

public class TestGraph {
    public static void main(String[] args) throws NullObjectReceivedException, WrongGraphMethodException {
        LuigiGraph<Integer> intGraph = new LuigiGraph<>(false);
        System.out.println(intGraph.addVertex(15));  // returns true
        System.out.println(intGraph.addVertex(58));  // returns true
        System.out.println(intGraph.addVertex(7));   // returns true
        System.out.println(intGraph.addVertex(7));   // returns false
        System.out.println(intGraph.addEdge(15, 58)); // return true
        System.out.println(intGraph.addArc ( 7, 15)); // return true
        try {
            System.out.println(intGraph.addEdge(15, 58, 3.6)); // return true
        }
        catch (WrongGraphMethodException Ex) {
            System.out.println(Ex);
        }
        try {
            System.out.println(intGraph.addArc(7, 15, 2.6)); // return true
        }
        catch (WrongGraphMethodException Ex) {
            System.out.println(Ex);
        }
        System.out.println(intGraph.addEdge(15, 10)); // return false
        System.out.println(intGraph.addEdge(15, 58)); // return false
    }
}
