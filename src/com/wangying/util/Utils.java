package com.wangying.util;

public class Utils {

	public  static void  swap(int [] source ,int index1,int index2) {
		int temp ;
		temp = source[index1];
		source[index1] = source[index2];
		source[index2] = temp;
	}
	
}
