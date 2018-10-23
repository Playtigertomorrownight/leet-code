package com.wangying.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wangying.entity.ListNode;

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
		int result = Integer.MAX_VALUE;
		int len=nums.length;
		if(len==0) return result;
		Arrays.sort(nums);
		for(int i=0;i<len;i++) {
			if(i>0&&nums[i]==nums[i-1]) continue;
			int low = i+1,height=len-1,temp=target-nums[i];
			while(low<height) {
				int total = nums[low]+nums[height]+nums[i];
				result = (result==Integer.MAX_VALUE||Math.abs(result-target)>Math.abs(total-target))?total:result;
				if(nums[low]+nums[height]==temp) return target;
				else if (nums[low] + nums[height] < temp) low++;
	            else height--;
			}
		}
		return result;
	 }
	
	/**
	 * 17. Letter Combinations of a Phone Number
	 * @param digits
	 * @return
	 */
	public static List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<String>();
	      String  basestr = "abcdefghijklmnopqrstuvwxyz";
	      int [] indexs = new int[] {0,3,6,9,12,15,19,22,26};
	      int digit;
	      char [] chars = new char[0];
	      for(int i=0,len=digits.length();i<len;i++) {
	    	 digit = (int)digits.charAt(i)-48;
	    	 if(digit<2||digit>9) continue;
	    	 chars = basestr.substring(indexs[digit-2],indexs[digit-1]).toCharArray();
	    		 result = letterCombinations_(result,chars);
	      }
	      return result;
	      
	}
	
	private static List<String> letterCombinations_(List<String> source, char [] chars) {
		List<String> temp = new ArrayList<String>();
		StringBuilder sb= new StringBuilder();
		if(source.size()==0) {
			for(char a : chars) {
				sb.setLength(0);
				temp.add(sb.append(a).toString());
			}
		}else {
			for(String s : source) {
				for(char a : chars) {
					sb.setLength(0);
					temp.add(sb.append(s).append(a).toString());
				}
			}
		}
		return temp;
	}
	
	 /**
	  * 18. 4Sum
	 * @param nums
	 * @param target
	 * @return
	 */
	 public static List<List<Integer>> fourSum(int[] nums, int target) {
		    List<List<Integer>> result = new ArrayList<List<Integer>>();
			int len=nums.length;
			if(len==0) return result;
			Arrays.sort(nums);
			for(int i=0;i<len-1;i++) {
				if(i>=1&&nums[i]==nums[i-1]) continue;
				for(int j=i+1;j<len-1;j++) {
					if(j>i+1&&nums[j]==nums[j-1]) continue;
					int low = j+1,height=len-1,temp=target-nums[i]-nums[j];
					while(low<height) {
						if(nums[low]+nums[height] == temp) {
							result.add(Arrays.asList(nums[i],nums[j],nums[low],nums[height]));
							while(low<height&&nums[low]==nums[low+1]) low++;
							while(height>low&&nums[height]==nums[height-1]) height--;
							low++;	height--;
						}else if(nums[low]+nums[height] < temp) low++;
						else height--;
					}
				}
			}
			return result;
	 }
	
	  /**
	   * 19. Remove Nth Node From End of List
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		  ListNode temp = new ListNode(0);
		  temp.next = head;
		  ListNode first = temp;
		  ListNode second = temp;
		  while(n+1>0&&first!=null){
              n--;
              first = first.next;
          } 
		  while(null!=first) {
			  first = first.next;
			  second = second.next;
		  }
		  second.next = second.next.next;
		  return temp.next;
	  }

	
	/**
	 * 20. Valid Parentheses
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s) {
		char stack [] = new char [s.length()];
		int index = 0,size=0;
		char [] ss =s.toCharArray();
        for(char c : ss) {
        	if(c==']'||c=='}'||c==')') {
        		if(size==0||stack[--index]!=c) return false;
        		size--;
        	}else {
        		switch(c) {
        			case '(':
        				stack[index++] = ')';
        				break;
        			case '[':
        				stack[index++] = ']';
        				break;
        			case '{':
        				stack[index++] = '}';
        				break;
        		}
        		size++;
        	}
        }
        return size==0;
    }
	
	/**
	 * 21. Merge Two Sorted Lists21. Merge Two Sorted Lists
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		 if(l1==null) return l2;
			if(l2==null) return l1;
			ListNode start,end,result;
	        if(l1.val>l2.val){
	            start = l2;
	            result = start;
	            end = l1;
	        }else{
	            start = l1;
	            result = start;
	            end = l2;
	        }
			ListNode last = start;
			while(start!=null&&end!=null) {
				if(start.val>end.val) {
					last.next = new ListNode(end.val);
					last.next.next=start;
					end = end.next;
					last = last.next;
				}else {
					last = start;
					start = start.next;
				}
				
			}
			if(end!=null) {
				last.next=end;
			}
			return result;
    }
	
	 /**
	  * 22. Generate Parentheses
	 * @param n
	 * @return
	 */
	public static List<String> generateParenthesis(int n) {
		List<String> result= new ArrayList<String> ();
		Map<String,Boolean>  map = generateParenthesis_(n);
		result.addAll(map.keySet());
		return result;
	}
	
	public static Map<String,Boolean> generateParenthesis_(int n) {
		Map<String,Boolean> result= new HashMap<String,Boolean> ();
		if(n==1) {
			result.put("()",true);
			return result;
		}else {
			StringBuilder sb = new  StringBuilder();
			List<String> last = generateParenthesis(n-1);
			for(String l : last) {
				//并列
				sb.setLength(0);
				result.put(sb.append("()").append(l).toString(),true);
				sb.setLength(0);
				result.put(sb.append(l).append("()").toString(),true);
				//包含
				sb.setLength(0);
				result.put(sb.append("(").append(l).append(")").toString(),true);
			}
			if(n%4==0) {
				sb.setLength(0);
				for(int i=0;i<n/2;i++) {
					sb.append("(");
				}
				for(int i=0;i<n/2;i++) {
					sb.append(")");
				}
				result.put(sb.append(sb).toString(),true);
			}
			
			return result;
		}
		
	}
	
}
