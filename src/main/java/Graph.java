public abstract class Graph<E> {
    boolean isWeighted;

    Graph(boolean isWeighted) {
        this.isWeighted = isWeighted;
    }

    public abstract boolean addVertex(E obj);
    public abstract boolean addEdge(E src, E dest);
    public abstract boolean addEdge(E src, E dest, double weight);
    public abstract boolean addArc (E src, E dest);
    public abstract boolean addArc (E src, E dest, double weight);
    public abstract int vertexCount();
    // public abstract int edgeCount();
    public abstract boolean removeVertex(E obj);
    public abstract boolean removeArc (E src, E dest);
    public abstract boolean removeEdge(E src, E dest);
    public abstract boolean updateArc (E src, E dest, double weight);
    public abstract boolean updateEdge(E src, E dest, double weight);
    public abstract double  getArcWeight (E src, E dest);
    public abstract double  getEdgeWeight(E src, E dest);
}
