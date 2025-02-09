package graph;

import java.util.*;

public class DisjointSet {
    List<Integer> rank;
    List<Integer> parent;

    public DisjointSet(int n) {
        rank = new ArrayList<>();
        parent = new ArrayList<>();

        for (int i= 0; i<=n; i++) {
            rank.add(0);
            parent.add(i);
        }
    }

    public int findParent(int node) {
        if (node == parent.get(node)) return node;
        // return findParent(parent.get(node)); recursive ==> O(logN)

        int representative = findParent(parent.get(node));
        parent.set(node, representative);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int rep_u = findParent(u);
        int rep_v = findParent(v);
        if (rep_u == rep_v) return; // they already belongs to same component, no need to join
        else if (rank.get(rep_u) < rank.get(rep_v)) { // smaller will attach to bigger component
            parent.set(rep_u, rep_v);            
        } else if (rank.get(rep_v) < rank.get(rep_u)){ // R(v) < R(u)
            parent.set(rep_v, rep_u);
        } else { // if same Rank
            parent.set(rep_u, rep_v);
            int rankU = rank.get(rep_u);
            rank.set(rep_u, rankU + 1);
        }
    }

    public void unionBySize(int u, int v) {
        //......
    }

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);
        //ds.unionByRank(3, 7);

        // if 3 and 7 same or not?
        if (ds.findParent(3) == ds.findParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

        ds.unionByRank(3, 7);
        if (ds.findParent(3) == ds.findParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }
    }


}
