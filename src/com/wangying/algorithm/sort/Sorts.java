package com.wangying.algorithm.sort;

/**
 * 排序方法,都是用整数演示
 * @author wangying.dz3
 *
 */
public class Sorts {

	/**
	 * 冒泡排序,每次遍历将最大的放到最后
	 * @param source
	 */
	public static void bubbleSort (int [] source) {
		if(null==source||source.length<=1) return;
		int length = source.length;
		for(int i=1;i<length;i++) {
			for(int j=0;j<length-i;j++) {
				if(source[j]>source[j+1]) {
					swap(source,j,j+1);
				}
			}
		}
		return;
	}
	
	/**
	 * 选择排序法，每次遍历将最小的放到前面
	 * @param source
	 */
	public static void SelectSort(int [] source) {
		if(null==source||source.length<=1) return;
		int length = source.length;
		int minidex = 0;
		for(int i=0;i<length-1;i++) {
			minidex = i;
			for(int j=i+1;j<length;j++) {
				if(source[minidex]>source[j]) {
					minidex = j;
				}
			}
			swap(source,i,minidex);
		}
	}
	
	/**
	 * 插入排序法
	 * @param source
	 */
	public static void InsertionSort(int [] source) {
		if(null==source||source.length<=1) return;
		int length = source.length;
		int temp,index;
		for(int i=1;i<length;i++) {
			index = i;
			temp = source[i];
			while(index>=1 && temp<source[index-1]) {
				source[index] = source[index-1];
				index--;
			}
			source[index] = temp;
		}
	}
	
	/**
	 * 希尔排序
	 * @param source
	 */
	public static void ShellSort(int [] source) {
		if(null==source||source.length<=1) return;
		int length = source.length;
		int temp,gap=1;
		while (gap < length / 3)   gap = gap * 3 + 1;        // 动态定义间隔序列
		for (; gap > 0; gap = (int)Math.floor(gap / 3)) {
	        for (int i = gap; i < length; i++) {
	            temp = source[i];
	            int j = i-gap;
	            for ( ; j >= 0 && source[j]> temp; j-=gap) {
	            	source[j + gap] = source[j];
	            }
	            source[j + gap] = temp;
	        }
	    } 
	}
	
	/**
	 * 归并排序
	 * @param source
	 * @return
	 */
	public static int []  mergeSort(int [] source) {
		if(null==source||source.length<2) return source;
		int length = source.length;
		int mid = (int)Math.floor(length/2);
		int [] left = new int[mid];
		System.arraycopy(source, 0, left, 0, mid);
		int [] right = new int[length-mid];
		System.arraycopy(source, mid, right, 0, length-mid);
		return merge(mergeSort(left),mergeSort(right));
	}
	
	private static int [] merge(int [] left,int [] right) {
		int [] result = new  int [left.length+right.length];
		int indexLeft = 0, indexRight = 0;
		while(indexLeft<left.length&&indexRight<right.length) {
			if(left[indexLeft]<right[indexRight]) {
				result[indexLeft+indexRight] = left[indexLeft++];
			}else {
				result[indexLeft+indexRight] = right[indexRight++];
			}
		}
		while(indexLeft<left.length) {
			result[indexLeft+indexRight] = left[indexLeft++];
		}
		while(indexRight<right.length) {
			result[indexLeft+indexRight] = right[indexRight++];
		}
		return result;
	}
	
	
	/**
	 * 快速排序
	 * @param length
	 */
	public static  void qickSort(int [] source) {
		qickSort_(source,0,source.length-1);
	}
	
	private static void qickSort_(int [] source,int left,int right) {
		if(left<0||left>=right) return;
		int privot  = left;
		int privotIndex = left+1;
		for(int i = privotIndex;i<=right;i++) {
			if(source[i]<source[privot]) {
				swap(source,privotIndex,i);
				privotIndex++;
			}
		}
		--privotIndex;
		swap(source,privotIndex,privot);
		qickSort_(source,left,privotIndex-1);
		qickSort_(source,privotIndex+1,right);
		return ;
	}
	
	/**
	 * 堆排序
	 * @param source
	 */
	public static void HeapSort(int [] source) {
		if(null==source||source.length<2) return ;
		int length = source.length;
		//先构建一下确保每个节点的值大于子节点
		for(int  i = (int)Math.floor(length/2);i>=0;i--) {
			adaptNode(source,i,length);
		}
		//将最大值不断往后移动
		for(int i=length-1;i>0;i--) {
			swap(source,0,i);
			adaptNode(source,0,--length);
		}
	}
	
	private static void  adaptNode(int [] arr,int index , int len) {    //堆调整
		int left = (index<<1)+1;
		int right = (index<<1)+2;
		int max = index;
		if(left<len && arr[left]>arr[max]) {
			max = left;
		}
		if(right<len && arr[right]>arr[max]) {
			max = right;
		}
		if(max != index) {
			swap(arr,index,max);    //将最大点置顶
			adaptNode(arr,max,len);     //递归处理已经比变换的节点
		}
	}
	
	
	/**
	 * 计数排序，不基于比较的排序方法
	 * @param source
	 */
	public static void countingSort(int [] source) {
		if(null==source||source.length<2) return ;
		int max=0,min=Integer.MAX_VALUE;
		for(int i=0,len=source.length;i<len;i++) {
			max = source[i]>max?source[i]:max;
			min = source[i]<min?source[i]:min;
		}
		int [] sortArray = new int [max-min+1];
		for(int i=0,len=source.length;i<len;i++) {
			sortArray[source[i]-min]++;
		}
		int index = 0;
		for(int i=0,len=sortArray.length;i<len;i++) {
			while(sortArray[i]>0) {
				source[index] = i+min;
				index++;
				sortArray[i]--;
			}
		}
	}
	
	
	
	private  static void  swap(int [] source ,int index1,int index2) {
		int temp ;
		temp = source[index1];
		source[index1] = source[index2];
		source[index2] = temp;
	}
	
}
