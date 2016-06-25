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
	 * ʵ�� int sqrt(int x) ���������㲢���� x ��ƽ������
	 * sqrt(3) = 1 sqrt(4) = 2 sqrt(5) = 2 sqrt(10) = 3
	 */
	public int sqrt(int x) {
       //ʹ�ö��ֲ��ҷ�
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
	 * ����һ�����������һ��Ŀ��ֵ��������������ҵ�Ŀ��ֵ�򷵻����������û�У����ص������ᱻ��˳������λ�á�
	 * ����Լ��������������ظ�Ԫ�ء�
	 * [1,3,5,6]��5 �� 2
	 * [1,3,5,6]��2 �� 1
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
	 * д��һ����Ч���㷨������ m �� n�����е�ֵ��
	 * �����������������ԣ�
	 * ÿ���е������������������
	 * ÿ�еĵ�һ����������һ�е����һ��������
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
        	if(matrix[i][n-1]>=target){   //target����һ��
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
	 * ����һ��������������飨���򣩺�һ��Ҫ���ҵ�����target����O(logn)��ʱ����ҵ�target��һ�γ��ֵ��±꣨��0��ʼ�������target�������������У�����-1��
	 * ������ [1, 2, 3, 3, 4, 5, 10] �ж��ֲ���3������2��
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
	  * ��һЩԭľ�����������Щľͷ�и��һЩ������ͬ��С��ľͷ����Ҫ�õ���С�ε���Ŀ����Ϊ k����Ȼ������ϣ���õ���С��Խ��Խ�ã�����Ҫ�����ܹ��õ���С��ľͷ����󳤶ȡ�
	  * ľͷ���ȵĵ�λ�����ס�ԭľ�ĳ��ȶ���������������Ҫ���и�õ���С��ľͷ�ĳ���ҲҪ�����������޷��г�Ҫ������ k �ε�,�򷵻� 0 ���ɡ�
	  * ��3��ľͷ[232, 124, 456], k=7, ��󳤶�Ϊ114.
	  */
	public int woodCut(int[] L, int k) {
		 if(L.length == 0){
			 return 0;
		 }
		 Arrays.sort(L);
		 
		 
		 
		 int i,m=0,low = 1,high = L[L.length-1];  //�ֱ������ľͷ���ľͷ�ĳ���
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
			 //�жϳ���Ϊi���Ƿ�����
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
	  * ����һ����ת�������������ʼλ����δ֪�ģ�����0 1 2 4 5 6 7 ���ܱ����4 5 6 7 0 1 2����
	  * ����Ҫ�ҵ�������С��Ԫ�ء�
	  * ����Լ��������в������ظ���Ԫ�ء�
	  * ����[4,5,6,7,0,1,2]  ���� 0
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
	 * �����һ����������(sizeΪn)������������ص㣺
	 * ����λ�õ������ǲ�ͬ
	 * A[0] < A[1] ���� A[n - 2] > A[n - 1]
	 * �ٶ�P�Ƿ�ֵ��λ��������A[P] > A[P-1]��A[P] > A[P+1]����������������һ����ֵ��λ�á�
	 * ������ܰ��������ֵ��ֻ���ҵ����е��κ�һ������
	 * ��������[1, 2, 1, 3, 4, 5, 7, 6]����1, ����ֵ 2 ����λ��, ����6, ����ֵ 7 ����λ��.
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
	 *�����İ汾���Ǵ� 1 �� n ��������ĳһ�죬�����ύ�˴���汾�Ĵ��룬����������֮��汾�Ĵ����ڵ�Ԫ�����о��������ҳ���һ������İ汾�š�
	 *�����ͨ�� isBadVersion �Ľӿ����жϰ汾�� version �Ƿ��ڵ�Ԫ�����г�������ӿ�����͵��÷�����������ע�Ͳ��֡�
	 *
	 * ���Ķ��������룬���ڲ�ͬ�����Ի�ȡ��ȷ�ĵ��� isBadVersion �ķ���������java�ĵ��÷�ʽ��VersionControl.isBadVersion
	 * 
	 * ���� n=5
	 * ����isBadVersion(3)���õ�false
	 * ����isBadVersion(5)���õ�true
	 * ����isBadVersion(4)���õ�true
	 * ��ʱ���ǿ��Զ϶�4�ǵ�һ������İ汾��
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
	  * ������һ������İ�δ֪����ת����ת������(���磬0 1 2 4 5 6 7 ���ܳ�Ϊ4 5 6 7 0 1 2)������һ��Ŀ��ֵ��������
	  * ������������ҵ�Ŀ��ֵ���������е�����λ�ã����򷵻�-1��
	  * ����Լ��������в������ظ���Ԫ�ء�
	  * ����[4, 5, 1, 2, 3]��target=1������ 2
	  * ����[4, 5, 1, 2, 3]��target=0������ -1
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
	  * ����һ������ n ���������������飬�ҳ�����Ŀ��ֵ target ����ʼ�ͽ���λ�á�
	  * ���Ŀ��ֵ���������У��򷵻�[-1, -1]
	  * ����[5, 7, 7, 8, 8, 10]��Ŀ��ֵtarget=8,����[3, 4]
	  */
	 public int[] searchRange(int[] A, int target) {
		 int[] result = {-1,-1};
		 int low = 0,high = A.length;
		 int i=0,lasti=-1;
		 
		 //�����������
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
