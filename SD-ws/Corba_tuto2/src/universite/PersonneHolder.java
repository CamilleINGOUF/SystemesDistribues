package universite;

/**
* universite/PersonneHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.ild
* vendredi 9 mars 2018 15 h 23 CET
*/

public final class PersonneHolder implements org.omg.CORBA.portable.Streamable
{
  public universite.Personne value = null;

  public PersonneHolder ()
  {
  }

  public PersonneHolder (universite.Personne initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = universite.PersonneHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    universite.PersonneHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return universite.PersonneHelper.type ();
  }

}