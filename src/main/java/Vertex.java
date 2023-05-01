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

    //GETTERS
    public String getData(){
        return this.data;
    }

    public ArrayList<Edge> getEdges(){
        return this.edges;
    }

    public void print(boolean showWeight){
        String message = "";
        if(this.edges.size() == 0){
            System.out.println(this.data + " --> ");
            return;
        }
        for(int i=0;i<this.edges.size();i++){
            if(i==0) message+=this.edges.get(i).getStartVertex().data+" --> ";
            message+=this.edges.get(i).getEndVertex().getData();
            if(showWeight) message+=" ("+this.edges.get(i).getWeight()+")";
            if(i != this.edges.size()-1) message+=", ";
        }
        System.out.println(message);

    }



}
