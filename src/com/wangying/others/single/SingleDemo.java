package com.wangying.others.single;

/**
 * 饿汉式单例
 * @author wangying.dz3
 *
 */
public class SingleDemo {
  
  private static  SingleDemo SingleDemo =  new SingleDemo();
  
  private SingleDemo() {
    
  }
  
  public static SingleDemo  getInsatnce() {
    return SingleDemo;
  }

}
