package com.lintcode.americanbigcompany;

import java.util.ArrayList;
import java.util.Arrays;

public class LintCode_BinarySearch {

	public static void main(String args[]){
		LintCode_BinarySearch bs = new LintCode_BinarySearch();
		int[] A = {5, 7, 7, 8, 8, 10};
		int[] test = bs.searchRange(A,10);
		System.out.println("["+test[0]+","+test[1]+"]");
	}
	
	
	/**
	 * 实现 int sqrt(int x) 函数，计算并返回 x 的平方根。
	 * sqrt(3) = 1 sqrt(4) = 2 sqrt(5) = 2 sqrt(10) = 3
	 */
	public int sqrt(int x) {
       //使用二分查找法
		int high=0;
		int length = String.valueOf(x).length();
		if(length%2==0){
			String h = "1";
			for(int o=0;o<length/2;o++)
				h += "0";
			high = Integer.valueOf(h);
		}else{
			String h = "3";
			for(int o=0;o<length/2;o++){
				h += "9";
			}
			high = Integer.valueOf(h);
		}
		
		
		if(x==0)
			return 0;
		int i=1,temp;
		int low=1;
		if(high > 46340){
			high = 46340;
		}
		for(;i<=high && i>=1 && low<=high;){
			temp = (i*i);
			if(temp == x){
				return i;
			}else if(temp < x){
				if((i+1)*(i+1)>x){
					return i;
				}
				low = i;
			}else{
				if((i-1)*(i-1)<x){
					return i-1;
				}
				high = i;
			}
			i = (low+high)/2;
			if(i==46339){
				return 46340;
			}
		}
		return i;
		
    }

	/**
	 * 给定一个排序数组和一个目标值，如果在数组中找到目标值则返回索引。如果没有，返回到它将会被按顺序插入的位置。
	 * 你可以假设在数组中无重复元素。
	 * [1,3,5,6]，5 → 2
	 * [1,3,5,6]，2 → 1
	 */
	public int searchInsert(int[] A, int target) {
		if(A.length==0)
        	return 0;
		
        if(A[A.length-1]<target){
        	return A.length;
        }else if(A[0]>target){
        	return 0;
        }else if(A[A.length-1]==target){
        	return A.length-1;
        }
        
        
		
		for(int i=0;i<A.length-1;i++){
        	if(A[i]==target){
        		return i;
        	}else if(A[i]<target && A[i+1]>target){
        		return i+1;
        	}
        }
		return 0;
    }

	/**
	 * 写出一个高效的算法来搜索 m × n矩阵中的值。
	 * 这个矩阵具有以下特性：
	 * 每行中的整数从左到右是排序的
	 * 每行的第一个数大于上一行的最后一个整数。
	 * [
	 *  [1, 3, 5, 7],
	 *  [10, 11, 16, 20],
	 *  [23, 30, 34, 50]
	 *  ]
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0)
        	return false;
        int n = matrix[0].length;
        int i,j;
        
        for(i=0;i<m;i++){
        	if(matrix[i][n-1]>=target){   //target在这一行
        		for(j=0;j<n;j++){
        			if(matrix[i][j]==target)
        				return true;
        		}
        		if(j==n)
        			return false;
        	}
        }
        if(i==m)
        	return false;
		return false;
        
    }

	/**
	 * 给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），如果target不存在于数组中，返回-1。
	 * 在数组 [1, 2, 3, 3, 4, 5, 10] 中二分查找3，返回2。
	 */
	public int binarySearch(int[] nums, int target) {
	      int low = 0,i=0,position=-1,lasti=-1;
	      int high = nums.length;
	      for(;i<nums.length && low<=high;){
	    	 
	    	  if(nums[i] == target){
	    		  for(int k=i;k>=0;k--){
	    			  if(nums[k] == target){
	    				  position = k;
	    			  }else{
	    				  break;
	    			  }
	    		  }
	    		  return position;
	    	  }else if(nums[i]>target){
	    		  high = i;
	    	  }else{
	    		  low = i;
	    	  }
	    	  
	    	  i = (low+high)/2;
	    	  if(lasti == i){
	    		  break;
	    	  }else{
	    		  lasti = i; 
	    	  }
	    	  
	      }
	      return position;
		 
	 }

	 /**
	  * 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，需要得到的小段的数目至少为 k。当然，我们希望得到的小段越长越好，你需要计算能够得到的小段木头的最大长度。
	  * 木头长度的单位是厘米。原木的长度都是正整数，我们要求切割得到的小段木头的长度也要求是整数。无法切出要求至少 k 段的,则返回 0 即可。
	  * 有3根木头[232, 124, 456], k=7, 最大长度为114.
	  */
	public int woodCut(int[] L, int k) {
		 if(L.length == 0){
			 return 0;
		 }
		 Arrays.sort(L);
		 
		 
		 
		 int i,m=0,low = 1,high = L[L.length-1];  //分别是最短木头和最长木头的长度
		 int lasti = 0;
		 if(L.length>=k){
			 i = L[L.length-k];
			 m = L.length-k;
		 }else{
			 int p = k/L.length + 1;
			 i = L[0]/p;
			 if(i<1)
				 i = 1;
		 }
		 while(low <= high){
			 //判断长度为i的是否满足
			 int moodNum = 0;
			 if(i==0){
				 return 0;
			 }
			 for(int j=m;j<L.length;j++){
				 moodNum += L[j]/i;
			 }
			 if(moodNum >= k){
				int nextNum = 0;
				for(int j=m;j<L.length;j++){
					nextNum += L[j]/(i+1);
				}					
				if(nextNum >= k){
					low = i;
				}else{
					return i;
				}
			 }else if(moodNum > k){
				 low = i;
			 }else{
				 high = i;
			 }
			 
			 i = (low+high)/2;
			 if(lasti == i){
				 break;
			 }else{
				 lasti = i;
			 }
		 }
		return 0;
	 }

	 /**
	  * 假设一个旋转排序的数组其起始位置是未知的（比如0 1 2 4 5 6 7 可能变成是4 5 6 7 0 1 2）。
	  * 你需要找到其中最小的元素。
	  * 你可以假设数组中不存在重复的元素。
	  * 给出[4,5,6,7,0,1,2]  返回 0
	  */
	public int findMin(int[] num) {
	        // write your code here
		 if(num.length == 0){
			 return 0;
		 }if(num.length == 1){
			 return num[0];
		 }
		 
		 int i;
		 for(i=0;i<num.length-1;i++){
			 if(num[i+1]<num[i]){
				 return num[i+1];
			 }
		 }
		 if(i == num.length-1){
			 return num[0];
		 }
		return 0;
	 }

	/**
	 * 你给出一个整数数组(size为n)，其具有以下特点：
	 * 相邻位置的数字是不同
	 * A[0] < A[1] 并且 A[n - 2] > A[n - 1]
	 * 假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
	 * 数组可能包含多个峰值，只需找到其中的任何一个即可
	 * 给出数组[1, 2, 1, 3, 4, 5, 7, 6]返回1, 即数值 2 所在位置, 或者6, 即数值 7 所在位置.
	 */
	public int findPeak(int[] A) {
      if(A.length==0){
    	  return 0;
      }else if(A.length == 1){
    	  return 0;
      }else if(A.length == 2){
    	  return 0;
      }
      int i=0;
      for(i=1;i<A.length-1;i++){
    	  if(A[i]>A[i-1] && A[i]>A[i+1]){
    		  return i;
    	  }
      }
      if(i==A.length-1){
    	  return 0;
      }
	return 0;
    }


	/**
	 *代码库的版本号是从 1 到 n 的整数。某一天，有人提交了错误版本的代码，因此造成自身及之后版本的代码在单元测试中均出错。请找出第一个错误的版本号。
	 *你可以通过 isBadVersion 的接口来判断版本号 version 是否在单元测试中出错，具体接口详情和调用方法请见代码的注释部分。
	 *
	 * 请阅读上述代码，对于不同的语言获取正确的调用 isBadVersion 的方法，比如java的调用方式是VersionControl.isBadVersion
	 * 
	 * 给出 n=5
	 * 调用isBadVersion(3)，得到false
	 * 调用isBadVersion(5)，得到true
	 * 调用isBadVersion(4)，得到true
	 * 此时我们可以断定4是第一个错误的版本号
	 */
	/* public int findFirstBadVersion(int n) {
		 if(n<=0)
	            return 0;
	        if(n==1)
	            return 1;
	        int left = 1;
	        int right = n;
	        while(left  < right){
	            int mid = (left + right)/2;
	            if(SVNRepo.isBadVersion(mid))
	                right = mid;
	            else
	                left = mid + 1;
	        }
	        return left;
	 }*/


	 /**
	  * 假设有一个排序的按未知的旋转轴旋转的数组(比如，0 1 2 4 5 6 7 可能成为4 5 6 7 0 1 2)。给定一个目标值进行搜索
	  * 如果在数组中找到目标值返回数组中的索引位置，否则返回-1。
	  * 你可以假设数组中不存在重复的元素。
	  * 给出[4, 5, 1, 2, 3]和target=1，返回 2
	  * 给出[4, 5, 1, 2, 3]和target=0，返回 -1
	  */
	 public int search(int[] A, int target) {
		 int pos = -1;
		 if(A.length == 0){
			 return -1;
		 }if(A.length == 1){
			 if(A[0] == target)
				 return 0;
			 else
				 return -1;
		 }
		 
		 int t;
		 for(t=0;t<A.length-1;t++){
			 if(A[t+1]<A[t]){
				 pos = t+1;
				 break;
			 }
		 }
		 if(t == A.length-1){
			 pos = 0;
		 }
	    Arrays.sort(A);
	    int low = 0,high = A.length;
	    int i=0,lasti = -1;
	    while(low <= high){
	    	if(A[i]==target){
	    		if(i+pos>=A.length){
	    			return i+pos-A.length;
	    		}else{
	    			return i+pos;
	    		}
	    	}else if(A[i]>target){
	    		high = i;
	    	}else{
	    		low = i;
	    	}
	    	
	    	i = (low+high)/2;
	    	
	    	if(lasti==i){
	    		return -1;
	    	}else{
	    		lasti = i;
	    	}
	    }
		return -1;
	 }

	 /**
	  * 给定一个包含 n 个整数的排序数组，找出给定目标值 target 的起始和结束位置。
	  * 如果目标值不在数组中，则返回[-1, -1]
	  * 给出[5, 7, 7, 8, 8, 10]和目标值target=8,返回[3, 4]
	  */
	 public int[] searchRange(int[] A, int target) {
		 int[] result = {-1,-1};
		 int low = 0,high = A.length;
		 int i=0,lasti=-1;
		 
		 //特殊情况处理
		 if(A.length <= 0)
			 return result;
		 
		 while(low <= high){
			 if(A[i] == target){
				 int begin,end;
				 for(begin=i;begin>=0;begin--){
					 if(A[begin] != target){
						 break;
					 }
				 }
				 begin++;
				 for(end=i;end<A.length;end++){
					 if(A[end] != target){	 
						 break;
					 }
				 }
				 end--;
				 result[0] = begin;
				 result[1] = end;
				 return result;
			 }else if(A[i] > target){
				 high = i;
			 }else{
				 low = i;
			 }
			 
			 i = (high+low)/2;
			 if(lasti == i){
				 result[0] = -1;
				 result[1] = -1;
				 return result;
			 }else{
				 lasti = i;
			 }
		 }
		 return result;
	 }
}
