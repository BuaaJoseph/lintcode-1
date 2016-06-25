package com.lintcode.americanbigcompany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.util.ArrayList;

public class Solution {
	/**
	 * @param m:
	 *            An integer m denotes the size of a backpack
	 * @param A:
	 *            Given n items with size A[i]
	 * @return: The maximum size
	 */
	public int backPack(int m, int[] A) {
		int[] result = new int[m + 1];
		for (int i = 0; i < A.length; i++) {
			for (int j = m; j >= A[i]; j--) {
				result[j] = Math.max(result[j - A[i]] + A[i], result[j]);
			}
		}
		return result[m];
	}
}
