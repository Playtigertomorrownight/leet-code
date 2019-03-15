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
	private static  Map<Integer,List<String>> resultCache = new HashMap<Integer,List<String>>();
	public static List<String> generateParenthesis(int n) {
		List<String> result= new ArrayList<String> ();
		result = resultCache.get(n);
		if(null==result) {
			if(n==1) {
				result =  Arrays.asList("()");
			}else {
				result = new ArrayList<String> ();
				Map<String,Boolean>  map = generateParenthesis_2(n);
				result.addAll(map.keySet());
			}
			resultCache.put(n, result);
		}
		return result;
	}
	
	public static Map<String,Boolean> generateParenthesis_2(int n) {
		Map<String,Boolean> result= new HashMap<String,Boolean> ();
		StringBuilder sb = new  StringBuilder();
		boolean iseven = n%2==0;
		int start = n-1;
		while(iseven?(start>=n/2):(start>n/2)) {
			List<String> last_1 = generateParenthesis(start);
			for(String l : last_1) {
				List<String> last_2 = generateParenthesis(n-start);
				for(String l2 : last_2) {
					//并列
					sb.setLength(0);
					result.put(sb.append(l2).append(l).toString(),true);
					sb.setLength(0);
					result.put(sb.append(l).append(l2).toString(),true);
				}
				//包含
				if(start==n-1) {
					sb.setLength(0);
					result.put(sb.append("(").append(l).append(")").toString(),true);
				}
				
			}
			start--;
		}
		result.remove("");
		return result;
	}
	
	/**
	 * STAND SOLUTION
	 * @param n
	 * @return
	 */
	public static List<String> generateParenthesis_(int n) {
        List<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
	
	
	/**
	 * 23. Merge k Sorted Lists
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists(ListNode[] lists) {
		ListNode result = new ListNode(0),temp=result;
		int min;
		int index = 0;
		while(true) {
			min = Integer.MAX_VALUE;
			for(int i=0,len=lists.length;i<len;i++) {
				if(null!=lists[i]&&lists[i].val<min) {
					min = lists[i].val;
					index = i;	
				}
			}
			if(min==Integer.MAX_VALUE) break;
			temp.next = new ListNode(lists[index].val);
			lists[index] = lists[index].next;
			temp = temp.next;
		}
		return result.next;
    }
	
	/**
	 * 23. Merge k Sorted Lists  分治
	 * @param lists
	 * @param start
	 * @param end
	 * @return
	 */
	public static ListNode mergeKLists_(ListNode[] lists,int start ,int end) {
		if(start>end) return null;
		if(start==end) return lists[start];
		int mid = start+(end-start)/2;
		
		ListNode left = mergeKLists_(lists,start,mid);
		ListNode right = mergeKLists_(lists,mid+1,end);
		
		ListNode result = new ListNode(0),temp = result;
		while(left!=null&&right!=null) {
			if(left.val<right.val) {
				temp.next = new ListNode(left.val);
				left = left.next;
			}else {
				temp.next = new ListNode(right.val);
				right = right.next;
			}
			temp = temp.next;
		}
		if(left!=null) temp.next = left;
		if(right!=null) temp.next = right;
		
		return result.next;
    }
	
	
	/**
	 * 24. Swap Nodes in Pairs
	 * @param head
	 * @return
	 */
	public static ListNode swapPairs(ListNode head) {
		ListNode result = new ListNode(0);
		result.next = head;
		ListNode last = result;
		while(null!=head)	{
			if(head.next==null) break;
			ListNode temp  = head.next;
			head.next = temp.next;
			temp.next = head;
			last.next = temp;
			last = head;
			head = head.next;
		}
		return result.next;
    }
	
	/**
	 * 25. Reverse Nodes in k-Group
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {
      if(head==null||k<=1) return head;
      ListNode temp = head;
       
		 
		  return head;
   }
	
}
