package classes;


public class TypeWork {
	public static String defineTypeWork(String typeWork,String assessment) 
	{
		if(typeWork==null)
			return "�� ����������";
		else if(assessment==null)
			return "�� ��������";
		else
			return "���������";
	}
}
