package com.lintcode.americanbigcompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LintCode_IntArray {

	public static void main(String args[]) {
		int[] A = new int[100];
		A[0]=416;A[1]=484;A[2]=808;A[3]=960;
		A[4]=1340;A[5]=1342;A[6]=1618;A[7]=1632;
		A[8]=1841;A[9]=1898;
		//A[0] = 1342;A[1]=1618;A[2]=1632;A[3]=1841;
		int[] B = {1};
		LintCode_IntArray li = new LintCode_IntArray();
		int test = li.removeDuplicates(B);
		System.out.println(test);
	}

	/**
	 * ����һ�������һ��ֵ����ԭ��ɾ����ֵ��ͬ�����֣�����������ĳ��ȡ�Ԫ�ص�˳����Ըı䣬���Ҷ��µ����鲻����Ӱ�졣 ����һ������
	 * [0,4,4,0,0,2,4,4]����ֵ 4 ���� 4 ����4��Ԫ�ص�������Ϊ[0,0,0,2]
	 */
	public int removeElement(int[] A, int elem) {
		// write your code here
		int originLength = A.length;
		for (int i = 0; i < originLength; i++) {
			if (A[i] == elem) {
				originLength--;
				if (i != A.length - 1) {
					for (int j = i; j < A.length - 1; j++) {
						A[j] = A[j + 1];
					}
				}
				i--;
			}
		}

		for (int k = 0; k < originLength; k++) {
			System.out.print(A[k] + " ");
		}
		System.out.println("");

		return originLength;
	}

	/**
	 * ����һ���������飬�ҵ���Ϊ��������顣��Ĵ���Ӧ�÷�������Ҫ������������ʼλ�úͽ���λ�� ���� [-3, 1, 2, -3, 4]������[0,
	 * 2] ���� [1, 3]. ���������Դ������ݻᳬʱ
	 */
	/*
	 * public ArrayList<Integer> subarraySum(int[] nums) { // write your code
	 * here ArrayList<Integer> pos = new ArrayList<Integer>(); if(nums.length==1
	 * && nums[0]==0){ pos.add(0); pos.add(0); return pos; } for(int
	 * i=1;i<nums.length+1;i++){ for(int j=0;j<nums.length-i+1;j++){ int sum=0;
	 * for(int k=j;k<j+i;k++){ sum += nums[k]; } if(sum==0){ pos.add(j);
	 * pos.add(j+i-1); return pos; } } } return pos; }
	 */

	public ArrayList<Integer> subarraySum(int[] nums) {
		// write your code here
		ArrayList<Integer> pos = new ArrayList<Integer>();
		if (nums.length == 1 && nums[0] == 0) {
			pos.add(0);
			pos.add(0);
			return pos;
		}
		int[] preSum = new int[nums.length];
		int t = 0;
		for (int i = 1; i < nums.length + 1; i++) {
			Integer sum = 0;
			for (int j = 0; j < i; j++) {
				sum += nums[j];
			}
			if (sum == 0) {
				pos.add(0);
				pos.add(i - 1);
				return pos;
			}
			for (int k = 0; k < preSum.length; k++) {	
				if (preSum[k] == sum) {
					pos.add(k + 1);
					pos.add(i - 1);
					return pos;
				}
			}
			preSum[t++] = sum;
		}
		return pos;
	}
	
	/*
	 * ����һ���������飬��ԭ������ɾ���ظ����ֵ����֣�ʹ��ÿ��Ԫ��ֻ����һ�Σ����ҷ����µ�����ĳ��ȡ���Ҫʹ�ö��������ռ䣬������ԭ��û�ж���ռ����������ɡ�
	 * ��������A =[1,1,2]����ĺ���Ӧ�÷��س���2����ʱA=[1,2]��
	 */
	public int removeDuplicates(int[] nums) {
        // write your code here
		int length = nums.length;
		if(length==0){
			return 0;
		}
		for(int i=0;i<length-1;i++){
			if(nums[i+1]==nums[i]){
				for(int j=i;j<length-1;j++){
					nums[j] = nums[j+1];
				}
				length--;
				i--;
			}
		}
		
		
		/*for(int k=0;k<length;k++){
			System.out.print(nums[k]+" ");
		}
		System.out.println();*/
		return length;
    }
	
	/**
	 * �ϲ������������������A��B���һ���µ����顣
	 *  ע������:����Լ���A�����㹻�Ŀռ䣨A����Ĵ�С���ڻ����m+n��ȥ���B�е�Ԫ�ء�
	 *  ���� A = [1, 2, 3, empty, empty], B = [4, 5]
	 *  �ϲ�֮�� A ����� [1,2,3,4,5]
	 *  * @param A: sorted integer array A which has m elements, 
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
	 */
	public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
		int i=0,j=0,q=0,p=0;
		if(m==0){
			for(int o=0;o<n;o++)
				A[m++] = B[o];
		}else{
			int minA = A[0],maxA = A[m-1];
			for(q=0;q<n;q++){				//�ҳ�B�б�A����С������С��
				if(B[q] >= minA)
					break;
			}
			for(p=0;p<n;p++){				//�ҳ�B�б�A�������������
				if(B[p] >= maxA)
					break;
			}
			
			if(q!=0){						//��B�б�A����С������С�����ŵ�A����ǰ�棬A�ĳ�������
				for(int o=m-1;o>=0;o--)
					A[o+q] = A[o];
				for(int o=0;o<q;o++)
					A[o] = B[o];
				m = m+q;
			}
			
			if(p != n){						//��B�бȣ�����������������ŵ���������棬A�ĳ�������
				for(int o=p;o<n;o++)
					A[m++] = B[o];
				n = p;
			}
			
			for(i=q;i<m-1;i++){
				for(j=q;j<n;j++){
					if(B[j]>=A[i] && B[j]<A[i+1]){
						int insertNum = 1;
						for(int k=j+1;k<n;k++){
							if(B[k]<=A[i+1])
								insertNum++;
						}
						//B��Ԫ�ز���A��
						for(int o=m-1;o>i;o--){
							A[o+insertNum] = A[o];
						}
						for(int t=0;t<insertNum;t++){
							A[i+1+t] = B[j+t];
						}
						i = i+insertNum;
						q = q+insertNum;
						m = m+insertNum;
						break;
					}else{
						break;
					}
				}
			}
		}
		
		
		for(int u=0;u<m;u++){
			System.out.print(A[u]+" ");
		}
    }
	
	/**
	 * ����һ����������A������B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]�� ����B��ʱ���벻Ҫʹ�ó�����
	 * ����A=[1, 2, 3]������ BΪ[6, 3, 2]
	 */
	 public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
	        // write your code
		 ArrayList<Long> B = new ArrayList<Long>();
		 for(int i=0;i<A.size();i++){
			 Long sum = (long) 1;
			 for(int j=0;j<A.size();j++){
				 if(j != i){
					 sum *= A.get(j);
				 }
			 }
			 B.add(sum);
		 }
		 return B;
	 }
	 
	 /**
	  * ����һ��������������飬�ҳ�����û�г��ֵ���С��������
	  * ������� [1,2,0], return 3
	  * ������� [3,4,-1,1], return 2
	  */
	 public int firstMissingPositive(int[] A) {
		 int fmp = 1;
		 for(int i=0;i<A.length;i++){
			 if(A[i]==fmp){
				 A[i]=-1;
				 fmp++;
				 i=-1;
			 }
		 }
		 return fmp;
	 }
	 
	 /**
	  * ��һ������n������������S, �ҵ������������target��ӽ�����Ԫ�飬�������������ĺ͡�
	  * ����S = [-1, 2, 1, -4] and target = 1.  ����ӽ�1����Ԫ���� -1 + 2 + 1 = 2.
	  */
	 public int threeSumClosest(int[] numbers ,int target) {
		int[] B = new int[numbers.length];
		int sum=0;
			
	    for(int i=0;i<numbers.length;i++){
	    	B[i] = numbers[i]-target;
	    	if(B[i]<0){
	    		B[i] = -B[i];
	    	}
	    }
	    int max=0;
	    for(int t=0;t<B.length;t++){
	    	if(B[t]>max){
	    		max = B[t];
	    	}
	    }
	    int k,l=0;
	    int noNeed[] = {-1,-1,-1};
	    while(l<3){
	    	int min=max+1;
	    	for(k=0;k<B.length;k++){
		    	if(B[k]<min && k!=noNeed[0] && k!=noNeed[1] && k!=noNeed[2]){
		    		min = B[k];
		    		noNeed[l] = k;
		    	}
		    }
		    
		    sum += numbers[noNeed[l]];
		    l++;
	    }
	    return sum;
	    
	    
	 }
	 
	 
	 public int threeSumClosest2(int[] numbers ,int target) {
	     // ��¼��С�Ĳ�ֵ
	        long minDiff = Long.MAX_VALUE;
	        // ��¼��С��ֵ��Ӧ������������
	        long result = 0;
	        // ÿ����õĲ�ֵ
	        long diff;
	        // ÿ����õ����������ĺ�
	        long sum;

	        // �ȶ������������
	        Arrays.sort(numbers);


	        // i��ʾ����ȡ��i������Ϊ���
	        for (int i = 0; i < numbers.length - 2; i++) {
	            // �ڶ��������ܵ���ʼλ��
	            int j = i + 1;
	            // �������������ǽ���λ��
	            int k = numbers.length - 1;

	            while (j < k) {
	                // ��ǰ�������ĺ�
	                sum = numbers[j] + numbers[k] + numbers[i];
	                // ��ǰ����Ŀ���֮��Ĳ�ֵ
	                diff = Math.abs(target - sum);

	                // ��ֵΪ0��ֱ�ӷ���
	                if (diff == 0) {
	                    return (int) sum;
	                }

	                // �����ǰ�Ĳ�ֵ��֮ǰ��¼�Ĳ�ֵС
	                if (diff < minDiff) {
	                    // ������С�Ĳ�ֵ
	                    minDiff = diff;
	                    // ������С��ֵ��Ӧ�ĺ�
	                    result = sum;

	                    // ���ϵ���������һ��Ԫ�ش���ʱ��Ч
	                }


	                // �ʹ���target
	                if (sum > target) {
	                    k--;
	                }
	                // ��С��target
	                else {
	                    j++;
	                }
	            }
	        }

	        return (int) result;
	    }
	 
	 /**
	  * ����һ����n������������S����S���ҵ���������a, b, c���ҵ�����ʹ��a + b + c = 0����Ԫ�顣
	  * ��S = {-1 0 1 2 -1 -4}, ����Ҫ���ص���Ԫ�鼯�ϵ��ǣ�(-1, 0, 1) (-1, -1, 2)
	  */
	 public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
		 ArrayList answer = new ArrayList();
	     for(int i=0;i<numbers.length;i++){
	    	 for(int j=i+1;j<numbers.length;j++){
	    		 for(int k=j+1;k<numbers.length;k++){
	    			 if(numbers[i]+numbers[j]+numbers[k]==0){
	    				 ArrayList<Integer> data = new ArrayList<Integer>();
	    				 int min = numbers[i];
	    				 if(numbers[j]<min){
	    					 min = numbers[j];
	    					 if(numbers[k]<min){
	    						 data.add(numbers[k]);
	    	    				 data.add(numbers[j]);
	    	    				 data.add(numbers[i]);
	    					 }else{
	    						 data.add(numbers[j]);
	    						 if(numbers[k]>numbers[i]){
	    							 data.add(numbers[i]);
	    							 data.add(numbers[k]);
	    						 }else{
	    							 data.add(numbers[k]);
	    							 data.add(numbers[i]);
	    						 }
	    					 }
	    				 }else{
	    					 if(numbers[k]>numbers[j]){
	    						 data.add(numbers[i]);
	    	    				 data.add(numbers[j]);
	    	    				 data.add(numbers[k]);
	    					 }else{
	    						 if(numbers[k]<numbers[i]){
	    							 data.add(numbers[k]);
		    	    				 data.add(numbers[i]);
		    	    				 data.add(numbers[j]);
	    						 }else{
	    							 data.add(numbers[i]);
		    	    				 data.add(numbers[k]);
		    	    				 data.add(numbers[j]);
	    						 }
	    					 }
	    				 }
	    				 
	    				 answer.add(data);
	    			 }
	    		 }
	    	 }
	     }
	     for(int i=0;i<answer.size();i++){
	    	 for(int j=i+1;j<answer.size();j++){
	    		 if(answer.get(i).equals(answer.get(j))){
	    			 answer.remove(j);
	    			 j--;
	    		 }
	    			 
	    	 }
	     }
	     return answer;
	 }
	 
	 /**
	  * ��һ���������飬�ҵ�������ʹ�����ǵĺ͵���һ����������target��
	  *����Ҫʵ�ֵĺ���twoSum��Ҫ���������������±�, ���ҵ�һ���±�С�ڵڶ����±ꡣע�������±�ķ�Χ��1��n��������0��ͷ��
	  *numbers=[2, 7, 11, 15],  target=9  return [1, 2]
	  */
	 public int[] twoSum(int[] numbers, int target) {
	     int[] res = new int[2];
		 for(int i=0;i<numbers.length;i++){
			 for(int j=i+1;j<numbers.length;j++){
				 if(numbers[i]+numbers[j]==target){
					 res[0] = i+1;
					 res[1] = j+1;
					 return res;
				 }
			 }
		 }
		return res;
	  }
	 
	 /**
	  * ����һ����������nums��һ������k���������飨���ƶ�����nums�е�Ԫ�أ���ʹ�ã�
	  * ����С��k��Ԫ���Ƶ����
	  * ���д��ڵ���k��Ԫ���Ƶ��ұ�
	  * �������黮�ֵ�λ�ã��������е�һ��λ��i������nums[i]���ڵ���k��
	  * ע������:��Ӧ�������Ļ�������nums����������ֻ�Ǽ����kС�����������������nums�е�����Ԫ�ض���kС���򷵻�nums.length��
	  * ��������nums=[3,2,2,1]�� k=2������ 1
	  */
	 public int partitionArray(int[] nums, int k) {
		 int length = nums.length;
		 for(int i=0;i<length;i++){
			 if(nums[i]>=k){
				 for(int j=length-1;j>=i;j--){
					 if(nums[j]<k){
						 int temp;
						 temp = nums[i];
						 nums[i] = nums[j];
						 nums[j] = temp;
						 length = j+1;
						 break;
					 }
					 if(j==i){
						 return j;
					 }
				 }
			 }
		 }
		 return nums.length;
	 }
	 
	 /**
	  * ������ɾ���ظ����֡����������������������ظ�����δ���
	  * ��������A =[1,1,1,2,2,3]����ĺ���Ӧ�÷��س���5����ʱA=[1,1,2,2,3]��
	  */
	 public int removeDuplicates2(int[] nums) {
	        int length = nums.length;
			if(length==0){
				return 0;
			}
			for(int i=0;i<length-2;i++){
				if(nums[i+1]==nums[i]){
					if(nums[i+2]==nums[i]){
						for(int j=i+1;j<length-1;j++){
							nums[j] = nums[j+1];
						}
						length--;
						i--;
					}
					
					
				}
			}
			
			
			/*for(int k=0;k<length;k++){
				System.out.print(nums[k]+" ");
			}
			System.out.println();*/
			return length;
	    }
}
