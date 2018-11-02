package com.wangying.others.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程实现，通过Callable 接口实现
 * @author wangying.dz3
 *
 */
public class Thread003 {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Task call = new Task();
    System.out.println(Thread.currentThread().getName());
    List<FutureTask<String>> tasks = new ArrayList<FutureTask<String>>();
    int index = 10;
    while(index-->0) {
      call.setNum(index);
      FutureTask<String> task = new FutureTask<String>(call);
      tasks.add(task);
      new Thread(task).start();
    }
    Thread.sleep(3000);
    for(FutureTask<String> t:tasks) {
      System.out.println(t.get());
    }
    // 
  }
  
}

class Task implements Callable<String> {

  volatile int num = 0 ;
  
  public Task() {
  }
  
  @Override
  public String call() throws Exception {
    // TODO Auto-generated method stub
    System.out.println(Thread.currentThread().getName());
    synchronized(this) {
      String msg = "it the thred implement by Callable Interface "+ num;
      System.out.println(msg);
      return "result = "+(num + 16)+"   "+msg;
    }
  }
  
  public void setNum(int num ) {
    System.out.println("set num == "+num);
    synchronized(this) {
      this.num = num;
    }
    
  }
  
}