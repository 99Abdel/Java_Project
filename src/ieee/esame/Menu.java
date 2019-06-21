package ieee.esame;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Menu {
	
	private final static String GUIDA = "QUI METTERAI LA DESCRIZIONE DEL GIOCO";
	private final static String TITOLO = "DIVERTITI GIOCANDO A UNIPOLY";
	private final static String VOCE1 = "INIZIA UNA NUOVA PARTITA"; 
	private final static String VOCE2 = "INIZIA UNA NUOVA PARTITA MULTIPLAYER";
	private final static String N_GIOCATORI = "SCRIVERE IL NUMERO DI GIOCATORI (DA 2 A 4):  ";
	
	/**
	 * stampa a video il menu permettendo all'utente di sceglere fra le proposte
	 */
	public static void apriMenu() {
		
		String[] voci = {VOCE1, VOCE2,"GUIDA"};
		MyMenu menuPrincipale = new MyMenu(TITOLO, voci);
		int scelta = menuPrincipale.scegli();
		
		while(scelta != 0) {
			
			switch (scelta) {
			
			case 1: 
				
				Partita.iniziaPartita();
				
				break;
				
			case 2:
				PartitaMultiplayer.iniziaPartita(InputDati.leggiIntero(N_GIOCATORI, 2, 4));
				break;
				
			
			case 3:
				
				System.out.println(GUIDA);
				InputDati.leggiIntero("PER USCIRE INSERIRE 0 -> ");
				break;
			
			}
			
			scelta = menuPrincipale.scegli();

		}
	}

}
