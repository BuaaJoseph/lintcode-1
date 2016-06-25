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
	 * ����һ���������飬�ҳ���Ԫ�أ����������еĳ��ִ����ϸ��������Ԫ�ظ����Ķ���֮һ��
	 * ��������[1,1,1,1,2,2,2]������ 1
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
	  * ��һ����·���� N ������վ�����е� i ������վ������gas[i]�����Ҵӵ�_i_������վǰ����_i_+1������վ��Ҫ��������cost[i]��
	  * ����һ�������������޴������������Ҫ��ĳһ������վ�����ƻ�·һ�ܣ�һ��ʼ����Ϊ�ա�
	  * ��ɻ��ƻ�·һ��ʱ�����ļ���վ�ı�ţ��������ڻ���һ�ܵķ������򷵻�-1��\
	  * ������4������վ��������gas[i]=[1, 1, 3, 1]����·����ʱ���ĵ�������cost[i]=[2, 2, 1, 1]��������ļ���վ�ı��Ϊ2��
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
	  * ����һ��Ǹ������������������ǵ�˳����������һ������������
	  * ���� [1, 20, 23, 4, 8]�����������������ӦΪ8423201��
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
	  * ����һ���ַ��� A, ��ʾһ�� n λ������, ɾ������ k λ����, ʹ��ʣ���������Ȼ����ԭ����˳�����в���һ���µ���������
	  * �ҵ�ɾ�� k ������֮�����С��������
	  * N <= 240, k <= N
	  * ����һ���ַ�������������� A ��һ������ k, ���� A = 178542, k = 4
	  * ����һ���ַ��� "12"
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
	  * ����һ���Ǹ��������飬�������λ������ĵ�һ��λ�á�������
	  * �����е�ÿ��Ԫ�ش��������Ǹ�λ�ÿ�����Ծ����󳤶ȡ���������
	  * �ж����Ƿ��ܵ�����������һ��λ�á�
	  * 
	  * �������������������һ����̰�ĺ� ��̬�滮��
	  * ̰�ķ���ʱ�临�Ӷ�ΪO��N����
	  * ��̬�滮������ʱ�临�Ӷ�ΪΪO��n^2����
	  * �����ֶ�����С�����ݼ���ʹ�������ͨ�����Ե����ַ�ʽ���������Ϊ���ô��ѧ�����ʹ�ö�̬�滮�ķ�ʽ��������⡣������ö�̬�滮�ķ�ʽ�����������Գ���̰�ķ�����ʹ���ٴ�ͨ��һ�Ρ�
	  * 
	  * A = [2,3,1,1,4]������ true.
	  * A = [3,2,1,0,4]������ false.
	  */
	 public boolean canJump(int[] A) {
		 if(A.length <= 1)
			 return true;
	      //����̰���㷨���ڱ������ҵ���������Զ��
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
	  *����һ��������������ʾ���У��ҳ���֮���һ�����С� 
	  *��������[1,3,2,3]������һ��������[1,3,3,2]
	  *��������[4,3,2,1]������һ��������[1,2,3,4]
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
