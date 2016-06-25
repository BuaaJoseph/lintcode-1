package com.lintcode.americanbigcompany;

public class Tree {
	public TreeNode root,left,right;
	public int val;
	
	Tree(){
		root = left = right = null;
	}
	
	public boolean isEmpty(){
		return root==null;
	}
	
	public void addRoot(int x){
		TreeNode temp = new TreeNode(x);
		if(root==null)
			root= temp;
	}
	
	public TreeNode addNode(int x){
		TreeNode node = new TreeNode(x);
		node.left = null;
		node.right = null;
		return node;
	}
	
	public void addleft(TreeNode node,int x){
		node.left = addNode(x);
	}
	
	public void addright(TreeNode node,int x){
		node.right = addNode(x);
	}
}
