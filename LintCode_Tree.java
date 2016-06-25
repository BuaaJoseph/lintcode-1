package com.lintcode.americanbigcompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LintCode_Tree {
	
	public static void main(String args[]){
		Tree tree = new Tree();
		tree.addRoot(4);
		tree.addleft(tree.root, 2);
		tree.addright(tree.root, 8);
		tree.addleft(tree.root.right, 6);
		tree.addright(tree.root.right, 7);
		tree.addleft(tree.root.left, 1);
		tree.addright(tree.root.left, 3);
		
		int[] pre = {17,18,19,20,21,22,23};
		int[] in = {19,18,17,23,22,21,20};
		LintCode_Tree li = new LintCode_Tree();
		//TreeNode test = li.buildTree(pre,in);
		String haha = li.serialize(tree.root);
		System.out.println(haha);
	}
	
	/**
	 * 给定一个二叉树，找出其最大深度。
	 * 二叉树的深度为根节点到最远叶子节点的距离。
	 */
	public int maxDepth(TreeNode root) {
        int leftdepth = 1,rightdepth = 1;
        if(root == null)
        	return 0;
     
        if(root.left != null){
        	leftdepth += maxDepth(root.left);
        }
        if(root.right != null){
        	rightdepth += maxDepth(root.right);
        }
        
        if(leftdepth >= rightdepth){
        	return leftdepth;
        }else{
        	return rightdepth;
        }
    }

	/**
	 * 给定一棵二叉查找树和一个新的树节点，将节点插入到树中。
	 * 你需要保证该树仍然是一棵二叉查找树。
	 * 给出如下一棵二叉查找树，在插入节点6之后这棵二叉查找树可以是这样的：

		  2             2
		 / \           / \
		1   4   -->   1   4
		   /             / \ 
		  3             3   6
	 */
	public TreeNode insertNode(TreeNode root, TreeNode node) {
       if(root == null)
    	   return node;
       
       TreeNode n = root,t = root;
       while(n != null){
    	   if(n.val <= node.val){
    		   t = n;
    		   n = n.right;
    	   }else{
    		   t = n;
    		   n = n.left;
    	   }
       }
       if(t.val >= node.val){
    	   t.left = node;
    	   return root;
       }else{
    	   t.right = node;
    	   return root;
       }
    }

	/**
	 * 给出一棵二叉树，返回其节点值的前序遍历。
	 */
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> tree = new ArrayList<Integer>();
		if(root == null)
			return tree;
		
		TreeNode node = root;
		if(node != null){
			tree.add(node.val);
			
			tree.addAll(preorderTraversal(node.left));
			tree.addAll(preorderTraversal(node.right));
		}
		return tree;
    }

	/**
	 * 给定一个二叉树，判断它是否是合法的二叉查找树(BST)
	 * 一棵BST定义为
	 * 节点的左子树中的值要严格小于该节点的值。
	 * 节点的右子树中的值要严格大于该节点的值。
	 * 左右子树也必须是二叉查找树。
	 * 一个节点的树也是二叉查找树。
	 */
	public boolean isValidBST(TreeNode root) {
        if(root == null){
        	return true;
        }
        
        TreeNode node = root;
        ArrayList<Integer> list = new ArrayList<Integer>();
        midOrder(root,list);
        
        for(int i=0;i<list.size()-1;i++){
        	if(list.get(i)>=list.get(i+1))
        		return false;
        }
        return true;
    }
	
	public void midOrder(TreeNode root,ArrayList<Integer> list){
		if(root == null){
			return;
		}
		midOrder(root.left,list);
		list.add(root.val);
		midOrder(root.right,list);
	}

	/**
	 * 给定一个二叉树,确定它是高度平衡的。对于这个问题,一棵高度平衡的二叉树的定义是：一棵二叉树中每个节点的两个子树的深度相差不会超过1。
	 */
	public boolean isBalanced(TreeNode root) {
        if(root == null)
        	return true;
        
        TreeNode node = root;
        if(node != null){
        	int left = maxDepth(node.left);
        	int right = maxDepth(node.right);
        	if(left-right>1 || right-left>1)
        		return false;
        	else
        		return isBalanced(node.left) & isBalanced(node.right);
        }
		return false;
    }

	/**
	 * 根据前序遍历和中序遍历树构造二叉树.
	 * 你可以假设树中不存在相同数值的节点
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length==0){
			return null;
		}
		TreeNode root = new TreeNode(preorder[0]);
  		root.left = root.right = null;
		
		if(preorder.length>1 && inorder.length>1){
			int in = 0,pre;
			int i=0,j=0,flag=0;
			for(i=0;i<inorder.length;i++){
				if(inorder[i] == preorder[0]){
					if(i==0){
						flag = 1;
						break;
					}else{
						in = inorder[i-1];
						break;
					}
					
				}
			}
			if(flag == 1){
				root.left = null;
				int[] rightpre = new int[preorder.length-1-j];
				int[] rightin = new int[inorder.length-1-i];
				for(int k=j+1;k<preorder.length;k++)
					rightpre[k-j-1] = preorder[k];
				for(int k=i+1;k<inorder.length;k++)
					rightin[k-i-1] = inorder[k];
				root.right = buildTree(rightpre,rightin);
			}else{
				int[] leftpre = new int[i];
				int[] leftin = new int[i];
				int n = 0;
				for(int k=0;k<i;k++){
					leftin[k] = inorder[k];
				}
				for(int k=0;k<i;k++){
					leftpre[k] = preorder[k+1];
				}
				root.left = buildTree(leftpre,leftin);
				
				j = preorder.length-1-leftpre.length;
				int[] rightpre = new int[j];
				int[] rightin = new int[inorder.length-1-i];
				for(int k=0;k<j;k++)
					rightpre[k] = preorder[k+1+leftpre.length];
				for(int k=0;k<j;k++)
					rightin[k] = inorder[k+i+1];
				root.right = buildTree(rightpre,rightin);
			}
		}
		return root;
    }

	/**
	 * 给出一棵二叉树，返回其节点值的层次遍历（逐层从左往右访问）
	 */
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null)
        	return res;
        
        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        everyLevel(nodes,res);
        return res;
    }
	
	public void everyLevel(ArrayList<TreeNode> nodes,ArrayList<ArrayList<Integer>> res){
		ArrayList<Integer> nums = new ArrayList<Integer>();
		ArrayList<TreeNode> newNodes = new ArrayList<TreeNode>();
		if(nodes.size() != 0){
			for(int i=0;i<nodes.size();i++){
				TreeNode node = nodes.get(i);
				nums.add(node.val);
				if(node.left != null)
					newNodes.add(node.left);
				if(node.right != null)
					newNodes.add(node.right);				
			}
			res.add(nums);
			if(newNodes.size() != 0){
				everyLevel(newNodes,res);
			}
			
		}else{
			return;
		}
		
	}


	/**
	 * 给定两个值 k1 和 k2（k1 < k2）和一个二叉查找树的根节点。找到树中所有值在 k1 到 k2 范围内的节点。即打印所有x (k1 <= x <= k2) 其中 x 是二叉查找树的中的节点值。返回所有升序的节点值。
	 */
	public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        
        if(root == null)
        	return nums;
        
        TreeNode node = root;
        
        if(node != null){
        	if(node.val>=k1 && node.val<=k2){
        		nums.add(node.val);
        		nums.addAll(searchRange(node.left,k1,k2));
        		nums.addAll(searchRange(node.right,k1,k2));
        	}else if(node.val < k1){
        		nums.addAll(searchRange(node.right,k1,k2));
        	}else{
        		nums.addAll(searchRange(node.left,k1,k2));
        	}
        }
        Collections.sort(nums);
        return nums;
    }

	/**
	 * 设计一个算法，并编写代码来序列化和反序列化二叉树。将树写入一个文件被称为“序列化”，读取文件后重建同样的二叉树被称为“反序列化”。
	 * 如何反序列化或序列化二叉树是没有限制的，你只需要确保可以将二叉树序列化为一个字符串，并且可以将字符串反序列化为原来的树结构。
	 */
	 /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        //分别前序遍历和中序遍历二叉树，生成字符串
    	ArrayList<Integer> preorder = preorderTraversal(root);
    	ArrayList<Integer> inorder = new ArrayList<Integer>();
        midOrder(root,inorder);
        
        String str = "";
        for(int i=0;i<preorder.size();i++){
        	str  = str + preorder.get(i)+",";
        }
        for(int i=0;i<inorder.size();i++){
        	str = str + inorder.get(i)+",";
        }
        return str;
        
    	
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
		String[] strs = data.split(",");
		int len = strs.length/2;
		int[] preorder = new int[len];
		int[] inorder = new int[len];
		
		for(int i=0;i<len;i++){
			String str = strs[i];
			preorder[i] = Integer.valueOf(str);
		}
		for(int i=0;i<len;i++){
			String str = strs[i+len];
			inorder[i] = Integer.valueOf(str);
		}
		
		return buildTree(preorder,inorder);
    }

    /**
     * 给定一棵具有不同节点值的二叉查找树，删除树中与给定值相同的节点。如果树中没有相同值的节点，就不做任何处理。你应该保证处理之后的树仍是二叉查找树。
     */
    public TreeNode removeNode(TreeNode root, int value) {
        
    	if(root == null)
    		return null;
    	
    	TreeNode node = root,f = root;
    	TreeNode r=null,s=null;
    	int flag=0;
    	while(node != null){
    		if(node.val > value){
    			f = node;
    			node = node.left;
    		}
    			
    		else if(node.val < value){
    			f = node;
    			node = node.right;
    		}
    			
    		else{
    			if(node.left == null){
    				if(node == root){
    					root = node.right;
    					break;
    				}
    				else{
    					if(f.left == node){
    						f.left = node.right;
    						break;
    					}   						
    					else{
    						f.right = node.right;
    						break;
    					}   						
    				}
    			}else if(node.right == null){
    				if(node == root){
    					root = node.left;
    					break;
    				}   					
    				else{
    					if(f.left == node){
    						f.left = node.left;
    						break;
    					}   						
    					else{
    						f.right = node.left;
    						break;
    					}
    						
    				}
    			}else{
    				TreeNode q = node.left,k = q;
    				while(q.right != null){
    					k = q;
    					q = q.right;
    				}
    				if(k == node.left){
    					node.val = k.val;
    					node.left = k.left;
    				}else{
    					node.val = q.val;
    					k.right = q.left;
    				}
    			}
    		}
    	}
    	return root;
    }
}
