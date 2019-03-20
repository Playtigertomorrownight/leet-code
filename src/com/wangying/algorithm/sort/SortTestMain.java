package com.wangying.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class SortTestMain {

  public static void main(String[] args) {
    int arr [] = new int [300000];
    //createRandomArray(arr);
//    printArray(arr,"before bubble sort:");
//    SortAgin.BubbleSort(arr);
//    printArray(arr,"after bubble sort:");
//    System.out.println();
//    createRandomArray(arr);
//    printArray(arr,"before select sort:");
//    SortAgin.SelectSort(arr);
//    printArray(arr,"after select sort:");
//    createRandomArray(arr);
////    printArray(arr,"before insert sort:");
//    SortAgin.InsertSort(arr);
//    printArray(arr,"after insert sort:");
    createRandomArray(arr);
//    printArray(arr,"before shell sort:");
    SortAgin.shellSort(arr);
//    long startTime = System.currentTimeMillis();
//    Arrays.sort(arr);
//    System.out.println(System.currentTimeMillis()-startTime);
   // printArray(arr,"after shell sort:");
  }

  
  private static void createRandomArray(int [] arr) {
    if(null==arr) return ;
    Random r = new Random(System.currentTimeMillis());
    int index=0, len=arr.length;
    while(index<len) {
      arr[index] = r.nextInt(len*3);
      index++;
    }
  }
  
  /**
   * 输出数组
   * @param arr
   */
  private static void printArray(int [] arr,String msg) {
    if(null==arr) return ;
    System.out.println(msg);
    int index=0, len=arr.length;
    while(index<len) {
      System.out.print(arr[index]);
      if(index!=len-1) {
        System.out.print(" , ");
      }
      index++;
    }
    System.out.println();
  }
  
}
