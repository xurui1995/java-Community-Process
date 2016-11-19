package chapter3;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	public static class Soldier implements Runnable{
		private String soldier;
		private final CyclicBarrier cyclic;
		
		public Soldier(String soldier, CyclicBarrier cyclic) {
			super();
			this.soldier = soldier;
			this.cyclic = cyclic;
		}

		@Override
		public void run() {
			try {
				cyclic.await();
				doWork();
				cyclic.await();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void doWork() {
			try {
				Thread.sleep(Math.abs(new Random().nextInt()%10000));
				
			} catch (Exception e) {
				
			}
			System.out.println("�������");
		}
		
	}
	public static class BarrierRun implements Runnable{
		boolean flag;
		int N;
		

		public BarrierRun(boolean flag, int n) {
			super();
			this.flag = flag;
			N = n;
		}


		@Override
		public void run() {
			if(flag){
				System.out.println("˾�[ʿ��"+N+"�����������]");
			}else{
				System.out.println("˾�[ʿ��"+N+"�����������]");
				flag=true;
			}
		}
		
	}
	
	public static void main(String[] args) {
		final int N=10;
		Thread[] allSolider=new Thread[N];
		boolean flag=false;
		CyclicBarrier cyclic=new CyclicBarrier(N,new BarrierRun(flag, N));
		System.out.println("���϶���");
		for(int i=0;i<N;i++){
			System.out.println("ʿ��"+i+"����");
			allSolider[i]=new Thread(new Soldier("ʿ��"+i, cyclic));
			allSolider[i].start();
		}
	}

}
