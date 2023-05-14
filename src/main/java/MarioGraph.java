import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MarioGraph<E> extends Graph<E>{
    private Boolean[][] adjBoolMatrix; //Boolean Adjancecy Matrix
    private Double[][] adjWeightMatrix; //Double Adjancecy Matrix
    private ArrayList<E> vertices;
    private boolean[] visited;

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
            //TODO: poner valor 0 a los indices [x][x]
            this.adjWeightMatrix = new Double[numVrx][numVrx]; //numVrx = Number of Vertex
        this.vertices = new ArrayList<>();
        visited = new boolean[numVrx];

    }

    @Override
    public boolean addVertex(E vtx) {
        if(super.isWeighted){
            if(vertices.size() == adjWeightMatrix.length) return false;
        }
        if(!super.isWeighted){
            if(vertices.size() == adjBoolMatrix.length) return false;
        }
        if(this.vertices.contains(vtx))
            return false;
        this.vertices.add(vtx);
        return true;
    }

    @Override
    public boolean addEdge(E index1, E index2) {
        if(super.isWeighted) return false;
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(ind1 == -1 || ind2 == -1){
            return false;
        }
        if(!adjBoolMatrix[ind1][ind2] || !adjBoolMatrix[ind2][ind1]){
            adjBoolMatrix[ind1][ind2] = true;
            adjBoolMatrix[ind2][ind1] = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(E index1, E index2, double weight) {
        if(!super.isWeighted) return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(index1.equals(index2))
            return false;
        if(ind1 == -1 || ind2 == -1){
         return false;
        }
        //TODO: Valor 0.0 es valido
        if(adjWeightMatrix[ind1][ind2] == null || adjWeightMatrix[ind2][ind1] == null){
            adjWeightMatrix[ind1][ind2] = weight;
            adjWeightMatrix[ind2][ind1] = weight;
            return true;
        }
        return false;
    }

    @Override
    public boolean addArc(E index1, E index2, double weight) {
        if(!super.isWeighted) return false;
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(ind1 == -1 || ind2 == -1){
            return false;
        }
        if(adjWeightMatrix[ind1][ind2] == null){
            adjWeightMatrix[ind1][ind2] = weight;
            return true;
        }
        return false;
    }

    @Override
    public boolean addArc(E index1, E index2){
        if(super.isWeighted) return false;
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(ind1 == -1 || ind2 == -1){
            return false;
        }
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
        if(ind1 == -1 || ind2 == -1){
            return false;
        }
        if(!super.isWeighted){
            if(adjBoolMatrix[ind1][ind2]){
                adjBoolMatrix[ind1][ind2] = false;
                return true;
            }
        }
        if(super.isWeighted){
            if(adjWeightMatrix[ind1][ind2] == null){ //adjWeightMatrix[ind1][ind2] != null ||
                adjWeightMatrix[ind1][ind2] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeEdge(E index1, E index2) {
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(ind1 == -1 || ind2 == -1){
            return false;
        }
        if(!super.isWeighted){
            if(adjBoolMatrix[ind1][ind2] && adjBoolMatrix[ind2][ind1]){
                adjBoolMatrix[ind1][ind2] = false;
                adjBoolMatrix[ind2][ind1] = false;
                return true;
            }
        }
        if(super.isWeighted){
            if((adjWeightMatrix[ind1][ind2].equals(adjWeightMatrix[ind2][ind1])) && (adjWeightMatrix[ind1][ind2] != null)){
                adjWeightMatrix[ind1][ind2] = null;
                adjWeightMatrix[ind2][ind1] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateArc(E index1, E index2, double weight) {
        if(!super.isWeighted) return false;
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(ind1 == -1 || ind2 == -1){
            return false;
        }
        if(adjWeightMatrix[ind1][ind2] != null){
            adjWeightMatrix[ind1][ind2] = weight;
                return true;
            }
        return false;
    }

    @Override
    public boolean updateEdge(E index1, E index2, double weight) {
        if(!super.isWeighted) return false;
        if(index1.equals(index2))
            return false;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(ind1 == -1 || ind2 == -1){
            return false;
        }
        if((adjWeightMatrix[ind1][ind2].equals(adjWeightMatrix[ind2][ind1])) && (adjWeightMatrix[ind1][ind2] != null)){
            adjWeightMatrix[ind1][ind2] = weight;
            adjWeightMatrix[ind2][ind1] = weight;
            return true;
        }
        return false;
    }

    @Override
    public Double getArcWeight(E index1, E index2) {
        if (!super.isWeighted) return null;
        if (index1.equals(index2))
            return null;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if (ind1 == -1 || ind2 == -1) {
            return null;
        }
        return adjWeightMatrix[ind1][ind2];
    }

    @Override
    public Double getEdgeWeight(E index1, E index2) {
        if(!super.isWeighted) return null;
        if(index1.equals(index2))
            return null;
        int ind1 = this.vertices.indexOf(index1);
        int ind2 = this.vertices.indexOf(index2);
        if(ind1 == -1 || ind2 == -1){
            return null;
        }
        return adjWeightMatrix[ind1][ind2];
    }


    public void DFS(E src) {
        if (!vertices.contains(src)) {
            throw new IllegalArgumentException("El vértice no existe en el grafo.");
        }

        System.out.println("\nDFS");
        // Creamos una pila y la inicializamos con el vértice inicial
        Stack<E> stack = new Stack<>();
        int srcIndex = vertices.indexOf(src);
        visited[srcIndex] = true;
        stack.push(src);

        // Realizamos DFS iterativamente con una pila
        while (!stack.isEmpty()) {
            // Sacamos el vértice superior de la pila y lo visitamos
            E curr = stack.pop();
            System.out.print(curr + " ");

            // Agregamos a la pila todos los vecinos no visitados del vértice
            if (this.isWeighted) {
                for (int i = 0; i < adjWeightMatrix.length; i++) {
                    if (adjWeightMatrix[vertices.indexOf(curr)][i] != null && !visited[i]) {
                        visited[i] = true;
                        stack.push(vertices.get(i));
                    }
                }
            } else {
                for (int i = 0; i < adjBoolMatrix.length; i++) {
                    if (adjBoolMatrix[vertices.indexOf(curr)][i] == true && !visited[i]) {
                        visited[i] = true;
                        stack.push(vertices.get(i));
                    }
                }
            }
        }
    }


    public void BFS(E src){
        if (!vertices.contains(src)) {
            throw new IllegalArgumentException("El vértice no existe en el grafo.");
        }

        System.out.println("BFS");

        int srcIndex = vertices.indexOf(src);
        visited = new boolean[visited.length];
        visited[srcIndex] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(srcIndex);

        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.print(vertices.get(current) + " ");

            if(this.isWeighted){
                for (int i = 0; i < adjWeightMatrix.length; i++) {
                    if (adjWeightMatrix[current][i] != null && !visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
            else{
                for (int i = 0; i < adjBoolMatrix.length; i++) {
                    if (adjBoolMatrix[current][i] == true && !visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
        }

    }


    @Override
    public String toString(){
        /*
        StringBuilder sb =  new StringBuilder();
        sb.append()
         */
        return super.toString();
    }

    public void print(){
        System.out.print(" ");
        for(E vertex : vertices){
            System.out.printf("%s ",vertex.toString());
        }
        System.out.println();

        //TODO: signo infinito en null
        if(super.isWeighted){
            for(int i = 0; i < adjWeightMatrix.length; i++){
                //System.out.printf("%s ",vertices.get(i));
                for(int j = 0; j < adjWeightMatrix[i].length; j++){
                    if(adjWeightMatrix[i][j] == null) System.out.print("0.0 ");
                    else{
                        System.out.printf("%.1f ",adjWeightMatrix[i][j]);
                    }
                }
                System.out.println();
            }
        }


        if(!super.isWeighted){
            for(int i = 0; i < adjBoolMatrix.length; i++){
                //System.out.printf("%S ",vertices.get(i));
                for(int j = 0; j < adjBoolMatrix[i].length; j++){
                    if(adjBoolMatrix[i][j]) System.out.print("T ");
                    if(!adjBoolMatrix[i][j]) System.out.print("F ");
                }
                System.out.println();
            }
        }
    }


}
