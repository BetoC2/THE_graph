import java.net.DatagramPacket;
import java.util.ArrayList;

public class MarioGraph<E> extends Graph<E>{
    private boolean[][] adjBoolMatrix; //Boolean Adjancecy Matrix
    private Double[][] adjWeightMatrix; //Double Adjancecy Matrix
    private ArrayList<E> vertices;

    MarioGraph(int numVrx,boolean isWeighted){
        super(isWeighted);
        if (!isWeighted)
            this.adjBoolMatrix = new boolean[numVrx][numVrx]; //numVrx = Number of Vertex

        if(isWeighted)
            this.adjWeightMatrix = new Double[numVrx][numVrx]; //numVrx = Number of Vertex

        this.vertices = new ArrayList<>();
    }

    @Override
    public boolean addVertex(E vtx) {
        if(this.vertices.contains(vtx))
            return false;
        this.vertices.add(vtx);
        return true;
    }

    @Override
    public boolean addEdge(E index1, E index2) {
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(!adjBoolMatrix[ind1][ind2] && !adjBoolMatrix[ind2][ind1]){
            adjBoolMatrix[ind1][ind2] = true;
            adjBoolMatrix[ind2][ind1] = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(E index1, E index2, double weight) {
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(adjWeightMatrix[ind1][ind2] == 0.0 && adjWeightMatrix[ind2][ind1] == 0.0){
            adjWeightMatrix[ind1][ind2] = weight;
            adjWeightMatrix[ind2][ind1] = weight;
            return true;
        }
        return false;
    }

    @Override
    public boolean addArc(E index1, E index2, double weight) {
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(adjWeightMatrix[ind1][ind2] == 0.0){
            adjWeightMatrix[ind1][ind2] = weight;
            return true;
        }
        return false;
    }

    @Override
    public boolean addArc(E index1, E index2){
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(!adjBoolMatrix[ind1][ind2]){
            adjBoolMatrix[ind1][ind2] = true;
            return true;
        }
        return false;
    }

    @Override
    public int vertexCount() {
        return this.vertices.size();
    }

    @Override
    public boolean removeVertex(E vtx) {
        if(vertices.contains(vtx)){

            int ind = vertices.indexOf(vtx);
            if(super.isWeighted){
                for(int i = 0; i < vertices.size(); i++){
                    for(int j = ind; j < vertices.size() - 1; j++){
                        adjWeightMatrix[i][j] = adjWeightMatrix[i][j++];
                    }
                }
                for(int i = ind; i < vertices.size() - 1; i++){
                    for(int j = 0; j < vertices.size(); j++){
                        adjWeightMatrix[i][j] = adjWeightMatrix[i++][j];
                    }
                }
                for(int i = 0; i < vertices.size(); i++){
                    adjWeightMatrix[i][vertices.size() - 1] = null;
                }
                for(int i = 0; i < vertices.size(); i++){
                    adjWeightMatrix[vertices.size() - 1][i] = null;
                }
                vertices.remove(vtx);
                return true;
            }
            if(!super.isWeighted){
                for(int i = 0; i < vertices.size(); i++){
                    for(int j = ind; j < vertices.size() - 1; j++){
                        adjBoolMatrix[i][j] = adjBoolMatrix[i][j++];
                    }
                }
                for(int i = ind; i < vertices.size() - 1; i++){
                    for(int j = 0; j < vertices.size(); j++){
                        adjBoolMatrix[i][j] = adjBoolMatrix[i++][j];
                    }
                }
                for(int i = 0; i < vertices.size(); i++){
                    adjBoolMatrix[i][vertices.size() - 1] = false;
                }
                for(int i = 0; i < vertices.size(); i++){
                    adjBoolMatrix[vertices.size() - 1][i] = false;
                }
                vertices.remove(vtx);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeArc(E index1, E index2) {
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(adjBoolMatrix[ind1][ind2]){
            adjBoolMatrix[ind1][ind2] = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(E index1, E index2) {
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(adjBoolMatrix[ind1][ind2] && adjBoolMatrix[ind2][ind1]){
            adjBoolMatrix[ind1][ind2] = false;
            adjBoolMatrix[ind2][ind1] = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean updateArc(E index1, E index2, double weight) {
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(weight >= 0.0){
            adjWeightMatrix[ind1][ind2] = weight;
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEdge(E index1, E index2, double weight) {
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(weight >= 0.0){
            adjWeightMatrix[ind1][ind2] = weight;
            adjWeightMatrix[ind2][ind1] = weight;
            return true;
        }
        return false;
    }

    @Override
    public Double getArcWeight(E index1, E index2) {
        if(index1.equals(index2))
            return 0.0;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        return adjWeightMatrix[ind1][ind2];
    }

    @Override
    public Double getEdgeWeight(E index1, E index2) {
        if(index1.equals(index2))
            return 0.0;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        return adjWeightMatrix[ind1][ind2];
    }

    // Función auxiliar por si se necesita
    private boolean arcExists(E index1, E index2){
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(adjWeightMatrix[ind1][ind2] > 0.0) return true;
        if(adjBoolMatrix[ind1][ind2]) return true;
        return false;
    }


}
