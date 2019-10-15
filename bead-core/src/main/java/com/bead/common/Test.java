package com.bead.common;

public class Test {

	public int test(int[] a) {
		int n = a.length;
		if (n == 0) {
			return -1;
		}
		int leftsum = 0;
		int eq = a[0];
		
		int rightsum = 0;
		for(int i=1; i<n; i++) {
			rightsum += a[i];
		}
		
		if(leftsum == rightsum) {
			return eq;
		}
		
		for(int i=1; i<n; i++) {
			leftsum += eq;
			eq = a[i];
			if(i < n) {
				rightsum -= a[i];
			} else {
				rightsum = 0;
			}
			if(leftsum == rightsum) {
				return eq;
			}
		}
		
		return -1;

	}

	public static void main(String[] argv) {
		Test t = new Test();
		int[] a = new int[] { 1,4,5,-10,8,5,-5,-2};
		System.out.println(t.test(a));

	}

}
