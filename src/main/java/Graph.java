import java.util.ArrayList;

public class Graph<E> {

    private ArrayList<Vertex<E>> vertices;
    private final boolean isWeighted, isDirected;

    //CONSTRUCTORS
    public Graph(boolean isWeighted, boolean isDirected){
        this.vertices = new ArrayList<>();
        this.isWeighted = isWeighted;
        this.isDirected = isDirected;
    }

    //METHODS
    //VERTEX
    public Vertex<E> addVertex(E data){
        Vertex<E> newVertex = new Vertex<>(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void removeVertex(Vertex<E> vertex){
        this.vertices.remove(vertex);
    }
    //EDGE
    public void addEdge(Vertex<E> vertex1, Vertex<E> vertex2, Double weight){
        if(!this.isWeighted){
            weight = null;
        }
        //Adds edge to first vertex(Direction)
        vertex1.addEdge(vertex2, weight);
        if(!this.isDirected) vertex2.addEdge(vertex1,weight); //Considering that graph isn´t directed, it establishes a way back through an edge
    }

    public void removeEdge(Vertex<E> vertex1, Vertex<E> vertex2){
        vertex1.removeEdge(vertex2);
        if(!this.isDirected) vertex2.removeEdge(vertex1);
    }

    //GETTERS
    public ArrayList<Vertex<E>> getVertices(){
        return this.vertices;
    }
    public Vertex<E> getVertexByValue(String value){
        for(Vertex<E> vertex:this.vertices){
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

    // todo: Lo del toString x2
    public void print(){
        for(Vertex<E> vertex:this.vertices){
            vertex.print(isWeighted);
        }
    }


}
