package universite.EtudiantPackage;


/**
* universite/EtudiantPackage/ResultatHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.ild
* vendredi 9 mars 2018 15 h 23 CET
*/

abstract public class ResultatHelper
{
  private static String  _id = "IDL:universite/Etudiant/Resultat:1.0";

  public static void insert (org.omg.CORBA.Any a, universite.EtudiantPackage.Resultat that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static universite.EtudiantPackage.Resultat extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "matiere",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[1] = new org.omg.CORBA.StructMember (
            "note",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (universite.EtudiantPackage.ResultatHelper.id (), "Resultat", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static universite.EtudiantPackage.Resultat read (org.omg.CORBA.portable.InputStream istream)
  {
    universite.EtudiantPackage.Resultat value = new universite.EtudiantPackage.Resultat ();
    value.matiere = istream.read_string ();
    value.note = istream.read_float ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, universite.EtudiantPackage.Resultat value)
  {
    ostream.write_string (value.matiere);
    ostream.write_float (value.note);
  }

}
