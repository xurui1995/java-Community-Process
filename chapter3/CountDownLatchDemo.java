package chapter3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable{
	static final CountDownLatch end=new CountDownLatch(10);
	static final CountDownLatchDemo demo=new CountDownLatchDemo();
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec=Executors.newFixedThreadPool(10);
		for(int i=0;i<20;i++){
			exec.submit(demo);
		}
		end.await();//等待检查
		System.out.println("Fire!");//发射
		exec.shutdown();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10)*1000);//模拟检查
			System.out.println("check complete");
			end.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
