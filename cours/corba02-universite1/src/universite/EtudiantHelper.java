package universite;


/**
* universite/EtudiantHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* jeudi 17 mars 2016 11 h 05 CET
*/

abstract public class EtudiantHelper
{
  private static String  _id = "IDL:universite/Etudiant:1.0";

  public static void insert (org.omg.CORBA.Any a, universite.Etudiant that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static universite.Etudiant extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (universite.EtudiantHelper.id (), "Etudiant");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static universite.Etudiant read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_EtudiantStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, universite.Etudiant value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static universite.Etudiant narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof universite.Etudiant)
      return (universite.Etudiant)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      universite._EtudiantStub stub = new universite._EtudiantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static universite.Etudiant unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof universite.Etudiant)
      return (universite.Etudiant)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      universite._EtudiantStub stub = new universite._EtudiantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
