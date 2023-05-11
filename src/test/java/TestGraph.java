import exceptions.NullObjectReceivedException;

public class TestGraph {
    public static void main(String[] args) throws NullObjectReceivedException {
        Graph<Integer> intGraph = new LuigiGraph<>(false);
        intGraph.addVertex(15);  // returns true
        intGraph.addVertex(58);  // returns true
        intGraph.addVertex(7);   // returns true
        intGraph.addVertex(7);   // returns false
        intGraph.addEdge(15, 58); // return true
        intGraph.addArc ( 7, 15); // return true
        intGraph.addEdge(15, 58, 3.6); // return true
        intGraph.addArc ( 7, 15, 2.6); // return true
        intGraph.addEdge(15, 10); // return false
        intGraph.addEdge(15, 58); // return false

    }
}
