package chapter2;

public class NoVisibility {

	private static volatile boolean ready;
	private static  int num;
	private static class ReaderThread extends Thread{
		public void run() {
			
			while(!ready);
			System.out.println(num);
			
			
		}
	}
	public static void main(String[] args) throws InterruptedException {
			new ReaderThread().start();
			Thread.sleep(1000);
			num=42;
			ready=true;
			Thread.sleep(10000);
	}

}
