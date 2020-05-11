package ieee.esame;

import java.util.ArrayList;
import java.util.PriorityQueue;

import it.unibs.fp.mylib.InputDati;

/**
 * PER GESTIRE LA PARTITA CON PIU GIOCATORI
 * 
 * @author acer
 *
 */
public class PartitaMultiplayer {

	public final static String NOME = "INSERIRE IL PROPRIO NOME: ";
	public final static String PARTITA = "\n\n--------LA PARTITA E' INIZIATA BUON DIVERTIMENTO-----\n";

	public static void iniziaPartita(int nGiocatori) {

		Mappa mappa = new Mappa();
		LetturaXML.oggettiXml(mappa);

		// DUE LISTE UNA DEI GIOCATORI E UNA PER INSERIRE AL SUO INTERNO I PERDENTI
		// COSI CHE IL GIOCO POSSA CONTINUARE E VENGANO SALVATI I PERDENTI POI NELLA
		// CLASSIFICA
		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
		ArrayList<Giocatore> perdenti = new ArrayList<Giocatore>();

		// SALVO LE CASELLE STAAZIONE IN UN ULTERIORE ARRAY PER GESTIRE L'ECCEZIONE SUL
		// SALTO E IL DENARO.
		ArrayList<Casella> caselle_stazione = new ArrayList<Casella>();
		for (int i = 0; i < mappa.getPercorso().size(); i++) {

			if (mappa.getPercorso().get(i).getTipologia() == LetturaXML.CELLA_STAZIONE) {
				caselle_stazione.add(mappa.getPercorso().get(i));
			}

		}

		// CREAZIONE GIOCATORI
		System.out.println(PARTITA);
		for (int i = 0; i < nGiocatori; i++) {
			giocatori.add(new Giocatore(InputDati.leggiStringaNonVuota((i + 1) + ")" + NOME)));
		}

		// STAMPA A VIDEO DELLA SITUAZIONE INIZIALE
		for (int i = 0; i < giocatori.size(); i++) {
			System.out.println();
			System.out.println(giocatori.get(i).toString());
		}

		boolean vittoria = false;
		int i = 0;

		// CONDIZIONE PER CONTINUARE IL CICLO
		while (giocatori.size() > 0 && !vittoria) {

			vittoria = turnoGiocatori(mappa, giocatori, caselle_stazione, i, perdenti);
			if (giocatori.size() > 0)
				i = (i + 1) % giocatori.size();
		}

		stampaClassifica(perdenti, giocatori);

	}

	/**
	 * METODO CHE GESTISCE IL TURNO DEL GIOCATORE I-ESIMO
	 * 
	 * @param mappa
	 * @param giocatori
	 * @param caselle_stazione
	 * @param i
	 * @param perdenti
	 * @return se un giocatore ha vinto o meno
	 */
	private static boolean turnoGiocatori(Mappa mappa, ArrayList<Giocatore> giocatori,
			ArrayList<Casella> caselle_stazione, int i, ArrayList<Giocatore> perdenti) {

		
		giocatori.get(i).muoviPedina(mappa, caselle_stazione);
		System.out.println(giocatori.get(i).toString());
		InputDati.leggiIntero("INSERIRE 0 PER CONTINUARE --> ");

		if (giocatori.get(i).inBancarotta()) {
			System.out.println(giocatori.get(i).getNome() + "HAI PERSO PERCHE' SEI FINITO IN BANCAROTTA!");
			perdenti.add(giocatori.get(i));
			giocatori.remove(i);
		} else if (giocatori.get(i).isMilionario() || giocatori.size() <= 1) {

			System.out.println(giocatori.get(i).getNome() + "HAI VINTO");
			return true;
		}
		return false;
	}

	/**
	 * stampa la classifica finale dei giocatori
	 * 
	 * @param perdenti
	 * @param giocatori
	 */
	private static void stampaClassifica(ArrayList<Giocatore> perdenti, ArrayList<Giocatore> giocatori) {

		System.out.println("\n\nECCO LA VOSTRA CLASSIFICA: ");

		
		int i = 0;
		
		if (!giocatori.isEmpty()) {

			for (i = 0; i < giocatori.size(); i++) {
				System.out.println(( giocatori).get(i).toString());
			}

		}
		if (!perdenti.isEmpty()) {
			for (int j = perdenti.size() - 1; j > 0; j--) {
				System.out.println(perdenti.get(j));
			}
		}

	}

}
