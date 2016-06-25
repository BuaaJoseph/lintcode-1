package com.lintcode.americanbigcompany;

import java.util.ArrayList;

public class Graph {
	public ArrayList<DirectedGraphNode> neighbors;
	public int label;
	
	Graph(){
		neighbors = new ArrayList<DirectedGraphNode>();
	}
	
	public DirectedGraphNode addRoot(int x){
		DirectedGraphNode Graph = new DirectedGraphNode(x);
		Graph.neighbors = new ArrayList<DirectedGraphNode>();
		return Graph;
	}
	
	public void addNeighbor(DirectedGraphNode d,int x){
		DirectedGraphNode g = new DirectedGraphNode(x);
		g.neighbors = new ArrayList<DirectedGraphNode>();
		d.neighbors.add(g);
	}
	
	public void addNeigs(DirectedGraphNode d,int[] A){
		for(int i=0;i<A.length;i++){
			DirectedGraphNode g = new DirectedGraphNode(A[i]);
			g.neighbors = new ArrayList<DirectedGraphNode>();
			d.neighbors.add(g);
		}
	}
}
