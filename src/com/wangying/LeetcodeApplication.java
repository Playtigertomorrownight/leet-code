package com.wangying;

import com.wangying.leetcode.leetCodeSolution;

public class LeetcodeApplication {

	
	public static void main(String[] args) {
		
		
		int [] source = new int [] {0,2,1,-3};
		long start = System.currentTimeMillis();
		System.out.println(leetCodeSolution.threeSumClosest(source,1));
		long end = System.currentTimeMillis();
		
		System.out.println("use time: "+(end-start)+" ms");
	}
	
}
