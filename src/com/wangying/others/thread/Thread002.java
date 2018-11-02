package com.wangying.others.thread;

/**
 * 线程实现   --  实现 Runnable 接口
 * @author wangying.dz3
 *
 */
public class Thread002 implements Runnable{

  private String name;
  
  public Thread002(String name){
    this.name = name ;
    System.out.println("create instance : "+ this.name);
  }
  
  @Override
  public void run() {
    // TODO Auto-generated method stub
    int index = 10;
    while(index-->0) {
      System.out.println("it it the thread impleament by extends Thread!  : "+Thread.currentThread().getName() +"  : "+index);
    }
  }

}
