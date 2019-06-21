package ieee.esame;

import it.unibs.fp.mylib.InputDati;

public class Partita {
	
	private final static String NOME = "INSERIRE IL PROPRIO NOME: "; 
	
	public static void iniziaPartita() {
		
		String nome = InputDati.leggiStringaNonVuota(NOME);
		
		Giocatore giocatoreA = new Giocatore(nome);
		
		
	}

}
