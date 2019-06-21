package ieee.esame;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Menu {
	
	private final static String GUIDA = "QUI METTERAI LA DESCRIZIONE DEL GIOCO";
	private final static String TITOLO = "DIVERTITI GIOCANDO A UNIPOLY";
	private final static String VOCE1 = "INIZIA UNA NUOVA PARTITA"; 
	
	
	public static void apriMenu() {
		
		String[] voci = {VOCE1, "GUIDA"};
		MyMenu menuPrincipale = new MyMenu(TITOLO, voci);
		int scelta = menuPrincipale.scegli();
		
		while(scelta != 0) {
			
			switch (scelta) {
			
			case 1: 
				
				Partita.iniziaPartita();
				
				break;
				
			
			case 2:
				
				System.out.println(GUIDA);
				InputDati.leggiIntero("PER USCIRE INSERIRE 0 -> ");
				break;
			
			}
			
			scelta = menuPrincipale.scegli();

		}
	}

}
