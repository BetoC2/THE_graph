
public class TestGraph {
    public static void main(String[] args) {
        Graph<String> busNetwork = new Graph<>(true,true);
        Vertex<String> lopezStation = busNetwork.addVertex("Lopez Mateos");
        Vertex<String> laNormalStation = busNetwork.addVertex("La Normal");

        busNetwork.addEdge(lopezStation,laNormalStation,100.0);

        busNetwork.print();
    }
}
