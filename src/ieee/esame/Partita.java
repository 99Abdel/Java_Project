package ieee.esame;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class Partita {
	
	public final static String NOME = "INSERIRE IL PROPRIO NOME: "; 
	public final static String PARTITA = "\n\n--------LA PARTITA E' INIZIATA BUON DIVERTIMENTO-----\n";
	
	/**
	 * crea il giocatore e avvia la partita che continua finche il giocatore diventa milionario o va in bancarotta
	 */
	public static void iniziaPartita() {
		
		Mappa mappa = new Mappa();
		LetturaXML.oggettiXml(mappa);
		
		//SALVO LE CASELLE STAAZIONE IN UN ULTERIORE ARRAY PER GESTIRE L'ECCEZIONE SUL SALTO E IL DENARO.
		ArrayList<Casella> caselle_stazione = new ArrayList<Casella>();
		
		for (int i = 0; i < mappa.getPercorso().size(); i++) {
			
			if(mappa.getPercorso().get(i).getTipologia() == LetturaXML.CELLA_STAZIONE) {
				caselle_stazione.add(mappa.getPercorso().get(i));
			}
			
		}
		
		
		System.out.println(PARTITA);
		Giocatore giocatoreA = new Giocatore(InputDati.leggiStringaNonVuota(NOME));
		
		System.out.println(giocatoreA.toString());
		
		//CICLO CHE DETERMINA LA CONTUNUAZIONE DEL GIOCO
		while(!giocatoreA.isMilionario() && !giocatoreA.inBancarotta()) {
			
			
			giocatoreA.muoviPedina(mappa, caselle_stazione);
			System.out.println(giocatoreA.toString());
			
			//inserire un numero SOLO PER FERMARE MOMENTANEAMENTE LA STAMPA CICLICA
			InputDati.leggiIntero("INSERIRE 0 PER CONTINUARE --> ");
		}
		//CONDIZIONI DI USCITA 
		if(giocatoreA.isMilionario()) {
			
			System.out.println(" COMPLIMENTI");
			
		}
		else
			System.out.println(" PECCATO");
	}

}
