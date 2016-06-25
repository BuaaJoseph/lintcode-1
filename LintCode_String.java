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
	 * д��һ������ anagram(s, t) ȥ�ж������ַ����Ƿ��ǵߵ���ĸ˳�򹹳ɵ�
	 * ���� s="abcd"��t="dcab"������ true
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
	 *�Ƚ������ַ���A��B��ȷ��A���Ƿ����B�����е��ַ����ַ���A��B�е��ַ����� ��д��ĸ
	 *���� A = "ABCD" B = "ACD"������ true
	 *���� A = "ABCD" B = "AABC"�� ���� false
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
	 *����һ�������� source �ַ�����һ�� target �ַ�������Ӧ���� source �ַ������ҳ� target �ַ������ֵĵ�һ��λ��(��0��ʼ)����������ڣ��򷵻� -1��
	 *��� source = "source" �� target = "target"������ -1��
	 *��� source = "abcdabcdefg" �� target = "bcd"������ 1��
	 *
	 *�����������Ƿ���Ҫʵ��KMP�㷨��

	 *����Ҫ�����������������������ʱ�����Թٺܿ���ֻ����Ҫ����һ����Ļ���Ӧ����������Ȼ����Ҫ�ȸ����Թ�ȷ�����Ҫ��ôʵ������⡣
	 */
	public static int strStr(String source, String target) {
		if (source == null || target == null) {
			return -1;
		} else {
			return source.indexOf(target);
		}

	}

	
	/**
	 * ����һ���ַ�������S���ҵ��������е������ַ���(Anagram)�����һ���ַ����������ַ�������ô������һ����ĸ������ͬ����˳��ͬ���ַ���Ҳ��S�С�
	 * �����ַ������� ["lint","intl","inlt","code"]
	 *���� ["lint","inlt","intl"]
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
	 * ���������ַ������ҵ�������Ӵ����������䳤��
	 * ����A=��ABCD����B=��CBCE�������� 2
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
	 * ��k���ַ�����������ǵ������ǰ׺(LCP)
	 * �� "ABCD" "ABEF" �� "ACEF" ��,  LCP Ϊ "A"
	 * �� "ABCDEFG", "ABCEFG", "ABCEFA" ��, LCP Ϊ "ABC"
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
	 *ʵ��atoi�����������һ���ַ���ת��Ϊ���������û�кϷ�������������0���������������32λ�����ķ�Χ������INT_MAX(2147483647)�����������������INT_MIN(-2147483648)����Ǹ������� 
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
		//�Ƚ��ַ�����ʾ�����Ƿ񳬹�32λ����
		 if(str.charAt(0)!='-' && str.length()>10){
			 return 2147483647;
		 }else if(str.charAt(0)=='-' && str.length()>11){
			 return -2147483648;
		 }else if(str.charAt(0)!='-' && str.length()<10){
			 return Integer.parseInt(str);
		 }else if(str.charAt(0)=='-' && str.length()<11){
			 str = str.substring(1);
			 return -Integer.parseInt(str);
		 }else{		//�Ƚϸ��ӵ������������λ����10����Ҫ��2147483647�Ƚ�
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