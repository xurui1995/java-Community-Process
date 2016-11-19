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
			System.out.println("任务完成");
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
				System.out.println("司令：[士兵"+N+"个，任务完成]");
			}else{
				System.out.println("司令：[士兵"+N+"个，集合完成]");
				flag=true;
			}
		}
		
	}
	
	public static void main(String[] args) {
		final int N=10;
		Thread[] allSolider=new Thread[N];
		boolean flag=false;
		CyclicBarrier cyclic=new CyclicBarrier(N,new BarrierRun(flag, N));
		System.out.println("集合队伍");
		for(int i=0;i<N;i++){
			System.out.println("士兵"+i+"报道");
			allSolider[i]=new Thread(new Soldier("士兵"+i, cyclic));
			allSolider[i].start();
		}
	}

}
