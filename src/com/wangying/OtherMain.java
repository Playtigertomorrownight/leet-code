package com.wangying;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OtherMain {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
        TimeUnit.MILLISECONDS,
         new ArrayBlockingQueue<Runnable>(5)); 

    Integer num  = new Integer(127);
    Integer num2  = new Integer(127);
    
    String a = "abcdefghijklmnopqrstuvwxyz";
    StringBuilder sbd = new StringBuilder(a);
    StringBuffer sbf = new StringBuffer(sbd);
    System.out.println(num==num2);
    
    Map map = Collections.synchronizedMap(null);
    LinkedHashMap lm = new LinkedHashMap();
    HashSet set = new HashSet();
    Thread t = new Thread();
     
    
  }
  
  
  private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
};

}
