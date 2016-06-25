package com.lintcode.americanbigcompany;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LintCode_String {

	public static void main(String args[]) {
		String[] strs = {"ABCDEFG","ABCDEJH","ABCDE","ABCDEPOIOI"};
		LintCode_String so = new LintCode_String();
		int test = so.atoi(" 52lintcode");
		System.out.println(test);
	}

	/**
	 * 写出一个函数 anagram(s, t) 去判断两个字符串是否是颠倒字母顺序构成的
	 * 给出 s="abcd"，t="dcab"，返回 true
	 */
	public static boolean anagram(String s, String t) {
		if (s.length() != t.length())
			return false;
		else {
			for (int i = 0; i < t.length(); i++) {
				if (s.indexOf(t.charAt(i)) != -1) {
					int j = s.indexOf(t.charAt(i));
					s = s.substring(0, j) + s.substring(j + 1);
				} else {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 *比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是 大写字母
	 *给出 A = "ABCD" B = "ACD"，返回 true
	 *给出 A = "ABCD" B = "AABC"， 返回 false
	 */
	public static boolean compareStrings(String A, String B) {
		for (int i = 0; i < B.length(); i++) {
			if (A.indexOf(B.charAt(i)) != -1) {
				int j = A.indexOf(B.charAt(i));
				A = A.substring(0, j) + A.substring(j + 1);
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 *对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。
	 *如果 source = "source" 和 target = "target"，返回 -1。
	 *如果 source = "abcdabcdefg" 和 target = "bcd"，返回 1。
	 *
	 *在面试中我是否需要实现KMP算法？

	 *不需要，当这种问题出现在面试中时，面试官很可能只是想要测试一下你的基础应用能力。当然你需要先跟面试官确认清楚要怎么实现这个题。
	 */
	public static int strStr(String source, String target) {
		if (source == null || target == null) {
			return -1;
		} else {
			return source.indexOf(target);
		}

	}

	
	/**
	 * 给出一个字符串数组S，找到其中所有的乱序字符串(Anagram)。如果一个字符串是乱序字符串，那么他存在一个字母集合相同，但顺序不同的字符串也在S中。
	 * 对于字符串数组 ["lint","intl","inlt","code"]
	 *返回 ["lint","inlt","intl"]
	 */
	public static List<String> anagrams(String[] strs) {
		// write your code here
		List<String> rStr = new LinkedList<String>();
		int length = strs.length;
		for (int i = 0; i < length; ++i) {
			String str = strs[i];
			if (str != null) {
				for (int j = i + 1; j < length; j++) {
					String strToCom = strs[j];
					if (strToCom != null) {
						if (anagram(str, strToCom)) {
							if (strs[i] != null) {
								rStr.add(str);
							}
							if (strs[j] != null) {
								rStr.add(strToCom);
							}

							strs[i] = null;
							strs[j] = null;
						}
					}

				}
			}

		}
		return rStr;
	}

	/**
	 * 
	 * 给出两个字符串，找到最长公共子串，并返回其长度
	 * 给出A=“ABCD”，B=“CBCE”，返回 2
	 */
	public int longestCommonSubstring(String A, String B) {
		// write your code here
		int maxLength = 0;
		if(A==null || B==null)
			return 0;
		for (int i = 0; i < A.length(); i++) {
			for (int j = 0; j < B.length(); j++) {
				if (B.charAt(j) == A.charAt(i)) {
					int k = j + maxLength, l = i + maxLength;
					if (k >= B.length() || l >= A.length())
						break;
					else if(B.substring(j, j + maxLength).equals(A.substring(i, i + maxLength))) {

						while (B.charAt(k) == A.charAt(l)) {
							maxLength++;
							k++;
							l++;
							if (k >= B.length() || l >= A.length())
								break;

						}
					}
				}
			}
		}
		return maxLength;

	}
	
	
	/**
	 * 给k个字符串，求出他们的最长公共前缀(LCP)
	 * 在 "ABCD" "ABEF" 和 "ACEF" 中,  LCP 为 "A"
	 * 在 "ABCDEFG", "ABCEFG", "ABCEFA" 中, LCP 为 "ABC"
	 */
	public String longestCommonPrefix(String[] strs) {
        // write your code here
		int k=1;
		if(strs.length==1)
			return strs[0];
		else if(strs.length==0){
			return "";
		}else{
			while(true){
				for(int i=0;i<strs.length;i++){
					if(strs[i]==null){
						return null;
					}else if(k > strs[i].length()){
						return strs[i];
					}else if(!strs[i].substring(0, k).equals(strs[0].substring(0, k)))
						return strs[0].substring(0, k-1);
				}
				k++;
			}
		}
		
    }
	
	/**
	 *实现atoi这个函数，将一个字符串转换为整数。如果没有合法的整数，返回0。如果整数超出了32位整数的范围，返回INT_MAX(2147483647)如果是正整数，或者INT_MIN(-2147483648)如果是负整数。 
	 * "10" =>10
	 * "-1" => -1
	 * "123123123123123" => 2147483647
	 * "1.0" => 1
	 */
	 public int atoi(String str) {
		 str = str.trim();
		 
		 if(str.length()==0){
			 return 0;
		 }else if(str.equals(null)){
			 return 0;
		 }
		 if(str.charAt(0)=='+'){
			 if(str.length()==1){
				 return 0;
			 }else{
				 if(!Character.isDigit(str.charAt(1)))
					 return 0;
				 else
					 str = str.substring(1);
			 }
		 }
		 if(str.charAt(0)!='-' && !Character.isDigit(str.charAt(0))){
			 return 0;
		 }
		 for(int o=1;o<str.length();o++){
			 if(!Character.isDigit(str.charAt(o)) && str.charAt(o)!='.'){
				 str = str.substring(0,o);
			 }
		 }
		 for(int i=1;i<str.length();i++){
			 if(!Character.isDigit(str.charAt(i)) && str.charAt(i)!='.'){
				 return 0;
			 }else if(str.charAt(i)=='.'){
				 for(int j=i+1;j<str.length();j++){
					 if(str.charAt(j)!='0')
						 return 0;
				 }
				 str = str.substring(0,i);
			 }
		 }
		//比较字符串表示整数是否超过32位整数
		 if(str.charAt(0)!='-' && str.length()>10){
			 return 2147483647;
		 }else if(str.charAt(0)=='-' && str.length()>11){
			 return -2147483648;
		 }else if(str.charAt(0)!='-' && str.length()<10){
			 return Integer.parseInt(str);
		 }else if(str.charAt(0)=='-' && str.length()<11){
			 str = str.substring(1);
			 return -Integer.parseInt(str);
		 }else{		//比较复杂的情况，整数的位数是10，需要跟2147483647比较
			if(str.charAt(0)!='-'){
				 String comStr = "2147483647";
				 int res = comStr.compareTo(str);
				 if(res>0 || res==0){
					 return Integer.parseInt(str);
				 }else{
					 return 2147483647;
				 }
			}else{
				str = str.substring(1);
				String comStr = "2147483647";
				int res = comStr.compareTo(str);
				 if(res>0 || res==0){
					 return -Integer.parseInt(str);
				 }else{
					 return -2147483648;
				 }
			}
		 }
		 
	 }

};