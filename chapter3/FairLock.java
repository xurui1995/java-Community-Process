package chapter3;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable{

	public static ReentrantLock fariLock=new ReentrantLock(true);
	
	public static void main(String[] args) {
			FairLock f=new FairLock();
			Thread t1=new Thread(f,"t1");
			Thread t2=new Thread(f,"t2");
			t1.start();t2.start();
	}

	public void run() {
		while(true){
			try{
				fariLock.lock();
				System.out.println(Thread.currentThread().getName()+" »ñµÃËø");
			}
			finally{
				fariLock.unlock();
			}
		}
	}

}
