package com.lintcode.americanbigcompany;

public class List {
	public ListNode head,tail;
	public int length;
	
	List(){
		head = tail = null;
	}
	
	public boolean isEmpty(){
		return head==null;
	}
	
	public void addhead(int x){
		head = new ListNode(x);
		if(tail==null)
			tail= head;
	}
	
	public void addtail(int x){
		if(!isEmpty()){
			tail.next = new ListNode(x);
			tail = tail.next;
		}else{
			head = tail = new ListNode(x);
		}
	}
	
	public void print(){
		for(ListNode temp=head;temp!=null;temp = temp.next){
			System.out.print(temp.val+"->");
		}
		System.out.println("null");
	}
	
	public void print(ListNode head){
		for(ListNode temp=head;temp!=null;temp = temp.next){
			System.out.print(temp.val+"->");
		}
		System.out.println("null");
	}
}
