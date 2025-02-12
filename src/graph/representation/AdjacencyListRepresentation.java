package graph.representation;

import java.util.*;

public class AdjacencyListRepresentation {
    private ArrayList<ArrayList<Integer>> adj;
    private boolean isDirected;
    
    public AdjacencyListRepresentation(int nodes, boolean isDirected) {
        adj = new ArrayList<>(nodes);
        this.isDirected = isDirected;
        for (int i=0; i< nodes; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public AdjacencyListRepresentation(ArrayList<ArrayList<Integer>> edges) {
        adj = edges;
    }
    
    private void addEdge(int i, int j) {
        adj.get(i).add(j);
        if (!isDirected) adj.get(j).add(i); // undirected
    }


    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        ArrayList<Integer> path = new ArrayList<>();
        dfs(adj, 0, visited, path);
        return path;
    }

    private void dfs(ArrayList<ArrayList<Integer>> adj, Integer src, Set<Integer> visited, ArrayList<Integer> path) {
        if(visited.contains(src)) return;

        visited.add(src);
        path.add(src);

        for(Integer neighbour : adj.get(src)) {
            if(!visited.contains(neighbour)) {
                dfs(adj, neighbour, visited, path);
            }
        }
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> pathVisited = new HashSet<>();
        for (int i=0; i< adj.size(); i++) {
            if (!visited.contains(i) && hasCycleDfs(i, visited, pathVisited, 0)){
                System.out.println("CycleDetected: " + pathVisited);
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleDfs(int src, Set<Integer> visited, Set<Integer> pathVisited, int cycleLength) {
        if (pathVisited.contains(src)) {
            System.out.println("CycleLength: " + cycleLength);
            return  true;// cycle found
        }
        if (visited.contains(src)) return false;// visited, no need to traverse again for this node

        pathVisited.add(src);
        visited.add(src);

        for(Integer neighbour : adj.get(src)) {
            if (hasCycleDfs(neighbour, visited, pathVisited, cycleLength)) {
                return true;
            }
        }
        pathVisited.remove(src);
        return false;
    }

    public void printGraph() {
        System.out.println("SIZE: " + adj.size());
        for (int i=0; i< adj.size(); i++) {
            System.out.print(i + " -> ");
            for (int j=0; j< adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyListRepresentation graph = new AdjacencyListRepresentation(5, false);
        graph.addEdge(4, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 0);
        graph.printGraph();
        System.out.println("..................");
        System.out.println("HasCycle: " + graph.hasCycle());
    }
}
