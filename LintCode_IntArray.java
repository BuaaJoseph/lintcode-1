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
	 * 给定一个数组和一个值，在原地删除与值相同的数字，返回新数组的长度。元素的顺序可以改变，并且对新的数组不会有影响。 给出一个数组
	 * [0,4,4,0,0,2,4,4]，和值 4 返回 4 并且4个元素的新数组为[0,0,0,2]
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
	 * 给定一个整数数组，找到和为零的子数组。你的代码应该返回满足要求的子数组的起始位置和结束位置 给出 [-3, 1, 2, -3, 4]，返回[0,
	 * 2] 或者 [1, 3]. 这个方法面对大量数据会超时
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
	 * 给定一个排序数组，在原数组中删除重复出现的数字，使得每个元素只出现一次，并且返回新的数组的长度。不要使用额外的数组空间，必须在原地没有额外空间的条件下完成。
	 * 给出数组A =[1,1,2]，你的函数应该返回长度2，此时A=[1,2]。
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
	 * 合并两个排序的整数数组A和B变成一个新的数组。
	 *  注意事项:你可以假设A具有足够的空间（A数组的大小大于或等于m+n）去添加B中的元素。
	 *  给出 A = [1, 2, 3, empty, empty], B = [4, 5]
	 *  合并之后 A 将变成 [1,2,3,4,5]
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
			for(q=0;q<n;q++){				//找出B中比A中最小的数还小的
				if(B[q] >= minA)
					break;
			}
			for(p=0;p<n;p++){				//找出B中比A中最大的数还大的
				if(B[p] >= maxA)
					break;
			}
			
			if(q!=0){						//把B中比A中最小的数还小的数放到A的最前面，A的长度增加
				for(int o=m-1;o>=0;o--)
					A[o+q] = A[o];
				for(int o=0;o<q;o++)
					A[o] = B[o];
				m = m+q;
			}
			
			if(p != n){						//把B中比Ａ中最大的数还大的数放到Ａ的最后面，A的长度增加
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
						//B中元素插入A中
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
	 * 给定一个整数数组A。定义B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]， 计算B的时候请不要使用除法。
	 * 给出A=[1, 2, 3]，返回 B为[6, 3, 2]
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
	  * 给出一个无序的正数数组，找出其中没有出现的最小正整数。
	  * 如果给出 [1,2,0], return 3
	  * 如果给出 [3,4,-1,1], return 2
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
	  * 给一个包含n个整数的数组S, 找到和与给定整数target最接近的三元组，返回这三个数的和。
	  * 例如S = [-1, 2, 1, -4] and target = 1.  和最接近1的三元组是 -1 + 2 + 1 = 2.
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
	     // 记录最小的差值
	        long minDiff = Long.MAX_VALUE;
	        // 记录最小差值对应的三个整数和
	        long result = 0;
	        // 每次求得的差值
	        long diff;
	        // 每次求得的三个整数的和
	        long sum;

	        // 先对数组进行排序
	        Arrays.sort(numbers);


	        // i表示假设取第i个数作为结果
	        for (int i = 0; i < numbers.length - 2; i++) {
	            // 第二个数可能的起始位置
	            int j = i + 1;
	            // 第三个数可能是结束位置
	            int k = numbers.length - 1;

	            while (j < k) {
	                // 求当前三个数的和
	                sum = numbers[j] + numbers[k] + numbers[i];
	                // 当前和与目标和之间的差值
	                diff = Math.abs(target - sum);

	                // 差值为0就直接返回
	                if (diff == 0) {
	                    return (int) sum;
	                }

	                // 如果当前的差值比之前记录的差值小
	                if (diff < minDiff) {
	                    // 更新最小的差值
	                    minDiff = diff;
	                    // 更新最小差值对应的和
	                    result = sum;

	                    // 以上的设置在下一次元素处理时生效
	                }


	                // 和大于target
	                if (sum > target) {
	                    k--;
	                }
	                // 和小于target
	                else {
	                    j++;
	                }
	            }
	        }

	        return (int) result;
	    }
	 
	 /**
	  * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
	  * 如S = {-1 0 1 2 -1 -4}, 你需要返回的三元组集合的是：(-1, 0, 1) (-1, -1, 2)
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
	  * 给一个整数数组，找到两个数使得他们的和等于一个给定的数target。
	  *你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是1到n，不是以0开头。
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
	  * 给出一个整数数组nums和一个整数k。划分数组（即移动数组nums中的元素），使得：
	  * 所有小于k的元素移到左边
	  * 所有大于等于k的元素移到右边
	  * 返回数组划分的位置，即数组中第一个位置i，满足nums[i]大于等于k。
	  * 注意事项:你应该真正的划分数组nums，而不仅仅只是计算比k小的整数数，如果数组nums中的所有元素都比k小，则返回nums.length。
	  * 给出数组nums=[3,2,2,1]和 k=2，返回 1
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
	  * 跟进“删除重复数字”：如果可以允许出现两次重复将如何处理？
	  * 给出数组A =[1,1,1,2,2,3]，你的函数应该返回长度5，此时A=[1,1,2,2,3]。
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
