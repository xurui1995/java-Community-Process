package chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class IntLock implements Runnable {

	public static ReentrantLock lock1=new ReentrantLock();
	public static ReentrantLock lock2=new ReentrantLock();
	int lock;
	public IntLock(int i) {
		this.lock=i;
	}

	public static void main(String[] args) throws InterruptedException {
		IntLock r1=new IntLock(1);
		IntLock r2=new IntLock(2);
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();t2.start();
		Thread.sleep(1000);
		t2.interrupt();
	}

	public void run() {
		try {
			if (lock==1) {
				lock1.lockInterruptibly();
				Thread.sleep(500);
				
				lock2.lockInterruptibly();
				System.out.println(Thread.currentThread().getId()+"work");
			}else{
				lock2.lockInterruptibly();
				Thread.sleep(500);
				
				lock1.lockInterruptibly();
				System.out.println(Thread.currentThread().getId()+"work");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(lock1.isHeldByCurrentThread())
				lock1.unlock();
			if(lock2.isHeldByCurrentThread())
				lock2.unlock();
			System.out.println(Thread.currentThread().getId()+" Ïß³ÌÍË³ö");
		}
	}

}
