package com.lintcode.americanbigcompany;

public class LintCode_MathAndBinary {
	public static void main(String args[]){
		int a=51,b=9;
		Long l = (long)1001171717;
		LintCode_MathAndBinary li = new LintCode_MathAndBinary();
		String test = li.binaryRepresentation("17817287.6418609619140625");
		System.out.println(test);
	}
	
	/**
	 * 如果要将整数A转换为B，需要改变多少个bit位？
	 * 如把31转换为14，需要改变2个bit位。
	 * (31)10=(11111)2
	 * (14)10=(01110)2
	 */
	public static int bitSwapRequired(int a, int b) {
		int length=0,changeNum = 0;
		String str="";
		if(a == b)
			return changeNum;
        String A = Integer.toBinaryString(a);
        String B = Integer.toBinaryString(b);
        if(A.length() < B.length()){
        	length = B.length();
        	int len = B.length()-A.length();
        	for(int i=0;i<len;i++)
        		str += "0";
        	A = str+A;
        }else{
        	length = A.length();
        	int len = A.length()-B.length();
        	for(int i=0;i<len;i++)
        		str += "0";
        	B = str+B;
        }
        
        for(int i=length-1;i>=0;i--){
        	if(A.charAt(i) != B.charAt(i)){
        		changeNum++;
        	}
        }
        return changeNum;
    }


	/**
	 * 用 O(1) 时间检测整数 n 是否是 2 的幂次。
	 * n=4，返回 true;
	 * n=5，返回 false.
	 */
	public boolean checkPowerOf2(int n) {
		return ((n > 0) && ((n & (n - 1)) == 0));
    }

	/**
	 * 有一个机器人的位于一个M×N个网格左上角（下图中标记为'Start'）。
	 * 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角（下图中标记为'Finish'）
	 * 问有多少条不同的路径？
	 * n和m均不超过100
	 */
	public int uniquePaths(int m, int n) {
        int pathNum = 1,divideNum=1;
        int A=0,B=0;
        if(m==1){
        	if(n==1)
        		return 0;
        	else if(n>1)
        		return 1;
        }
        
        if(n==1){
        	if(m==1)
        		return 0;
        	else if(m>1)
        		return 1;
        }
        
        if(m<1 || n<1)
        	return 0;
        if(m>=n){
        	A = n-1;
        	B = m;
        }else{
        	A = m-1;
        	B = n;
        }
        
        int[] divide = new int[A];
        for(int t=0;t<A;t++)
        	divide[t] = t+1;
        
        int[] upDivide = new int[m+n-1-B];
        for(int o=0;o<m+n-1-B;o++){
        	upDivide[o] = B+o;
        }
        
        for(int o=0;o<m+n-1-B;o++){
        	for(int t=A-1;t>=0;t--){
        		if(divide[t]!=1 && upDivide[o]%divide[t]==0){
        			upDivide[o] /= divide[t];
        			divide[t] = 1;
        		}
        		for(int h=1;h<=divide[t];h++){
        			if(upDivide[o]%h==0 && divide[t]%h==0){
        				upDivide[o] /= h;
        				divide[t] /= h;
        			}
        		}
        	}
        }
        
        for(int o=0;o<m+n-1-B;o++)
        	pathNum *= upDivide[o];
        
        for(int t=0;t<A;t++){
        	if(divide[t]!=1){
        		pathNum /= divide[t];
        	}
        }
        return pathNum;
    }

	/**
	 * 设计一个算法，计算出n阶乘中尾部零的个数
	 * 11! = 39916800，因此应该返回 2
	 */
	public long trailingZeros(long n) {
        Long zeroNum = (long)0;
        Long divideNum = (long)5;
        String nStr = String.valueOf(n);
        while(n>=divideNum){
        	zeroNum += n/divideNum;
        	divideNum *= 5;
        }
        return zeroNum;
    }

	/**
	 * 给出两个32位的整数N和M，以及两个二进制位的位置i和j。写一个方法来使得N中的第i到j位等于M（M会是N中从第i为开始到第j位的子串）
	 * 给出N = (10000000000)2，M = (10101)2， i = 2, j = 6
	 * 返回 N = (10001010100)2
	 */
	 public int updateBits(int n, int m, int i, int j) {
	     String n_Str = Integer.toBinaryString(n);
	     String m_Str = Integer.toBinaryString(m);
	     String addZero = "";
	     int length = n_Str.length();
	     
	     if(length-1-j<0){
	    	 for(int h=0;h<j-length+1;h++)
	    		 addZero += "0";
	    	 n_Str = addZero+n_Str;
	    	 length += addZero.length();
	     }
	     char[] r_Str = new char[length];
	     if(m_Str.length()<(j-i+1)){
	    	 String mAddZero = "";
	    	 for(int h=0;h<j-i+1-m_Str.length();h++)
	    		 mAddZero += "0";
	    	 m_Str = mAddZero+m_Str;
	     }
	     
	     for(int k=0;k<length-1-j;k++){
	    	 r_Str[k] = n_Str.charAt(k);
	     }
	     
	     for(int k=length-1-j;k<=length-1-i;k++){
	    	 r_Str[k] = m_Str.charAt(k-length+1+j);
	     }
	     
	     for(int k=length-i;k<length;k++){
	    	 r_Str[k] = n_Str.charAt(k);
	     }
	     
	     String data;
	     int r_data;
	     if(length==32 && r_Str[0]=='1'){
	    	 for(int k=0;k<length;k++){
	    		 if(r_Str[k] == '0')
	    			 r_Str[k] = '1';
	    		 else
	    			 r_Str[k] = '0';
	    	 }
	    	 data = String.valueOf(r_Str);
	    	 r_data = Integer.parseInt(data,2)+1;
	    	 r_data = 0-r_data;
	     }else{
	    	 data = String.valueOf(r_Str);
	    	 r_data = Integer.parseInt(data,2);
	    	 r_data = Integer.parseInt(data,2);
	     }
	     return r_data;
	 }

	 /**【这道题没做出来】
	  *  可以分析，当n=1时，只有1个根节点，则只能组成1种形态的二叉树，令n个节点可组成的二叉树数量表示为h(n)，则h(1)=1; h(0)=0;

       当n=2时，1个根节点固定，还有2-1个节点。这一个节点可以分成（1,0），（0,1）两组。即左边放1个，右边放0个；或者左边放0个，右边放1个。即：h(2)=h(0)*h(1)+h(1)*h(0)=2，则能组成2种形态的二叉树。

      当n=3时，1个根节点固定，还有2个节点。这2个节点可以分成（2,0），（1,1），（0,2）3组。即h(3)=h(0)*h(2)+h(1)*h(1)+h(2)*h(0)=5，则能组成5种形态的二叉树。

以此类推，当n>=2时，可组成的二叉树数量为h(n)=h(0)*h(n-1)+h(1)*h(n-2)+...+h(n-1)*h(0)种，即符合Catalan数的定义，可直接利用通项公式得出结果。

	  * 给出 n，问由 1...n 为节点组成的不同的二叉查找树有多少种？
	  * 给出n = 3，有5种不同形态的二叉查找树：
	  *	1           3    3       2      1
 	  *	\         /    /       / \      \
  	  *	3      2     1       1   3      2
 	  *	/      /       \                  \
	  *	2     1          2                  3
	  */
	 public int numTrees(int n) {
		 int[] count = new int[n + 1];
	        if( n==0 )
	            return 1;
	        count[0] = 1;
	        count[1] = 1;
	 
	        for (int i = 2; i <= n; i++) {
	            for (int j = 0; j <= i - 1; j++) {
	                count[i] = count[i] + count[j] * count[i - j - 1];
	            }
	        }
	        return count[n];
	 }

	 /**
	  * 计算an % b，其中a，b和n都是32位的整数。
	  * 例如 231 % 3 = 2
	  * 例如 1001000 % 1000 = 0
	  * 
	  * .如果n 为奇数可以转化为 (a^(n/2) * a^(n/2) *  a ) %b = ((a^(n/2)%b * a^(n/2)%b)%b * a%b) %b
	  * 2. 如果n 为偶数可以转化为   (a^(n/2) * a^(n/2) ) %b = (a^(n/2)%b * a^(n/2)%b)%b 
	  */
	 public int fastPower2(int a, int b, int n) {
	     int remainder = 0;
	     int power = 1;
	     if(a == 0){
	    	 return 0;
	     }
	     
	     int firstReminder = a%b;
	     int row=0,flag=0;
	     
	     
		 for(int i=1;i<=n;i++){
			 power *= a;
			 
			 if(power%b==0)
				 return 0;
			 if(power%b == firstReminder){
				 if(flag==0)
					 flag++;
				 else{
					 int pow=1;
					 for(int k=0;k<n%(i-1);k++){
						 pow *= a;
					 }
					 return pow%b; 
				 }
				 
			 }
				 
		 }
		 return power%b;
	 }

	 public int fastPower(int a, int b, int n) {  
	        // write your code here  
	       if(n == 0)  
	            return 1 % b;  
	        if(n== 1)  
	            return a % b;  
	        long temp = fastPower(a, b, n/2);  
	        if((n & 1) == 1)  
	            return (int) (((temp * temp)%b)*a%b);  
	        else   
	            return (int) (temp*temp%b);  
	    }  

	 /**
	  * 给定一个数将其转换为二进制（均用字符串表示），如果这个数的小数部分不能在 32 个字符之内来精确地表示，则返回 "ERROR"。
	  * n = "3.72", 返回 "ERROR".
	  * n = "3.5", 返回 "11.1".
	  */
	 public String binaryRepresentation(String n) {
		 String intPart="",pointPart="";
		 if(n.contains(".")){
			 if(n.charAt(n.length()-1)!='0' && n.charAt(n.length()-1)!='5')
				 return "ERROR";
			 else{
				 String[] strs = n.split("\\.");
				 intPart = Integer.toBinaryString(Integer.valueOf(strs[0]));
				 double d = Double.parseDouble("0."+strs[1]);
				 
				 int length=0;
				 while(length<=33){
					 d = d*2;
					 String ha = String.valueOf(d);
					 String[] has = ha.split("\\.");
					 pointPart += has[0];
					 d = Double.parseDouble("0."+has[1]);

					 String checkStr = has[1];
					 int c;
					 for(c=0;c<checkStr.length();c++){
						 if(checkStr.charAt(c)!='0')
							 break;
					 }
					 if(c == checkStr.length())
						 break;
					 length++;
				 }
				 if(length>32)
					 return "ERROR";
				 
				 int i;
				 for(i=0;i<pointPart.length();i++){
					 if(pointPart.charAt(i)!='0')
						 break;
				 }
				 
				 if(i == pointPart.length())
					 return intPart;
				 return intPart+"."+pointPart;
			 }
			 
			
		 }else{
			 return String.valueOf(Integer.toBinaryString(Integer.parseInt(n)));
		 }
	 }
}
