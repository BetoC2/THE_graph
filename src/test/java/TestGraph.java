
public class TestGraph {
    public static void main(String[] args) {
        Graph busNetwork = new Graph(true,true);
        Vertex lopezStation = busNetwork.addVertex("Lopez Mateos");
        Vertex laNormalStation = busNetwork.addVertex("La Normal");

        busNetwork.addEdge(lopezStation,laNormalStation,100.0);

        busNetwork.print();
    }
}
