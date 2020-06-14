package com.ranjit.algo.datastructures.unionfind;

public class CycleInUnDirectedGraph {

	public static void main(String[] args) {
		CycleInUnDirectedGraph cs = new CycleInUnDirectedGraph();
		System.out.println(cs.isCycle(4, new int[][] {{0,1},{0,2},{1,3},{2,3}}));
	}
	
	public boolean isCycle(int vertices, int[][] edges) {
        UnionFind uf = new UnionFind(vertices);
        for(int i = 0; i < edges.length; i++) {
        	if(!uf.isunifyable(edges[i][0], edges[i][1]))
        		return true;
        	
        }
        return false;
    }
	
}
