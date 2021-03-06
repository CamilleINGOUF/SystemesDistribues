package universite.application;

import java.io.BufferedReader;
import java.io.FileReader;

import org.omg.CORBA.ORB;

import universite.Etudiant;
import universite.EtudiantHelper;
import universite.EtudiantPackage.Resultat;

public class ClientUniversite {

	public static void main(String[] args) throws Exception{
		ORB orb = ORB.init(args, null) ;
		
		BufferedReader br = new BufferedReader(new FileReader("ior.txt")) ;
		String ior = br.readLine() ;
		br.close() ;
		
		org.omg.CORBA.Object o = orb.string_to_object(ior) ;
		
		Etudiant etudiant = EtudiantHelper.narrow(o) ;
		etudiant.nom("Bill");
		etudiant.ajouterNoteDans(18.5f, "Informatique");
		etudiant.ajouterNoteDans(12f, "Mathematiques");
		etudiant.ajouterNoteDans(17f, "Anglais");
		
		System.out.println("Information sur " + etudiant.nom() + " : ");
		
		System.out.print("Liste des matières passées : ");
		String[] matieres = etudiant.listeMatieres() ;
		for (String m : matieres){
			System.out.print(m + " ");
		}
		System.out.println("");
		
		System.out.print("Liste des notes : ");
		float[] notes = etudiant.listeNotes() ;
		for (float n : notes){
			System.out.print(n + " ");
		}
		System.out.println("");
		
		System.out.println("Liste des résultats : ");
		Resultat[] resultats = etudiant.listeResultats() ;
		for (Resultat r : resultats){
			System.out.println(r.matiere + " : " + r.note);
		}
		
		System.out.println("Moyenne de " + etudiant.nom() + " : " + etudiant.moyenne());
	}
	
}
