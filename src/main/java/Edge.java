public class Edge {

    private Vertex startVertex, endVertex;
    private Double weight;

    //CONSTRUCTORS
    public Edge(Vertex startV, Vertex endV, Double inputWeight){
        this.startVertex = startV;
        this.endVertex = endV;
        this.weight = inputWeight;
    }

    //GETTERS
    public Vertex getStartVertex() {return this.startVertex;}
    public Vertex getEndVertex() {return this.endVertex;}
    public Double getWeight() {return this.weight;}



}
