package universite;

/**
* universite/EtudiantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* jeudi 17 mars 2016 11 h 05 CET
*/

public final class EtudiantHolder implements org.omg.CORBA.portable.Streamable
{
  public universite.Etudiant value = null;

  public EtudiantHolder ()
  {
  }

  public EtudiantHolder (universite.Etudiant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = universite.EtudiantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    universite.EtudiantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return universite.EtudiantHelper.type ();
  }

}
