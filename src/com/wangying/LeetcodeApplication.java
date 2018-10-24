package com.wangying;

import java.util.List;

import com.wangying.entity.ListNode;
import com.wangying.leetcode.leetCodeSolution;

public class LeetcodeApplication {

	
	public static void main(String[] args) {
		
		String [] source = new String [] {"((((()))))","(((()())))","(((())()))","(((()))())","(((())))()","((()(())))","((()()()))","((()())())","((()()))()","((())(()))","((())()())","((())())()","((()))(())","((()))()()","(()((())))","(()(()()))","(()(())())","(()(()))()","(()()(()))","(()()()())","(()()())()","(()())(())","(()())()()","(())((()))","(())(()())","(())(())()","(())()(())","(())()()()","()(((())))","()((()()))","()((())())","()((()))()","()(()(()))","()(()()())","()(()())()","()(())(())","()(())()()","()()((()))","()()(()())","()()(())()","()()()(())","()()()()()"};
		//String source = "{[((((()))))]}";
		long start = System.currentTimeMillis();
		List<String> strs = leetCodeSolution.generateParenthesis_(5);
//		for(String s: strs) {
//			System.out.println(s);
//		}
		System.out.println("size = "+ strs.size());
//			for(int j=0;j<source.length;j++) {
//				if(!strs.contains(source[j]))
//					System.out.println(source[j]);
//			}
		//printLink(leetCodeSolution.mergeTwoLists(l1,l2));
		long end = System.currentTimeMillis();
		
		System.out.println("use time: "+(end-start)+" ms");
	}
	
	private static void  printLink(ListNode l) {
		while(l!=null) {
			System.out.print(l.val+"->");
			l=l.next;
		}
		System.out.println();
	}
	
}
