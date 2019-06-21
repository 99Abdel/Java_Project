package ieee.esame;

import it.unibs.fp.mylib.InputDati;

public class Partita {
	
	private final static String NOME = "INSERIRE IL PROPRIO NOME: "; 
	
	public static void iniziaPartita() {
		
		Mappa mappa = new Mappa();
		LetturaXML.oggettiXml(mappa);
		
		for (int i = 0; i < mappa.getPercorso().size(); i++) {
			System.out.println(mappa.getPercorso().get(i));
		}
		
		Giocatore giocatoreA = new Giocatore(InputDati.leggiStringaNonVuota(NOME));
		
		
	}

}
