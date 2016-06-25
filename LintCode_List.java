package com.lintcode.americanbigcompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class LintCode_List {

	public static void main(String args[]) throws IOException {
		List list = new List();
		list.addtail(1);list.addtail(1);list.addtail(1);list.addtail(2);list.addtail(3);list.addtail(1);
		list.addtail(1);list.addtail(2);list.addtail(1);list.addtail(2);list.addtail(3);list.addtail(1);
		list.addtail(1);list.addtail(2);list.addtail(1);list.addtail(2);list.addtail(3);list.addtail(1);
		LintCode_List li = new LintCode_List();
		ListNode test = li.sortList(list.head);
		list.print(test);
		//System.out.println(test);

	}

	/**
	 * 给定一个链表，删除链表中倒数第n个节点，返回链表的头节点。 给出链表1->2->3->4->5->null和 n = 2.
	 * 删除倒数第二个节点之后，这个链表将变成1->2->3->5->null.
	 */
	ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null)
			return null;

		ListNode p = head, t = head, k = head;
		int num = 0;
		while (num < n - 1) {
			t = t.next;
			if (t == null) {
				return null;
			}
			num++;
		}

		if (t.next == null) {
			return p.next;
		} else {
			t = t.next;
			while (t != null) {
				t = t.next;
				k = p;
				p = p.next;
			}
			// 删除p
			k.next = p.next;
			return head;

		}
	}

	/**
	 * 将两个排序链表合并为一个新的排序链表 给出 1->3->8->11->15->null，2->null， 返回
	 * 1->2->3->8->11->15->null。
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}

		ListNode p = l1, q = l1.next;
		ListNode k = l2;

		// l1长度为1时
		if (q == null) {
			// 如果l1中的唯一元素小于l2中的最小元素
			if (p.val <= k.val) {
				p.next = k;
				return l1;
			}

			ListNode t = k.next;
			// 如果l2中也仅有一个元素
			if (t == null) {
				if (p.val >= k.val) {
					k.next = p;
					p.next = null;
					return l2;
				} else {
					p.next = k;
					k.next = null;
					return l1;
				}
			}
			// 把l1中的唯一元素插入l2中
			while (t != null) {
				if (p.val >= k.val && p.val < t.val) {
					k.next = p;
					p.next = t;
				} else {
					k = t;
					t = t.next;
				}
			}
		}

		while (q != null && k != null) {
			if (k.val >= p.val && k.val < q.val) {
				ListNode temp = k.next;
				p.next = k;
				k.next = q;
				p = p.next;
				k = temp;
			} else if (k.val < p.val) {
				ListNode temp = k.next;
				k.next = p;
				l1 = k;
				p = k;
				q = p;
				k = temp;
			} else {
				p = q;
				q = q.next;
			}
		}

		if (q == null) {
			while (k != null) {
				p.next = k;
				p = p.next;
				k = k.next;
			}
		}

		return l1;
	}

	/**
	 * 给定一个排序链表，删除所有重复的元素每个元素只留下一个。 给出 1->1->2->null，返回 1->2->null 给出
	 * 1->1->2->3->3->null，返回 1->2->3->null
	 */
	public static ListNode deleteDuplicates(ListNode head) {

		if (head == null || head.next == null)
			return null;

		ListNode p = head, q = head.next;
		while (q != null) {
			if (p.val == q.val) { // 删除q
				p.next = q.next;
				q = q.next;
			} else {
				p = q;
				q = q.next;
			}
		}
		return head;

	}

	/**
	 * 给定一个单链表和数值x，划分链表使得所有小于x的节点排在大于等于x的节点之前。 你应该保留两部分内链表节点原有的相对顺序。 给定链表
	 * 1->4->3->2->5->2->null，并且 x=3 返回 1->2->2->4->3->5->null
	 */
	public ListNode partition(ListNode head, int x) {

		if (head == null || head.next == null) {
			return head;
		}

		ListNode p = head, q = head.next;
		ListNode smallHead = null, r = smallHead, bigHead = null, t = bigHead;
		while (p != null) {
			if (p.val >= x) {
				if (bigHead == null) {
					bigHead = p;
					t = bigHead;

					if (q != null) {
						p = q;
						q = q.next;
					} else {
						p = q;
					}
					t.next = null;
					bigHead.next = null;
				} else {
					t.next = p;
					t = t.next;
					t.next = null;

					if (q != null) {
						p = q;
						q = q.next;
					} else {
						p = q;
					}
				}
			} else {
				if (smallHead == null) {
					smallHead = p;
					r = smallHead;
					if (q != null) {
						p = q;
						q = q.next;
					} else {
						p = q;
					}
					r.next = null;
					smallHead.next = null;

				} else {
					r.next = p;
					r = r.next;
					r.next = null;
					if (q != null) {
						p = q;
						q = q.next;
					} else {
						p = q;
					}
				}
			}
		}

		if (smallHead == null) {
			return bigHead;
		} else if (bigHead == null) {
			return smallHead;
		} else {
			r.next = bigHead;
			return smallHead;
		}
	}

	/**
	 * 翻转一个链表 给出一个链表1->2->3->null，这个翻转后的链表为3->2->1->null
	 */
	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null)
			return head;

		int length = 0;
		ListNode p = head;
		while (p != null) {
			length++;
			p = p.next;
		}

		ListNode[] nodes = new ListNode[length];
		ListNode q = head;
		int k = 0;
		while (q != null) {
			nodes[k++] = q;
			q = q.next;
		}

		ListNode newHead = nodes[k - 1], t = newHead;
		for (int i = k - 2; i >= 0; i--) {
			t.next = nodes[i];
			t = t.next;
		}
		t.next = null;
		return newHead;
	}

	/**
	 * 给定一个链表，旋转链表，使得每个节点向右移动k个位置，其中k是一个非负数 给出链表1->2->3->4->5->null和k=2
	 * 返回4->5->1->2->3->null
	 */
	public ListNode rotateRight(ListNode head, int k) {

		if (head == null || head.next == null)
			return head;
		int length = 0;
		ListNode p = head;
		while (p != null) {
			length++;
			p = p.next;
		}

		int realK = k % length;
		if (realK == 0)
			return head;
		ListNode hPart = head, tPart = null;
		ListNode q = head, r = null, t = null;
		for (int i = 0; i < length - realK - 1; i++) {
			q = q.next;
		}
		tPart = q.next;
		q.next = null;

		r = tPart;
		t = r.next;
		if (t == null) {
			tPart.next = hPart;
			return tPart;
		}
		while (t != null) {
			r = t;
			t = t.next;
		}
		r.next = hPart;
		return tPart;
	}

	/**
	  * 给出一个所有元素以升序排序的单链表，将它转换成一棵高度平衡的二分查找树
	  */
	 public TreeNode sortedListToBST(ListNode head) {  
		 if(head == null)
			 return null;
		 
		 int len = 0;
		 ListNode p = head;
		 while(p != null){
			 len++;
			 p = p.next;
		 }
		 if(len == 1){
			 TreeNode node = new TreeNode(head.val);
			 return node;
		 }else if(len == 2){
			 TreeNode node = new TreeNode(head.val);
			 node.right = new TreeNode(head.next.val);
			 return node;
		 }else{
			 len /= 2;
			 ListNode temp = head;
			 int cnt=0;
			 while(cnt < len){
				 temp = temp.next;
				 cnt++;
			 }
			 ListNode pre = head;
			 while(pre.next != temp){
				 pre = pre.next;
			 }
			 pre.next = null;
			 
			 TreeNode node = new TreeNode(temp.val);
			 node.left = sortedListToBST(head);
			 node.right = sortedListToBST(temp.next);
			 return node;
		 }
	 }

	 /**
	  * 给出一个链表，每个节点包含一个额外增加的随机指针可以指向链表中的任何节点或空的节点。
	  * 返回一个深拷贝的链表
	  * 【完全没看懂题】
	  */
	 public RandomListNode copyRandomList(RandomListNode head) {
		 if (head == null) {
	            return null;
	        }

	        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
	        RandomListNode dummy = new RandomListNode(0);
	        RandomListNode pre = dummy, newNode;
	        while (head != null) {
	            if (map.containsKey(head)) {
	                newNode = map.get(head);
	            } else {
	                newNode = new RandomListNode(head.label);
	                map.put(head, newNode);
	            }
	            pre.next = newNode;

	            if (head.random != null) {
	                if (map.containsKey(head.random)) {
	                    newNode.random = map.get(head.random);
	                } else {
	                    newNode.random = new RandomListNode(head.random.label);
	                    map.put(head.random, newNode.random);
	                }
	            }

	            pre = newNode;
	            head = head.next;
	        }

	        return dummy.next;
	  }

	 /**
	  * 给定一个链表，判断它是否有环。
	  * 给出 -21->10->4->5, tail connects to node index 1，返回 true
	  */
	 public boolean hasCycle(ListNode head) {  
	     ListNode fast = head,slow = head;
	     while(fast!=null && fast.next!=null){
	    	 slow = slow.next;
	    	 fast = fast.next.next;
	    	 if(slow == fast)
	    		 return true;
	     }
	     return false;
	 }

	 /**
	  * 给定一个单链表L： L0→L1→…→Ln-1→Ln,
	  * 重新排列后为：L0→Ln→L1→Ln-1→L2→Ln-2→…
	  * 必须在不改变节点值的情况下进行原地操作
	  * 给出链表1->2->3->4->null，重新排列后为1->4->2->3->null。
	  */
	 public void reorderList(ListNode head) {  
		 int len = 0;
		 if(head != null){
			 ListNode p = head;
			 while(p != null){
				 len++;
				 p = p.next;
			 }
			 ListNode[] nodes = new ListNode[len];
			 
			 p = head;
			 int k=0;
			 while(p != null){
				 nodes[k++] = p;
				 p = p.next;
			 }
			 
			 ListNode newh = head;
			 int m = len/2;
			 nodes[m].next = null;
			 for(int i=k-1;i>m;i--){
				 nodes[i].next = newh.next;
				 nodes[i-1].next = null;
				 newh.next = nodes[i];
				 newh = newh.next.next;		
			 } 
		 }
		 
	 }

	 /**
	  * 在O(nlogn)时间复杂度和常数级的空间复杂度下给链表排序。
	  */
	 public ListNode sortList(ListNode head) {  
		 int len = 0;
		 if(head==null || head.next==null)
			 return head;
		 ListNode p = head;
		 while(p != null){
			 len++;
			 p = p.next;
		 }
		 
		 ListNode newHead = head;
		 head = head.next;
		 newHead.next = null;
		 while(head != null){
			 ListNode r = head, q = newHead.next,o = newHead,u = newHead;
			 head = head.next;		 
			 if(r.val<newHead.val){
				 r.next = newHead;
				 newHead = r;
			 }else{
				 while(u.next != null){
					 if(u.val <= r.val && r.val < u.next.val){
						 r.next = u.next;
						 u.next = r;
						 break;
					 }else{
						 u = u.next;
					 }
					 
				 }
				 if(u.next == null){
					 u.next = r;
					 r.next = null;
				 }
			 }
			 
			 
		 }
		 return newHead;
	 }
	 
	
}
