package cn.umbrella.conmmons.util.math;

import java.util.LinkedList;

public class MathUtil {

	/**
	 * 求交集
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public LinkedList<Integer> intersection(int[] A, int[] B) {
		if (A == null || B == null || A.length == 0 || B.length == 0)
			return null;
		LinkedList<Integer> list = new LinkedList<Integer>();
		int pointerA = 0;
		int pointerB = 0;
		while (pointerA < A.length && pointerB < B.length) {
			if (A[pointerA] < B[pointerB])
				pointerA++;
			else if (A[pointerA] > B[pointerB])
				pointerB++;
			else {
				list.add(A[pointerA]);
				pointerA++;
				pointerB++;
			}
		}

		return list;

	}
}
