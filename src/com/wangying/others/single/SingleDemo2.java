package com.wangying.others.single;

/**
 * 懒汉式单例模式
 * @author wangying.dz3
 *
 */
public class SingleDemo2 {

  private volatile static  SingleDemo2 instance = null;
  
  private SingleDemo2() {
    
  }
  
  public static SingleDemo2 getInstance() {
    
    if(null == instance) {
      synchronized(SingleDemo2.class) {   //防止多线程竞争
        if(null == instance) {   //防止多个线程重复创建
          instance = new SingleDemo2();
        }
      }
    }
    
    return instance;
  }
  
}
