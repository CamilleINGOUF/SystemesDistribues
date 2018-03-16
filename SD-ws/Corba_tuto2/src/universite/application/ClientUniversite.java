package universite.application;

import java.io.BufferedReader;
import java.io.FileReader;

import org.omg.CORBA.ORB;

import universite.Etudiant;
import universite.Fac;
import universite.FacHelper;
import universite.EtudiantPackage.Resultat;

public class ClientUniversite {
	public static void main(String[] args) throws Exception {
		ORB orb = ORB.init(args, null) ;

		BufferedReader br = new BufferedReader(new FileReader("ior.txt")) ;
		String ior = br.readLine() ;
		br.close() ;

		org.omg.CORBA.Object o = orb.string_to_object(ior) ;

		Fac fac = FacHelper.narrow(o) ;
		
		fac.ajouterEtudiant("Guy");
		fac.ajouterEtudiant("Billy");
		fac.ajouterEtudiant("Jacky");
		fac.ajouterEtudiant("Nicolas");
		fac.ajouterEtudiant("Bertran");
		fac.ajouterEtudiant("Bruno Crépis");
		for(Etudiant etu : fac.listerEtudiants())
		{
			System.out.println(etu.nom() +" -> "+etu.numCarte());
			etu.ajouterNoteDans((int)(Math.random()*20), "Crypto");
			etu.ajouterNoteDans((int)(Math.random()*20), "Anglais");
			etu.ajouterNoteDans((int)(Math.random()*20), "SD");
			etu.ajouterNoteDans((int)(Math.random()*20), "AA");
			System.out.println("Moyenne : "+etu.moyenne());
			System.out.println("Résultats : ");
			for(Resultat r : etu.listeResultats())
			{
				System.out.println(r.matiere+" => "+r.note);
			}
			System.out.println("============================");
		}
	}
}
