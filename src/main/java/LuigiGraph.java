import exceptions.NullObjectReceivedException;
import exceptions.WrongGraphMethodException;

import java.util.*;

public class LuigiGraph<E> extends Graph<E>{

    private final Map<E, Vertex> vertexMap;

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
        boolean visited;
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


    @Override
    public boolean addEdge(E src, E dest){
        if (this.isWeighted)
            return false;
        return addEdgeHelper(src, dest, null);
    }


    @Override
    public boolean addEdge(E src, E dest, double weight){
        if (!this.isWeighted)
            return false;
        return addEdgeHelper(src, dest, weight);
    }

    private boolean addEdgeHelper(E src, E dest, Double weight){
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null)
            return false;

        boolean srcDestArc = arcExists(src, dest);
        boolean destSrcArc = arcExists(dest, src);

        if (srcDestArc && destSrcArc)
            return false;

        if (srcDestArc && !destSrcArc) {
            destV.neighbours.add(new Pair(srcV, weight));
            for (Pair pair: srcV.neighbours){
                if (pair.v.key.equals(dest))
                    pair.weight = weight;
            }
        }
        else if(!srcDestArc && destSrcArc) {
            srcV.neighbours.add(new Pair(destV, weight));
            for (Pair pair : destV.neighbours) {
                if (pair.v.key.equals(dest))
                    pair.weight = weight;
            }
        }
        else {
            srcV.neighbours.add(new Pair(destV, weight));
            destV.neighbours.add(new Pair(srcV, weight));
        }
        return true;
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

    @Override
    public boolean addArc(E src, E dest){
        if (this.isWeighted)
            return false;
         return addArcHelper(src, dest, null);
    }

    @Override
    public boolean addArc(E src, E dest, double weight) {
        if (!this.isWeighted)
            return false;
        return addArcHelper(src, dest, weight);
    }

    private boolean addArcHelper(E src, E dest, Double weight){
        Vertex srcV = vertexMap.get(src);
        Vertex destV = vertexMap.get(dest);

        if (srcV == null || destV == null) return false;

        if (arcExists(src, dest))
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
        if (src == null || dest == null)
            return false;

        Vertex srcV = this.vertexMap.get(src);
        return srcV.neighbours.removeIf(pair -> pair.v.key.equals(dest));
    }

    @Override
    public boolean removeEdge(E src, E dest) {
        return removeArc(src, dest) || removeArc(dest, src);
    }

    @Override
    public boolean updateArc(E src, E dest, double weight) {
        if (!this.isWeighted)
            return false;

        if (src == null || dest == null)
            return false;

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
        if (arcExists(src, dest) && arcExists(dest, src))
            return updateArc(src, dest, weight) && updateArc(dest, src, weight);
        return false;
    }

    @Override
    public Double getArcWeight(E src, E dest) {
        if (src == null || dest == null)
            return null;

        Vertex srcV = this.vertexMap.get(src);
        for (Pair pair: srcV.neighbours){
            if (pair.v.key.equals(dest))
                return pair.weight;
        }
        return null;
    }

    @Override
    public Double getEdgeWeight(E src, E dest) {
        if (src == null || dest == null)
            return null;

        if (arcExists(src, dest) && arcExists(dest, src))
            return getArcWeight(src, dest);

        return null;
    }

    public void DFS(E src){ //Find all vertexes from a origin Vertex that can be accessed
        if (!vertexMap.containsKey(src)) {
            throw new IllegalArgumentException("El vértice no existe en el grafo.");
        }
        System.out.println("DFS:");
        Vertex startVertex = vertexMap.get(src);
        resetVisited();
        Pair startPair = new Pair(startVertex, null);
        recursiveDFS(startPair);
    }

    private void recursiveDFS(Pair pair){
        pair.v.visited = true;
        System.out.print(pair.v.key + " ");
        for(Pair neighbor: pair.v.neighbours){
            if (!neighbor.v.visited) {
                recursiveDFS(neighbor);
            }
        }
    }


    public void BFS(E src) {
        if (!vertexMap.containsKey(src)) {
            throw new IllegalArgumentException("El vértice no existe en el grafo.");
        }
        System.out.println("\nBFS");
        resetVisited();

        // Cola para almacenar los vértices que se van a visitar
        Queue<Vertex> queue = new LinkedList<>();

        // Marca el vértice de inicio como visitado y lo añade a la cola
        Vertex startVertex = vertexMap.get(src);
        startVertex.visited = true;
        queue.add(startVertex);

        // Ciclo para visitar los vértices en la cola
        while (!queue.isEmpty()) {
            // Obtiene el siguiente vértice en la cola
            Vertex currentVertex = queue.poll();
            System.out.print(currentVertex.key + " ");

            // Visita los vecinos del vértice actual y los añade a la cola si aún no han sido visitados
            for (Pair neighbor : currentVertex.neighbours) {
                if (!neighbor.v.visited) {
                    neighbor.v.visited = true;
                    queue.add(neighbor.v);
                }
            }
        }
    }

    private void resetVisited() {   // Método para reiniciar el estado de visita de todos los vértices
        for (Vertex vertex : vertexMap.values()) {
            vertex.visited = false;
        }
    }

    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append("Impresión de grafo:\n");
        for(Vertex vertex: vertexMap.values()){
            st.append(vertex.key);
            st.append("-> ");
            for(Pair pair: vertex.neighbours){
                st.append(pair.v.key);
                if (this.isWeighted) st.append(String.format(" (%.2f)",pair.weight));
                st.append(", ");
            }
            st.append("\n");
        }
        return st.toString();
    }
} 