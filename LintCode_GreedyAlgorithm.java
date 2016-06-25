package com.lintcode.americanbigcompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class LintCode_GreedyAlgorithm {
	
	public static void main(String args[]){
		
		LintCode_GreedyAlgorithm li = new LintCode_GreedyAlgorithm();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		int[] gas = {4,6,9,5,9,3,0};
		boolean test = li.canJump(gas);
		System.out.println(test);
	}
	
	/**
	 * 给定一个整型数组，找出主元素，它在数组中的出现次数严格大于数组元素个数的二分之一。
	 * 给出数组[1,1,1,1,2,2,2]，返回 1
	 */
	 public int majorityNumber(ArrayList<Integer> nums) {
	      int max = nums.get(0);
	      int num = 1;
	      
	      for(int i=0;i<nums.size();i++){
	    	  if(nums.get(i)!=max){
	    		  num--;
	    		  if(num <= 0){
	    			  max = nums.get(i);
	    			  num = 1;
	    		  }
	    	  }else{
	    		  num++;
	    	  }
	      }
	      return max;
	 }

	 /**
	  * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油gas[i]，并且从第_i_个加油站前往第_i_+1个加油站需要消耗汽油cost[i]。
	  * 你有一辆油箱容量无限大的汽车，现在要从某一个加油站出发绕环路一周，一开始油箱为空。
	  * 求可环绕环路一周时出发的加油站的编号，若不存在环绕一周的方案，则返回-1。\
	  * 现在有4个加油站，汽油量gas[i]=[1, 1, 3, 1]，环路旅行时消耗的汽油量cost[i]=[2, 2, 1, 1]。则出发的加油站的编号为2。
	  */
	 public int canCompleteCircuit(int[] gas, int[] cost) {
		int restGas = 0;
		int i;
		for(i=0;i<gas.length;i++){
			int j,k;
			for(j=i;j<gas.length;j++){
				restGas = restGas + gas[j]-cost[j];
				if(restGas < 0){
					restGas = 0;
					break;
				}
			}
			for(k=0;k<i;k++){
				restGas =restGas + gas[k]-cost[k];
				if(restGas < 0){
					restGas = 0;
					break;
				}
			}
			if(j==gas.length && k==i)
				break;
		}
		if(i == gas.length)
			return -1;
		else
			return i;
	 }

	 /**
	  * 给出一组非负整数，重新排列他们的顺序把他们组成一个最大的整数。
	  * 给出 [1, 20, 23, 4, 8]，返回组合最大的整数应为8423201。
	  */
	 public String largestNumber(int[] num) {
	     
		 String nums = "";
		 String retStr = "";
		 char max = '0';
		 int maxPos = 0;
		 
		 if(num.length==0)
			 return "0";
		 
		 for(int i=0;i<num.length;i++){
			 for(int j=0;j<num.length;j++){
				 if(num[j] != -1){
					 String numStr = String.valueOf(num[j]);
					 char digital = numStr.charAt(0);
					 if(digital >= max){
						 if(digital == max){
							 String num_j = String.valueOf(num[j]);
							 String num_max = String.valueOf(num[maxPos]);
							 
							 if(num_j.length()<num_max.length()){
								 String temp = num_j;
								 int mul = num_max.length()/num_j.length();
								 int start = 0;
								 for(int o=0;o<mul-1;o++)
									 num_j += temp;
								 for(int k=num_j.length()*mul;k<num_max.length();k++)
									 num_j += temp.charAt(start++);
								 if(Integer.valueOf(num_j)>Integer.valueOf(num_max)){
									 max = digital;
									 maxPos = j;
								 } 
							 }else if(num_j.length()>num_max.length()){
								 String temp = num_max;
								 int mul = num_j.length()/num_max.length();
								 int start = 0;
								 for(int o=0;o<mul-1;o++)
									 num_max += temp;
								 for(int k=num_max.length()*mul;k<num_j.length();k++)
									 num_max += temp.charAt(start++);
								 if(Integer.valueOf(num_j)>=Integer.valueOf(num_max)){
									 max = digital;
									 maxPos = j;
								 }
							 }else{
								 if(Integer.valueOf(num_j)>Integer.valueOf(num_max)){
									 max = digital;
									 maxPos = j;
								 }  
							 }
							 
							 
						 }else{
							 max = digital;
							 maxPos = j;
						 }
						 
					 }
				 }
			 }
			 
			 max = '0';
			 retStr += String.valueOf(num[maxPos]);
			 num[maxPos] = -1;
		 }
		 if(retStr.charAt(0)=='0')
			 return "0";
		 return retStr;
	 }

	 /**
	  * 给出一个字符串 A, 表示一个 n 位正整数, 删除其中 k 位数字, 使得剩余的数字仍然按照原来的顺序排列产生一个新的正整数。
	  * 找到删除 k 个数字之后的最小正整数。
	  * N <= 240, k <= N
	  * 给出一个字符串代表的正整数 A 和一个整数 k, 其中 A = 178542, k = 4
	  * 返回一个字符串 "12"
	  */
	 public String DeleteDigits(String A, int k) {
	     
		 if(k >= A.length())
			 return "0";
		 
		 
		 int removeNum = 0;
		 int flag = 0;
		 while(removeNum<k && flag==0){
			 flag = 1;
			 for(int i=0;i<A.length()-1;i++){
				 if(A.charAt(i) > A.charAt(i+1)){
					 A = A.substring(0,i) + A.substring(i+1);
					 removeNum++;
					 i--;
					 flag = 0;
					 break;
				 }
			 }
		 }
		 
		 if(removeNum < k){
			 A = A.substring(0,A.length()-k+removeNum);
		 }
		 
		 for(int i=0;i<A.length();i++){
			 if(A.charAt(i) != '0'){
				 A = A.substring(i);
				 break;
			 }
		 }
		 return A;
		 
	 }

	 /**
	  * 给出一个非负整数数组，你最初定位在数组的第一个位置。　　　
	  * 数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　　
	  * 判断你是否能到达数组的最后一个位置。
	  * 
	  * 这个问题有两个方法，一个是贪心和 动态规划。
	  * 贪心方法时间复杂度为O（N）。
	  * 动态规划方法的时间复杂度为为O（n^2）。
	  * 我们手动设置小型数据集，使大家阔以通过测试的两种方式。这仅仅是为了让大家学会如何使用动态规划的方式解决此问题。如果您用动态规划的方式完成它，你可以尝试贪心法，以使其再次通过一次。
	  * 
	  * A = [2,3,1,1,4]，返回 true.
	  * A = [3,2,1,0,4]，返回 false.
	  */
	 public boolean canJump(int[] A) {
		 if(A.length <= 1)
			 return true;
	      //测试贪心算法，在本步，找到能跳的最远的
		 int i=0;
		 while(i<A.length){
			 int max = 0;
			 if(A[i] == 0)
				 return false;
			 if(A[i]+i >= A.length-1)
				 return true;
			 int t = i;
			 for(int j=t+1;j<=t+A[t];j++){
				 if(A[j] >= max){
					 max = A[j];
					 i = j;
				 }
			 }
		 }
		 return false;
	 }

	 /**
	  *给定一个整数数组来表示排列，找出其之后的一个排列。 
	  *给出排列[1,3,2,3]，其下一个排列是[1,3,3,2]
	  *给出排列[4,3,2,1]，其下一个排列是[1,2,3,4]
	  */
	 public int[] nextPermutation(int[] nums) {
	     int len = nums.length;
	     if(len < 2)
	    	 return nums;
	     
	     ArrayList<Integer> st = new ArrayList<Integer>();
	     int i = len-1;
	     while(i > 0){
	    	 if(nums[i] <= nums[i-1]){
	    		 st.add(nums[i]);
	    		 i--;
	    	 }else{
	    		 st.add(nums[i]);
	    		 Collections.sort(st);
	    		 for(int k=0;k<st.size();k++){
	    			 if(st.get(k) > nums[i-1]){
	    				 int temp = nums[i-1];
	    				 nums[i-1] = st.get(k);
	    				 st.set(k, temp);
	    				 break;
	    			 }
	    		 }
	    		 break;
	    	 }
	     }
	     if(i == 0){
	    	 st.add(nums[i]);
	     }
	     int j = 0;
	     Collections.sort(st);
	     while(i <= len-1){
	    	 nums[i] = st.get(j++);
	    	 i++;
	     }
	     return nums;
	 }
}
