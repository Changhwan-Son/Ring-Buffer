import java.util.Vector;

public class RingBuffer {
	
	// 링버퍼 크기
	// 데이터 담을 배열
	// 마지막 데이터 인덱스
	// 담긴 데이터 크기 
	// 맨 앞 데이터 인덱스 
	int bufSize;
	int [] store;
	int index = 0;
	int storeSize = 0;
	int front = 0;
	
	// 소실된 데이터 따로 담아두려고 만듦 
	Vector <Integer> trash;
	
	RingBuffer (int n) {
		bufSize = n;
		store = new int[bufSize];
		trash = new Vector<Integer>();
	}
	
	public synchronized void put(int num) throws InterruptedException {
		
		// Buffer가 Full 인 경우 
		if(storeSize == bufSize) {
			//wait();
			System.out.println("Buffer Full!!! 데이터 손실 : " +num);
			trash.add(num);
		}
		else {
			store[index] = num;
			index++;
			System.out.println("put " + store[index - 1]);
			
			// 마지막 index일 경우 0으로 돌아감 
			if(index == bufSize)
				index = 0;
			
			storeSize += 1;
			
		}
		//notify();
	}
	
	public synchronized int get() throws InterruptedException{
		int result = -1;
		
		// Buffer가 비어있을 경우 
		if(storeSize == 0) {
			//wait();
			System.out.println("Buffer 비어있음!!!");
		}
		else {
			result = store[front];
			front = front + 1;
			storeSize -= 1;
			
			// 맨 앞을 가리키는 front가 끝에 다다른 경우 0으로 돌아감 
			if(front ==  bufSize)
				front = 0;
			
			System.out.println("	get " + result);
		}
		
		//notify();
		return result;
	}
}
