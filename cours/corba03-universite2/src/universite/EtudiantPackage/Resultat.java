package universite.EtudiantPackage;


/**
* universite/EtudiantPackage/Resultat.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 11 mars 2016 15 h 49 CET
*/

public final class Resultat implements org.omg.CORBA.portable.IDLEntity
{
  public String matiere = null;
  public float note = (float)0;

  public Resultat ()
  {
  } // ctor

  public Resultat (String _matiere, float _note)
  {
    matiere = _matiere;
    note = _note;
  } // ctor

} // class Resultat
