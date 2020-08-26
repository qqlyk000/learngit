package com.threadTest;

import javax.swing.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: XianDaLi
 * Date: 2020/8/26 14:12
 * Remark:
 * 线程池的实现Demo
 */
public class MyThreadPoolExecutor_Demo {
	private int corePoolSize; // 线程池大小
	private int maxPoolSize;  // 线程池最大大小
	LinkedBlockingQueue<Runnable> workQueue = null; // 任务仓库 (阻塞队列，线程安全的)
	// J.U.C----并发编程工具包 -- 基础类型原子操作封装
	AtomicInteger currentPoolSize = new AtomicInteger(0); // 当前线程池的大小

	public MyThreadPoolExecutor_Demo(int corePoolSize, int maxPoolSize, int workQueueSize) {
		this.corePoolSize = corePoolSize;
		this.maxPoolSize = maxPoolSize;
		workQueue = new LinkedBlockingQueue<>(workQueueSize);
	}

	// 1.线程池的任务
	public class Worker extends Thread{
		Runnable firstTask;
		public Worker(Runnable firstTask){
			this.firstTask = firstTask;
		}
		@Override
		public void run() {
			try{
				// 执行用户提交的任务
				Runnable task = firstTask;
				/*
				 如果事件不为空或者队列中能取出将要执行的事件
				 take();取出队列事件，如果队列为空，则阻塞，等待
				 */
				while (task != null || (task = workQueue.take()) != null){
					task.run();
					task = null;
				}
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("线程池中的一个线程出现异常");
			}
		}
	}

	// 2.execute提交任务
	public void execute(Runnable task) throws Exception { // 做一次数量判断
		// 1.创建线程的步骤 如果核心线程未满，则创建一个worker去执行当前任务 (本质就是一个线程)
		if (currentPoolSize.get() < corePoolSize) {
			if (currentPoolSize.incrementAndGet() <= corePoolSize) { // 线程池大小+1,再判断(类似懒汉单例)
				new Worker(task).start(); // 线程状态切换 ----
			} else { // 如果发现 超过了核心线程数量的大小限制
				currentPoolSize.decrementAndGet(); // 减一
			}
		}

		// 2.不创建线程 提交到任务仓库
		// 如果队列中能放入将要执行的事件offer();
		// 放入事件，如果队列满，则返回false;
		// 任务队列是否已满？没满，则将新提交的任务存储在工作队列里。
		if(workQueue.offer(task)){
			return;
		}

		// 3.是否达到线程池最大数量?没达到，则创建一个新的工作线程来执行任务。
		if (currentPoolSize.get() < maxPoolSize) {
			if (currentPoolSize.incrementAndGet() <= maxPoolSize) { // 线程池大小+1,再判断(类似懒汉单例)
				new Worker(task).start(); // 线程状态切换 ----
			} else { // 如果发现 超过了核心线程数量的大小限制
				currentPoolSize.decrementAndGet(); // 减一
			}
		}
		// 4.拒绝处理这个任务
		throw new Exception("拒绝执行");
	}

}
