import java.util.*;



public class Main {

	private static int BUFSIZE = 20;
	
	
	public static void main(String[] args) {
		
		int PRODUCETIME = 30;
		int CONSUMETIME = 50;
		
		
		RingBuffer buffer = new RingBuffer(BUFSIZE);
		
		Thread provider = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i <= 100; i++) {
					try {
						buffer.put(i);
						Thread.sleep(PRODUCETIME);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i <= 100; i++) {
					try {
						buffer.get();
						Thread.sleep(CONSUMETIME);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		try {
			provider.start();
			consumer.start();
			provider.join();
			consumer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("-----  손실 데이터 -----");
		for(int i = 0 ; i < buffer.trash.size(); i++) {
			System.out.print(buffer.trash.get(i) + " ");
			if(i % 10 == 9)
				System.out.println("");
		}
		
	}
	
}
