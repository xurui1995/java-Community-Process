package chapter3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable{
	public static ReentrantLock lock=new ReentrantLock();
	public static Condition condition=lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		ReenterLockCondition r=new ReenterLockCondition();
		Thread t1=new Thread(r);
		t1.start();
		Thread.sleep(2000);
		lock.lock();
		condition.signal();
		lock.unlock();
	}

	public void run() {
		try{
			lock.lock();
			condition.await();
			System.out.println("Thread is going on");
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
	}

}
