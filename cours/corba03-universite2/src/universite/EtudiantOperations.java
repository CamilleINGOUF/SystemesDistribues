package universite;


/**
* universite/EtudiantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 11 mars 2016 15 h 49 CET
*/

public interface EtudiantOperations  extends universite.PersonneOperations
{
  void ajouterNoteDans (float note, String matiere);
  String[] listeMatieres ();
  float[] listeNotes ();
  float moyenne ();
  universite.EtudiantPackage.Resultat[] listeResultats ();
  int numCarte ();
} // interface EtudiantOperations
