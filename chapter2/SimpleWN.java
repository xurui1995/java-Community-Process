package chapter2;

public class SimpleWN {

	final static Object OBJECT=new Object();
	public static class T1 extends Thread{
		public void run() {
			
			synchronized (OBJECT) {
				System.out.println(System.currentTimeMillis()+":T1 start");
				try {
					System.out.println(System.currentTimeMillis()+":T1 wait for object");
					OBJECT.wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()+":T1 end");
			}
		}
	}
	public static class T2 extends Thread{
		public void run() {
			
			synchronized (OBJECT) {
			
				
					System.out.println(System.currentTimeMillis()+":T2 start and notify one thread");
					OBJECT.notify();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()+":T2 end");
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1=new T1();
		Thread t2=new T2();
		t1.start();
		t2.start();

	}

}
