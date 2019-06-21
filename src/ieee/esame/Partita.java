package ieee.esame;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class Partita {
	
	private final static String NOME = "INSERIRE IL PROPRIO NOME: "; 
	private final static String PARTITA = "\n\n--------LA PARTITA E' INIZIATA BUON DIVERTIMENTO-----\n";
	public static void iniziaPartita() {
		
		Mappa mappa = new Mappa();
		LetturaXML.oggettiXml(mappa);
		
		ArrayList<Casella> caselle_stazione = new ArrayList<Casella>();
		
		for (int i = 0; i < mappa.getPercorso().size(); i++) {
			
			if(mappa.getPercorso().get(i).getTipologia() == LetturaXML.CELLA_STAZIONE) {
				caselle_stazione.add(mappa.getPercorso().get(i));
			}
			
		}
		
		
		
		System.out.println(PARTITA);
		Giocatore giocatoreA = new Giocatore(InputDati.leggiStringaNonVuota(NOME));
		
		System.out.println(giocatoreA.toString());
		
		while(!giocatoreA.isMilionario() && !giocatoreA.inBancarotta()) {
			
			int dado = giocatoreA.lanciaDado(mappa);
			giocatoreA.muoviPedina(mappa, dado, caselle_stazione);
			System.out.println(giocatoreA.toString());
			InputDati.leggiIntero("INSERIRE 0 PER CONTINUARE --> ");
		}
		
		if(giocatoreA.isMilionario()) {
			
			System.out.println(" COMPLIMENTI");
			
		}
		else
			System.out.println(" PECCATO");
	}

}
