import exceptions.NullObjectReceivedException;
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
        // TODO: Manejar caso si el vertice ya existe (Si dejar así o añadir excepción
        if (vertexMap.get(vtx) != null)
            return false;
        vertexMap.put(vtx, new Vertex(vtx));
        return true;
    }

// TODO: En todas estas funciones, addEdge y addArc, se necesita comprobar si la unión existe, de no existir añadir excepción


    @Override
    public boolean addEdge(E src, E dest) throws NullObjectReceivedException{
        // TODO: Posible excepción si el grafo es ponderado o si nos dan null
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null) throw new NullObjectReceivedException(src);
        if(destV == null) throw new NullObjectReceivedException(dest);

        srcV.neighbours.add(new Pair(destV, null));
        destV.neighbours.add(new Pair(srcV, null));
        return true;
    }




    @Override
    public boolean addEdge(E src, E dest, double weight){
        // TODO: Posible excepción si el grafo es no ponderado o si nos dan null
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null) return false;

        srcV.neighbours.add(new Pair(destV, weight));
        destV.neighbours.add(new Pair(srcV, weight));
        return true;
    }

    @Override
    public boolean addArc(E src, E dest) {
        // TODO: Posible excepción si el grafo es ponderado o si nos dan null
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null) return false;

        srcV.neighbours.add(new Pair(destV, null));
        return true;
    }

    @Override
    public boolean addArc(E src, E dest, double weight) {
        // TODO: Codigo se repite en todos los adds, considerar otra opción
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null) return false;

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
        // TODO: Añadir excepción si alguno de los vertices tiene valor nulo
        Vertex srcV = this.vertexMap.get(src);
        return srcV.neighbours.removeIf(pair -> pair.v.key.equals(dest));
    }

    @Override
    public boolean removeEdge(E src, E dest) {
        // TODO: Añadir excepción si alguno de los vertices tiene valor nulo
        return removeArc(src, dest) && removeArc(dest, src);
    }

    @Override
    public boolean updateArc(E src, E dest, double weight) {
        // TODO: Añadir excepción si alguno de los vertices tiene valor nulo
        Vertex srcV = this.vertexMap.get(src);
        for (Pair pair: srcV.neighbours){
            if (pair.v.key.equals(dest)){
                pair.weight = weight;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateEdge(E src, E dest, double weight) {
        return updateArc(src, dest, weight) && updateArc(dest, src, weight);
    }

    @Override
    public double getArcWeight(E src, E dest) {
        Vertex srcV = this.vertexMap.get(src);
        for (Pair pair: srcV.neighbours){
            if (pair.v.key.equals(dest))
                return pair.weight;
        }
        // TODO: Si se llegó a esta parte, es porque no existe el arc, lanzar excepción
        throw new NullPointerException();
    }

    @Override
    public double getEdgeWeight(E src, E dest) {
        // TODO: Manejar excepción si algún argumento es null
        return getArcWeight(src, dest);
    }

    // Función auxiliar por si se necesita
    private boolean arcExists(E src, E dest){
        Vertex srcV = this.vertexMap.get(src);
        for (Pair pair: srcV.neighbours){
            if (pair.v.key.equals(dest)){
                return true;
            }
        }
        return false;
    }

    public static <E> void dfs(Graph <E> graph) {
    }



}


