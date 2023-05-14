import exceptions.NullObjectReceivedException;
import exceptions.WrongGraphMethodException;

import java.util.function.DoubleFunction;

public class TestGraph {
    public static void main(String[] args) throws NullObjectReceivedException, WrongGraphMethodException {
        MarioGraph<Integer> intGraph = new MarioGraph<>(5,false);
        System.out.println(intGraph.addVertex(15));  // returns true
        //System.out.println(intGraph.addVertex(15));  // returns false
        System.out.println(intGraph.addVertex(58));  // returns true
        System.out.println(intGraph.addVertex(7));   // returns true
        /*System.out.println(intGraph.addVertex(81));   // returns false
        System.out.println(intGraph.addVertex(701));   // returns false
        System.out.println(intGraph.addVertex(209));   // returns false
        System.out.println(intGraph.addVertex(71));   // returns false
        System.out.println(intGraph.addVertex(10));   // returns false*/


        System.out.println(intGraph.addEdge(15, 58)); // return true
        System.out.println(intGraph.addEdge ( 7, 15)); // return true
        System.out.println(intGraph.addArc ( 7, 58)); // return true
        /*System.out.println(intGraph.addEdge(15, 209)); // return true
        System.out.println(intGraph.addEdge(7, 15)); // return true
        System.out.println(intGraph.addEdge(701, 81)); // return true
        System.out.println(intGraph.addEdge(81, 7)); // return true
        System.out.println(intGraph.addEdge(209, 15)); // return true*/


        //System.out.println(intGraph.addEdge(15, 58, 3.6)); // return false
        //System.out.println(intGraph.addArc(7, 15, 2.6)); // return false

        //System.out.println(intGraph.addEdge(15, 10)); // return false
        //System.out.println(intGraph.addEdge(15, 58)); // return false
        /*LuigiGraph<Integer> intG = new LuigiGraph<>(true);
        intG.addVertex(1);
        intG.addVertex(2);
        intG.addVertex(3);
        intG.addEdge(1,2,100);
        intG.addEdge(1,3,100);*/

        intGraph.print();
        intGraph.DFS(15);
        System.out.println("\n");
        intGraph.BFS(15);

        //intG.BFS(1000);
        //intGraph.DFS(15);
        //intGraph.BFS(10000);
        //intGraph.print();
        //intGraph.DFS(15);
    }
}
