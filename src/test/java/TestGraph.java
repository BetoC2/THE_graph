
public class TestGraph {
    public static void main(String[] args) {
        Graph<String> busNetwork = new Graph<>(true,true);
        Vertex<String> lopezStation = busNetwork.addVertex("Lopez Mateos");
        Vertex<String> laNormalStation = busNetwork.addVertex("La Normal");
        Vertex<String> periSurStation = busNetwork.addVertex("Periférico sur");
        Vertex<String> periNorteStation = busNetwork.addVertex("Periférico norte");
        Vertex<String> juarezStation = busNetwork.addVertex("Juarez");


        busNetwork.addEdge(lopezStation,laNormalStation,100.0);
        busNetwork.addEdge(laNormalStation,laNormalStation,15000.0);
        busNetwork.addEdge(periSurStation,laNormalStation,11.0);
        busNetwork.addEdge(periNorteStation,laNormalStation,1110.1234);
        busNetwork.addEdge(juarezStation,laNormalStation,1.0);

        busNetwork.print();
        System.out.println(busNetwork);

        busNetwork.removeVertex(laNormalStation);
        System.out.print("\nSe supone que se eliminó el vértice laNormalStation\n\n");
        System.out.println(busNetwork);
    }
}
