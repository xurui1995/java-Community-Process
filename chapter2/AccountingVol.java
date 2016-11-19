package chapter2;

public class AccountingVol implements Runnable{
	static AccountingVol instance=new AccountingVol();
	static volatile int i=0;
	public static synchronized void increase(){
		i++;
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread(instance);
		Thread t2=new Thread(instance);
		t1.start();
		t2.start();
		t1.join();t2.join();
		System.out.println(i);
	}

	public void run() {
		for(int j=0;j<10000;j++){
			increase();
		}
	}

}
