package com.wangying.algorithm.sort;

public class SortAgin {
  
  private static long startTime;
  
  /**
   * 冒泡排序，两量比较，将最大（小）值放到最后
   * @param arr
   */
  public static void BubbleSort(int [] arr) {
    startTime = System.currentTimeMillis();
    int len = arr.length;
    for(int i=0;i<len-1;i++) {
      for(int j=0;j<len-i-1;j++) {
        if(arr[j]>arr[j+1])
          swap(arr,j,j+1);
      }
    }
    printUseTime(System.currentTimeMillis()-startTime,"bubble");
  }
  
  /**
   * 遍历n-1趟，每趟将最小（大）值找到并放到前面有序列
   * @param arr
   */
  public static void SelectSort(int [] arr) {
    startTime = System.currentTimeMillis();
    int len = arr.length,minIndex=0;
    for(int i=0;i<len-1;i++) {
      minIndex = i;
      for(int j=i+1;j<len;j++) {
        if(arr[minIndex]>arr[j])
          minIndex = j;
      }
      swap(arr,i,minIndex);
    }
    printUseTime(System.currentTimeMillis()-startTime,"select");
  }
  
  /**
   * 插入排序，遍历每个元素，狗仔该元素之前的所有元素的有序列
   * @param arr
   */
  public static void InsertSort(int [] arr) {
    startTime = System.currentTimeMillis();
    int len = arr.length,index=0,temp=0;
    for(int i=1;i<len;i++) {
      index = i-1;
      temp = arr[i];
      while(index>=0&&temp<arr[index]) {
        arr[index+1] = arr[index];
        index--;
      }
      arr[index+1] = temp;
    }
    printUseTime(System.currentTimeMillis()-startTime,"insert");
  }
  
  
  /**
   * 希尔排序，使用增量序列，分割数组分别插入排序
   * @param arr
   */
  public static void shellSort(int [] arr) {
    startTime = System.currentTimeMillis();
    int len = arr.length,index=0,temp=0;
   // increment
    for(int increment=(len/3+1);increment>0;increment=(increment/3+1)) {
      //System.out.println("increment : "+increment);
      for(int i=increment;i<len;i++) {
        index = i-increment;
        temp = arr[i];
        while(index>=0&&temp<arr[index]) {
          arr[index+increment] = arr[index];
          index-=increment;
        }
        arr[index+increment] = temp;
      }
      if(increment==1) break;
    }
    printUseTime(System.currentTimeMillis()-startTime,"shell");
  }
  
  /**
   * 归并排序，分成子序列，分别排序
   * 
   */
  public static void MergeSort(int [] arr) {
    
  }
  private static void merge_sub(int [] arr,int left,int right) {
    if(left>right) return ;
    
  }
  
  private static void swap(int [] arr,int i,int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
  
  private static void printUseTime(long useTime,String SortType) {
    StringBuilder sb = new StringBuilder(SortType);
    sb.append(" sort sort use :").append(useTime).append(" millis");
    System.out.println(sb.toString());
  }

}
