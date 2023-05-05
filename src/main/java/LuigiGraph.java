import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuigiGraph<E> extends Graph<E>{

    private Map<E, Vertex> vertexMap;

    LuigiGraph(boolean isWeighted) {
        super(isWeighted);
        this.vertexMap = new HashMap<>();
    }

    private class Pair {
        Vertex v;
        Double weight;
        public Pair(Vertex v, Double weight)
        {
            this.v = v;
            this.weight = weight;
        }
    }

    private class Vertex {
        E key;
        List<Pair> neighbours;
        public Vertex(E key){
            this.key = key;
            this.neighbours = new ArrayList<>();
        }
    }

    @Override
    public boolean addVertex(E vtx) {
        if (vertexMap.get(vtx) != null)
            return false;
        vertexMap.put(vtx, new Vertex(vtx));
        return true;
    }

// TODO: Comprimir estas funciones en 2, utilizando auxiliar tipo
//  return addEdge(src, dest, null);

    @Override
    public boolean addEdge(E src, E dest) {
        // TODO: Posible excepción si el grafo es ponderado o si nos dan null
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null)
            return false;

        srcV.neighbours.add(new Pair(destV, null));
        destV.neighbours.add(new Pair(srcV, null));
        return true;
    }

    @Override
    public boolean addEdge(E src, E dest, double weight) {
        // TODO: Posible excepción si el grafo es no ponderado o si nos dan null
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null)
            return false;

        srcV.neighbours.add(new Pair(destV, weight));
        destV.neighbours.add(new Pair(srcV, weight));
        return true;
    }

    @Override
    public boolean addArc(E src, E dest) {
        // TODO: Posible excepción si el grafo es ponderado o si nos dan null
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null)
            return false;

        srcV.neighbours.add(new Pair(destV, null));
        return true;
    }

    @Override
    public boolean addArc(E src, E dest, double weight) {
        // TODO: Posible excepción si el grafo es no ponderado o si nos dan null
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null)
            return false;

        srcV.neighbours.add(new Pair(destV, weight));
        return true;
    }

    @Override
    public int vertexCount() {
        return vertexMap.size();
    }

    @Override
    public boolean removeVertex(E vtx) {
        if (vertexMap.get(vtx) == null)
            return false;

        for (Vertex vertex: vertexMap.values()){
            vertex.neighbours.removeIf(pair -> pair.v.key.equals(vtx));
        }
        vertexMap.remove(vtx);
        return true;
    }

    @Override
    public boolean removeArc(E src, E dest) {
        return false;
    }

    @Override
    public boolean removeEdge(E src, E dest) {
        return false;
    }

    @Override
    public boolean updateArc(E src, E dest, double weight) {
        return false;
    }

    @Override
    public boolean updateEdge(E src, E dest, double weight) {
        return false;
    }

    @Override
    public double getArcWeight(E src, E dest) {
        return 0;
    }

    @Override
    public double getEdgeWeight(E src, E dest) {
        return 0;
    }
}
