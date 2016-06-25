package com.lintcode.americanbigcompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LintCode_SearchAndRecu {

	public static void main(String args[]) {
		int[] A = { 2, 3, 7 };
		LintCode_SearchAndRecu li = new LintCode_SearchAndRecu();

		Set<String> set = new HashSet<String>();
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");

		ArrayList<Integer> S = new ArrayList<Integer>();
		S.add(1);
		S.add(2);
		S.add(3);
		S.add(4);
		ArrayList<ArrayList<String>> test = li.findLadders("hit","cog",set);
		/*
		 * for(int i=0;i<test.size();i++){ System.out.println(test.get(i)); }
		 */
		String s1 = new String ("hello");
		String s2 = new String ("hello");
		System.out.println(s1==s2);
	}

	/**
	 * �������������n��k�����ش�1......n��ѡ����k��������ϡ� ���� n = 4 �� k = 2
	 * ���صĽ�Ϊ��[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
	 */
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> cur = new ArrayList<Integer>();
		combine(cur, 1, n, k, list);
		return list;

	}

	public void combine(ArrayList<Integer> cur, int index, int n, int k, ArrayList<ArrayList<Integer>> list) {
		if (cur.size() == k) {
			list.add(cur);
			return;
		}

		if (index > n || cur.size() > k)
			return;

		combine(cur, index + 1, n, k, list);
		cur.add(index);
		combine(cur, index + 1, n, k, list);
	}

	/**
	 * ����һ���ѡ����(C)��Ŀ������(T),�ҵ�C�����е���ϣ�ʹ�ҳ������ֺ�ΪT��C�е����ֿ����������ظ���ѡȡ��
	 * ����,������ѡ����[2,3,6,7]��Ŀ������7������Ľ�Ϊ�� ������ѡ����[2,3,6,7]��Ŀ������7 ���� [[7],[2,2,3]]
	 */
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		int size = candidates.length;
		if (size < 1) {
			return list;
		}
		ArrayList<Integer> cur = new ArrayList<Integer>();

		int sum = 0;
		Arrays.sort(candidates);
		execFind(list, candidates, target, cur, 0, 0);

		return list;
	}

	public void execFind(ArrayList<ArrayList<Integer>> list, int[] candidates, int target, ArrayList<Integer> cur,
			int cursum, int index) {
		if (cursum == target) {
			ArrayList<Integer> ne = new ArrayList<Integer>();
			ne.addAll(cur);
			list.add(ne);
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			cursum += candidates[i];
			cur.add(candidates[i]);
			if (cursum <= target) {
				execFind(list, candidates, target, cur, cursum, i);

				cursum -= candidates[i];
				cur.remove(cur.size() - 1);
				System.out.println(cursum + " " + cur);
			} else {
				cur.remove(cur.size() - 1);
				return;
			}
		}
	}

	/**
	 * ����һ������ͼ��ͼ�ڵ���������򱻶���Ϊ�� ����ÿ�������A--> B����A��������B֮ǰ
	 * ��������ĵ�һ���ڵ�������κ���ͼ��û�������ڵ�ָ�����Ľڵ� �ҵ�����ͼ����һ��������
	 */
	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

		ArrayList<DirectedGraphNode> res = new ArrayList<DirectedGraphNode>();
		if (graph.size() <= 1) {
			return graph;
		}

		while (graph.size() != 0) {
			ArrayList<DirectedGraphNode> nodesHasFather = new ArrayList<DirectedGraphNode>();
			for (int i = 0; i < graph.size(); i++) {
				nodesHasFather.addAll(graph.get(i).neighbors);
			}
			for (int i = 0; i < graph.size(); i++) {
				// ���Ϊ0��ɾ��
				if (!nodesHasFather.contains(graph.get(i))) {
					res.add(graph.get(i));
					graph.remove(i);
				}
			}
		}

		return res;
	}

	/**
	 * �����������ʣ�start��end����һ���ֵ䣬�ҵ���start��end�����ת������ ���磺 ÿ��ֻ�ܸı�һ����ĸ��
	 * �任�����е��м䵥�ʱ������ֵ��г��� �����������£� start = "hit" end = "cog" dict =
	 * ["hot","dot","dog","lot","log"] һ����̵ı任������ "hit" -> "hot" -> "dot" ->
	 * "dog" -> "cog"�� �������ĳ��� 5
	 */
	@SuppressWarnings("unchecked")
	public int ladderLength(String start, String end, Set<String> dict) {
		if (start.length() != end.length()) {
			return 0;
		}

		if (start.length() == 0 || end.length() == 0 || dict.size() == 0)
			return 0;

		Queue<String> path = new LinkedList<String>();
		path.add(start);
		Map count = new HashMap();
		count.put(start, 1);

		while (dict.size() >= 0 && !path.isEmpty()) {
			String curword = path.poll();
			int n = (int) count.get(curword);

			for (int i = 0; i < curword.length(); i++) {
				char[] temp = curword.toCharArray();

				for (char j = 'a'; j <= 'z'; j++) {
					if (temp[i] == j)
						continue;
					else {
						temp[i] = j;
					}

					if (dict.contains(String.valueOf(temp))) {
						path.add(String.valueOf(temp));
						count.put(String.valueOf(temp), n + 1);
						dict.remove(String.valueOf(temp));
					}

					if (String.valueOf(temp).equals(end))
						return (int) count.get(curword) + 1;
				}
			}
		}
		return 0;
	}

	/**
	 * n�ʺ������ǽ�n���ʺ������n*n�������ϣ��ʺ�˴�֮�䲻���໥������ ����һ������n���������в�ͬ��n�ʺ�����Ľ��������
	 * ÿ�������������һ����ȷ��n�ʺ���ò��֣����С�Q���͡�.���ֱ��ʾһ��Ů����һ����λ�á�
	 */
	public ArrayList<ArrayList<String>> solveNQueens(int n) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		ArrayList<String> base = new ArrayList<String>();
		int[] c = new int[n];
		if (n <= 0)
			return res;
		search(res, base, c, n, 0);
		return res;
	}

	public void search(ArrayList<ArrayList<String>> res, ArrayList<String> base, int[] c, int n, int curRow) {
		if (n == curRow) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.addAll(base);
			res.add(temp);
			return;
		}
		char[] str = new char[n];
		int i;
		for (i = 0; i < n; i++) {
			if (isValid(c, curRow, i)) {
				c[curRow] = i;
				for (int m = 0; m < n; m++) {
					str[m] = '.';
				}
				str[i] = 'Q';
				base.add(String.valueOf(str));
				search(res, base, c, n, curRow + 1);
				base.remove(base.size() - 1);
			}
		}
	}

	public boolean isValid(int[] c, int row, int col) {
		for (int i = 0; i < row; i++) {
			if (c[i] == col || row - col == i - c[i] || row + col == i + c[i]) // c[i]==col����ͬһ��
																				// row-col==i-c[i]�������ϽǺ����½ǵĶԽ���
																				// row+col==i+c[i]�������Ͻǵ����½ǵĶԽ���
				return false;
		}
		return true;
	}

	/**
	 * ����һ�����ܾ����ظ����ֵ��б����������п��ܵ��Ӽ�
	 */
	public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> base = new ArrayList<Integer>();
		ArrayList<Integer> already = new ArrayList<Integer>();
		if (S.size() <= 0) {
			res.add(base);
			return res;
		}

		/*
		 * int k = 0; while(k <= S.size()){ find1(res,base,k,already,S); k++; }
		 */
		Collections.sort(S);
		find2(res, base, S, 0);

		return res;

	}

	public void find1(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> base, int k, ArrayList<Integer> already,
			ArrayList<Integer> S) {
		if (k == base.size()) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.addAll(base);
			if (!res.contains(temp)) {
				res.add(temp);
			}

			return;
		}

		for (int i = 0; i < S.size(); i++) {
			int flag = 0;
			for (int j = 0; j < already.size(); j++) {
				if (i == already.get(j)) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				base.add(S.get(i));
				already.add(i);
				find1(res, base, k, already, S);
				base.remove(base.size() - 1);
				already.remove(already.size() - 1);
			}
		}
	}

	public void find2(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> base, ArrayList<Integer> S, int pos) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.addAll(base);
		res.add(temp);
		for (int i = pos; i < S.size(); i++) {
			if (i != pos && S.get(i) == S.get(i - 1))
				continue;
			base.add(S.get(i));
			find2(res, base, S, i + 1);
			base.remove(base.size() - 1);
		}
	}

	/**
	 * ����һ������ͬ�����ļ��ϣ����������е��Ӽ�
	 */
	public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> base = new ArrayList<Integer>();
		ArrayList<Integer> already = new ArrayList<Integer>();
		ArrayList<Integer> S = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			S.add(nums[i]);
		}
		if (S.size() <= 0) {
			res.add(base);
			return res;
		}

		/*
		 * int k = 0; while(k <= S.size()){ find1(res,base,k,already,S); k++; }
		 */
		Collections.sort(S);
		find2(res, base, S, 0);

		return res;
	}

	/**
	 * ����һ�������ظ����ֵ��б��ҳ��б����в�ͬ�����С�
	 */
	public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> base = new ArrayList<Integer>();
		ArrayList<Integer> already = new ArrayList<Integer>();

		if (nums.size() <= 0) {
			return res;
		}

		find1(res, base, nums.size(), already, nums);
		return res;
	}

	/**
	 * �����������ʣ�start��end����һ���ֵ䣬�ҳ����д�start��end�����ת������ ���磺 ÿ��ֻ�ܸı�һ����ĸ��
	 * �任�����е��м䵥�ʱ������ֵ��г��֡� �����������£�
	 * 
	 * start = "hit"
	 * 
	 * end = "cog"
	 * 
	 * dict = ["hot","dot","dog","lot","log"]
	 * 
	 * ����
	 * 
	 * [
	 * 
	 * ["hit","hot","dot","dog","cog"],
	 * 
	 * ["hit","hot","lot","log","cog"]
	 * 
	 * ]
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> findLadders(String start, String end, Set<String> dict) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		ArrayList<String> base = new ArrayList<String>();
		if (start.length() != end.length()) {
			return res;
		}

		if (start.length() == 0 || end.length() == 0 || dict.size() == 0)
			return res;

		Queue<String> path = new LinkedList<String>();
		path.add(start);
		Map count = new HashMap();
		count.put(start, 1);

		while (dict.size() >= 0 && !path.isEmpty()) {
			String curword = path.poll();
			int n = (int) count.get(curword);

			for (int i = 0; i < curword.length(); i++) {
				char[] temp = curword.toCharArray();

				for (char j = 'a'; j <= 'z'; j++) {
					if (temp[i] == j)
						continue;
					else {
						temp[i] = j;
					}

					if (dict.contains(String.valueOf(temp))) {
						path.add(String.valueOf(temp));
						count.put(String.valueOf(temp), n + 1);
						dict.remove(String.valueOf(temp));
						base.add(String.valueOf(temp));
					}

					if (String.valueOf(temp).equals(end))
						res.add(base);
				}
			}
		}
		return res;
    }
	
	public void findLadder(ArrayList<ArrayList<String>> res,ArrayList<String> base,String start,String end,Set<String> dict){
		
	}
}
