package classes;


public class TypeWork {
	public static String defineTypeWork(String typeWork,String assessment) 
	{
		if(typeWork==null)
			return "Не отправлена";
		else if(assessment==null)
			return "На проверке";
		else
			return "Проверена";
	}
}
