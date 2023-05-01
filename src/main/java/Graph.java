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
    //VERTEX
    public Vertex addVertex(String data){
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void removeVertex(Vertex vertex){
        this.vertices.remove(vertex);
    }
    //EDGE
    public void addEdge(Vertex vertex1, Vertex vertex2, Double weight){
        if(!this.isWeighted){
            weight = null;
        }
        //Adds edge to first vertex(Direction)
        vertex1.addEdge(vertex2, weight);
        if(!this.isDirected) vertex2.addEdge(vertex1,weight); //Considering that graph isn´t directed, it establishes a way back through an edge
    }

    public void removeEdge(Vertex vertex1, Vertex vertex2){
        vertex1.removeEdge(vertex2);
        if(!this.isDirected) vertex2.removeEdge(vertex1);
    }

    //GETTERS
    public ArrayList<Vertex> getVertices(){
        return this.vertices;
    }
    public Vertex getVertexByValue(String value){
        for(Vertex vertex:this.vertices){
            if(vertex.getData() == value){
                return vertex;
            }
        }
        return null; //Considerar excepción en este caso
    }

    public boolean isWeighted(){
        return this.isWeighted;
    }

    public boolean isDirected(){
        return this.isDirected;
    }

    public void print(){
        for(Vertex vertex:this.vertices){
            vertex.print(isWeighted);
        }
    }


}
