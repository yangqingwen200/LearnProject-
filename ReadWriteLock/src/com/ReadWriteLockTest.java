package com;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	private static ReadWriteLock rwl = new ReentrantReadWriteLock();
	private static Map<String, Object> map = new HashMap<>();

	public static void main(String[] args) {

		for(int i=0; i<10; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					new ReadWriteLockTest().get("aa");
				}
			});
			t.start();
		}
	}

	public Object get(String arg) {
		rwl.readLock().lock(); //锁定读锁
		Object obj = null;
		try {
			obj = map.get(arg);
			if (obj == null) {
				rwl.readLock().unlock(); //释放读锁
				rwl.writeLock().lock(); //锁定写锁
				try {
					/*
					 * 重新取值, 在没有写入值时, 值为空
					 * 在写锁释放之前, map中已经有值了, 所以重新取一次
					 */
					obj = map.get(arg); 
					if (obj == null) {
						System.out.println(Thread.currentThread().getName() + ": " + arg + " is null");
						obj = "123"; //从DB里面获取数据
						map.put(arg, obj); //放入值到map中
					}
				} finally {
					rwl.writeLock().unlock(); //释放写锁
				}
				rwl.readLock().lock(); //锁定读锁
			} else {
				System.out.println(Thread.currentThread().getName() + ": " + arg + " is not null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
		return obj;
	}

}
