package tests_threads;

public class Test_Synchronized 
{

	private static double taux = 0.05;

	static Double solde = new Double(0);

	public void crediter(double val) 
	{
		synchronized (solde) {
			print("Solde avant crédit : "+solde);
			try {
				print("Fait un gros calcul et va crediter le compte de : " + val) ;
				Thread.sleep(4000) ;
			} catch (InterruptedException ex) {
				ex.printStackTrace() ;
			}

			solde += val ;
			print("Solde apres credit : " + solde );	
		}
	}

	public void calculInterets() 
	{
		synchronized (solde) {
			print("Calcul des interets sur solde a " + solde );
			solde = solde + solde * taux ;
			print("Solde apres interets : " + solde );
		}
	}

	public static void print(String msg)
	{
		System.out.println(Thread.currentThread().getName() + ": " + msg);
	}

	public static void main(String[] args) throws InterruptedException 
	{
		final Test_Synchronized t = new Test_Synchronized();

		Runnable runA = new Runnable() {

			@Override
			public void run() {
				t.crediter(1000);
			}
		};

		Thread ta = new Thread(runA,"Site Internet");
		System.out.println("Lancement du credit par internet");
		ta.start();

		Thread.sleep(500);

		Runnable runB = new Runnable() {

			@Override
			public void run() {
				t.calculInterets();
			}
		};

		Thread tb = new Thread(runB, "Agence");
		System.out.println("Lancement calcul des interets");
		tb.start();
	}

}
