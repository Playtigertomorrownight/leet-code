package com.wangying.others.thread;

/**
 * 多线程实现
 * @author wangying.dz3
 *
 */
public class Thread001 extends Thread{

  private String name;
  
  public Thread001(String name){
    this.name = name ;
    System.out.println("create instance : "+ this.name);
  }
  
   int index = 10;
  
  public void run() {
    while(index-->0) {
      System.out.println("it it the thread impleament by extends Thread!  : "+Thread.currentThread().getName() +"  : "+index);
    }
  }
  
}
