package com.wangying.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode leetcode Algorithm problem solving
 * 
 * @author wangying.dz3
 *
 */
public class leetCodeSolution {

	/**
	 * 
	 * 10. Regular Expression Matching
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMatch(String s, String p) {
		System.out.println(s + " : " + p);
		if (p.isEmpty())
			return s.isEmpty();
		boolean headMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
		if (p.length() >= 2 && p.charAt(1) == '*')
			return isMatch(s, p.substring(2)) || (headMatch && isMatch(s.substring(1), p));
		else
			return headMatch && (isMatch(s.substring(1), p.substring(1)));
	}

	/**
	 * 10. Regular Expression Matching -- Dynamic planning
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMatch_(String s, String p) {
		int sl = s.length(), pl = p.length();
		boolean[][] matchs = new boolean[sl + 1][pl + 1];
		matchs[sl][pl] = true;
		boolean first_match;
		for (int i = sl; i >= 0; i--)
			for (int j = pl - 1; j >= 0; j--) {
				first_match = i < sl && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
				if (j < pl - 1 && p.charAt(j + 1) == '*') {
					matchs[i][j] = matchs[i][j + 2] || first_match && matchs[i + 1][j];
				} else {
					matchs[i][j] = first_match && matchs[i + 1][j + 1];
				}
			}
		return matchs[0][0];

	}

	/**
	 * 11. Container With Most Water
	 * 
	 * @param height
	 * @return
	 */
	public static int maxArea(int[] height) {
		int maxArea = 0, start = 0, end = height.length - 1;
		while (start < end) {
			maxArea = Math.max((end - start) * Math.min(height[start], height[end]), maxArea);
			if (height[start] <= height[end])
				start++;
			else
				end--;
		}
		return maxArea;
	}

	/**
	 * 12. Integer to Roman
	 * 
	 * @param num
	 * @return
	 */
	public static String intToRoman(int num) {
		int[] nums = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] romas = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			if (num < nums[i])
				continue;
			int site = num / nums[i];
			while (site-- > 0)
				result.append(romas[i]);
			num %= nums[i];
		}
		return result.toString();
	}

	/**
	 * 13. Roman to Integer
	 * 
	 * @param s
	 * @return
	 */
	public static int romanToInt(String s) {
		int[] nums = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] romas = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int result = 0;
		int index, index2;
		for (int i = 0, len = s.length(); i < len; i++) {
			index = 0;
			index2 = 0;
			String sss;
			for (int j = 0, lent = romas.length; j < lent; j++) {
				sss = romas[j];
				if (i < len && sss.equals(s.substring(i, i + 1)))
					index = j;
				if (i < len - 1 && sss.equals(s.substring(i, i + 2))) {
					index2 = j;
					break;
				}
			}
			if (index2 == 0)
				result += nums[index];
			else {
				result += nums[index2];
				i++;
			}

		}
		return result;
	}

	/**
	 * 14. Longest Common Prefix
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		if (strs.length == 1)
			return strs[0];
		int minIndex = Integer.MAX_VALUE;
		for (int i = 1, len = strs.length; i < len; i++) {
			int index = 0;
			while (index < strs[0].length() && index < strs[i].length()
					&& strs[i].charAt(index) == strs[0].charAt(index))
				index++;
			minIndex = Math.min(minIndex, index);
		}
		return strs[0].substring(0, minIndex < 0 || minIndex == Integer.MAX_VALUE ? 0 : minIndex);
	}

	/**
	 * 15. 3Sum
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int len=nums.length;
		if(len==0) return result;
		Arrays.sort(nums);
		for(int i=0;i<len-2;i++) {
			if(i-1>=0&&nums[i]==nums[i-1]) continue;
			int low = i+1,height=len-1,temp=-nums[i];
			while(low<height) {
				if(nums[low]+nums[height] == temp) {
					result.add(Arrays.asList(nums[i],nums[low],nums[height]));
					while(low<height&&nums[low]==nums[low+1]) low++;
					while(height>low&&nums[height]==nums[height-1]) height--;
					low++;	height--;
				}else if(nums[low]+nums[height] < temp) low++;
				else height--;
			}
				
		}
		return result;
	}
	
	 /**
	  * 16. 3Sum Closest
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int threeSumClosest(int[] nums, int target) {
		int result = 0,min=Integer.MAX_VALUE;
		int len=nums.length;
		if(len==0) return result;
		Arrays.sort(nums);
		for(int i=0;i<len-2;i++) {
			if(i-1>=0&&nums[i]==nums[i-1]) continue;
			int low = i+1,height=len-1,temp=target-nums[i];
			while(low<height) {
				if(Math.abs(nums[low]+nums[height]-temp)<min) {
					min = Math.abs(nums[low]+nums[height]-temp);
					result = nums[low]+nums[height]+nums[i];
					while(low<height&&nums[low]==nums[low+1]) low++;
					while(height>low&&nums[height]==nums[height-1]) height--;
					low++;	height--;
				}else height--;
					
			}
		}
		return result;
	 }

}
