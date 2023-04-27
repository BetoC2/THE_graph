import java.util.ArrayList;

public class Vertex {

    private String data;
    private ArrayList<Edge> edges;

    //CONSTRUCTORS
    public Vertex(String inputData){
        this.data = inputData;
        this.edges = new ArrayList<Edge>();
    }

    // METHODS
    public void addEdge(Vertex endVertex, Double weight){
        this.edges.add(new Edge(this,endVertex,weight));

    }

    public void removeEdge(Vertex endVertex){
        // Removes edge if in edges (ArrayList), edge is directed to the same endVertex
        this.edges.removeIf(edge -> edge.getEndVertex().equals(endVertex));
    }

    public String getData(){
        return this.data;
    }

    public ArrayList<Edge> getEdges(){
        return this.edges;
    }



}
