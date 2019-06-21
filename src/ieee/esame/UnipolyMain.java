package ieee.esame;

public class UnipolyMain {

	public static void main(String[] args) {
		
		System.out.println("-----BENVENUTO ALL' UNIPOLY-----");
		
		LetturaXML.leggiXml();
		
		Menu.apriMenu();
		
		System.out.println("-------- CIAO!!! -------");
	}
	
}
