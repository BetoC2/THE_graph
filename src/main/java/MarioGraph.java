import java.net.DatagramPacket;
import java.util.ArrayList;

public class MarioGraph<E> extends Graph<E>{
    private Boolean[][] adjBoolMatrix; //Boolean Adjancecy Matrix
    private Double[][] adjWeightMatrix; //Double Adjancecy Matrix
    private ArrayList<E> vertices;

    MarioGraph(int numVrx,boolean isWeighted){
        super(isWeighted);
        if (!isWeighted) {
            this.adjBoolMatrix = new Boolean[numVrx][numVrx];
            for (int i = 0; i < adjBoolMatrix.length; i++){
                for(int j = 0; j < adjBoolMatrix.length; j++){
                    adjBoolMatrix[i][j] = false;
                }
            }//numVrx = Number of Vertex
        }
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
        // TODO: Regresar falso si alguno es -1
        if(!adjBoolMatrix[ind1][ind2] && !adjBoolMatrix[ind2][ind1]){
            adjBoolMatrix[ind1][ind2] = true;
            adjBoolMatrix[ind2][ind1] = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(E index1, E index2, double weight) {
        // TODO: regresar falso si es no ponderado
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

    private void swapVertical(Object[][] matrix,int indObj, int lenght){
        for(int i = 0; i < lenght; i++){
            for(int j = indObj; j < lenght - 1; j++){
                matrix[i][j] = matrix[i][j++];
            }
        }
    }

    private void swapHorizontal(Object[][] matrix,int indObj, int length){
        for(int i = indObj; i < length - 1; i++){
            for(int j = 0; j < length; j++){
                matrix[i][j] = matrix[i][j++];
            }
        }
    }

    private void fillNulls(Object[][] matrix, int length){
        for(int i = 0; i < length; i++){
            matrix[i][length-1] = null;
        }
        for(int i = 0; i < length; i++){
            adjWeightMatrix[length][i] = null;
        }
    }

    @Override
    public boolean removeVertex(E vtx) {
        if(vertices.contains(vtx)){
            int ind = vertices.indexOf(vtx);
            int length = vertices.size();
            if(super.isWeighted){
                swapVertical(adjWeightMatrix,ind,length);
                swapHorizontal(adjWeightMatrix,ind,length);
                fillNulls(adjWeightMatrix,length);
                vertices.remove(vtx);
                return true;
            }
            if(!super.isWeighted){
                swapVertical(adjBoolMatrix,ind,length);
                swapHorizontal(adjBoolMatrix,ind,length);
                fillNulls(adjBoolMatrix,length);
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

    @Override
    public String toString(){
        return super.toString();
    }

    public void print(){
        System.out.print(" ");
        for(E vertex : vertices){
            System.out.print(vertex.toString() + " ");
        }
        System.out.println();

        if(super.isWeighted){
            for(int i = 0; i < adjWeightMatrix.length; i++){
                System.out.print(vertices.get(i) + "  ");
                for(int j = 0; j < adjWeightMatrix[i].length; j++){
                    System.out.print(adjWeightMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
        if(!super.isWeighted){
            for(int i = 0; i < adjBoolMatrix.length; i++){
                System.out.print(vertices.get(i) + "  ");
                for(int j = 0; j < adjBoolMatrix[i].length; j++){
                    System.out.print(adjBoolMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }


}
