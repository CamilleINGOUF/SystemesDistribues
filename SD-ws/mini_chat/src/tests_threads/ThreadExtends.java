package tests_threads;

public class ThreadExtends extends Thread{

	private long delay;
	
	public ThreadExtends(long delay)
	{
		this.delay = delay;
	}
	
	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++) 
		{
			System.out.println(getName()+" "+i);
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		Thread t1 = new ThreadExtends(300);
		Thread t2 = new ThreadExtends(100);
		t1.start();
		t2.start();
	}

}
