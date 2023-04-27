import java.util.ArrayList;

public class Graph {

    private ArrayList<Vertex> vertices;
    private boolean isWeighted, isDirected;

    //CONSTRUCTORS
    public Graph(boolean isWeighted, boolean isDirected){
        this.vertices = new ArrayList<Vertex>();
        this.isWeighted = isWeighted;
        this.isDirected = isDirected;
    }

    //METHODS
    public Vertex addVertex(String data){
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2, Double weight){
        if(!this.isWeighted){
            weight = null;
        }
        //Adds edge to first vertex(Direction)
        vertex1.addEdge(vertex2, weight);
        if(!this.isDirected){ //Considering that graph isn´t directed, it establishes a way back through an edge
            vertex2.addEdge(vertex1,weight);
        }

    }

    //NEXT METHOD -> removeEdge
}
