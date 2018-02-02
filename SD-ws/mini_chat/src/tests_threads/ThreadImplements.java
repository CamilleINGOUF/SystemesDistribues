package tests_threads;

public class ThreadImplements implements Runnable{

	private long delay;
	
	public ThreadImplements(long delay)
	{
		this.delay = delay;
	}
	
	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++) 
		{
			System.out.println(Thread.currentThread().getName()+" "+i);
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		Thread t1 = new Thread(new ThreadImplements(100)),
			   t2 = new Thread(new ThreadImplements(300));
		t1.start();
		t2.start();
	}

}
