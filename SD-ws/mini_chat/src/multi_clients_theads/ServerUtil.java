package multi_clients_theads;

public class ServerUtil {
	
	public static final int WHISPER = 0;
	public static final int ALL = 1;
	
	
	public static String[] getSender(String rawMessage)
	{
		if(!rawMessage.substring(0,6).equals("%from%"))
			return null;
		
		int indexEndFrom = rawMessage.indexOf("%/from%");
		String name = rawMessage.substring(6, indexEndFrom);
		
		return new String[]{name,rawMessage.substring(indexEndFrom+7, rawMessage.length())};
	}
	
	public static int getMessageType(String rawMessage)
	{
		if(rawMessage.startsWith("%all%"))
		{
			return ALL;
		}
		return WHISPER;
	}
	
	public static String cleanMessageFortWhisper(String rawMessage)
	{
		return rawMessage.substring(rawMessage.indexOf("%/whisper%") + "%/whisper%".length());
	}
	
	public static String[] getTargetsWhisper(String rawMessage)
	{
		String rawName = rawMessage.substring("%whisper%".length(),rawMessage.indexOf("%/whisper%"));
		return rawName.split(";");
	}
	
	public static String cleanMessageForAll(String rawMessage)
	{
		return rawMessage.substring(5);
	}
}
