package ieee.esame;

import it.unibs.fp.mylib.InputDati;

public class Partita {
	
	private final static String NOME = "INSERIRE IL PROPRIO NOME: "; 
	private final static String PARTITA = "\n\n--------LA PARTITA E' INIZIATA BUON DIVERTIMENTO-----\n";
	public static void iniziaPartita() {
		
		Mappa mappa = new Mappa();
		LetturaXML.oggettiXml(mappa);
		
		for (int i = 0; i < mappa.getPercorso().size(); i++) {
			System.out.println(mappa.getPercorso().get(i));
		}
		
		System.out.println(PARTITA);
		Giocatore giocatoreA = new Giocatore(InputDati.leggiStringaNonVuota(NOME));
		
		while(!giocatoreA.isMilionario() || !giocatoreA.inBancarotta()) {
			
			
			
		}
	}

}
