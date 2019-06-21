package ieee.esame;

import java.util.ArrayList;

import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.mylib.InputDati;

public class Giocatore {

	private final static String MESS_PRIGIONE = "SEI FINITO IN PRIGIONE SCLEGLI COSA FARE: ";

	private String nome;
	private double denaro = 1;
	private int posizione = 0;
	private int tiket = 0;
	private int turniPrigione = 0;

	public Giocatore(String nome) {

		this.nome = nome;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getDenaro() {
		return denaro;
	}

	public void setDenaro(double denaro) {
		this.denaro += denaro;
	}

	public int getPosiziione() {

		return posizione;

	}

	/**
	 * METODO PER VARIARE IL VALORE DELL'ATTRIBUTO posizione; IL METODO STAMPA ANCHE
	 * A VIDEO MESSAGGI ALL'UTENTE E GESTISCE IL CASO DELLE CELLE STAZIONE;
	 * 
	 * @param mappa
	 * @param valore
	 * @param caselle_stazione
	 */
	public void muoviPedina(Mappa mappa, ArrayList<Casella> caselle_stazione) {

		posizione = ((posizione + lanciaDado()) % mappa.getPercorso().size());

		// gestione casella tiket
		if (mappa.getPercorso().get(posizione).getTipologia() == LetturaXML.CELLA_TIKET)
			tiket++;
		// condizione per gestire la pedina nelle celle di stazione
		else if (mappa.getPercorso().get(posizione).getTipologia() == LetturaXML.CELLA_STAZIONE) {

			casellaStazione(caselle_stazione);

		} else {
			// gestione celle basilari ossia PROBABILITA E IMPREVISTI
			System.out.printf("%s ORA SEI NELLA CELLA %d\n", nome, posizione);
			System.out.println(mappa.getPercorso().get(posizione).toString());
			double soldi = mappa.getPercorso().get(posizione).getAmmonto();
			setDenaro(soldi);
		}

	}

	private void casellaStazione(ArrayList<Casella> caselle_stazione) {

		int i = 0;
		// stampa delle celle stazione disponibili
		for (i = 0; i < caselle_stazione.size(); i++) {
			System.out.println(i + ")" + caselle_stazione.get(i).toString());
		}
		// scelta id cella un cui andare al prossimo turno
		int scelta = InputDati.leggiIntero(
				"\n SEI FINITO IN UNA Casella STAZIONE SCEGLI QUALE ALTRA CASELLA STAZIONE ANDARE: ", 0, i - 1);
		posizione = caselle_stazione.get(scelta).getId();

	}

	/**
	 * METODO PER ESTRARRE NUMERI CASUALI IN PIU STAMPA ANCHE A VIDEO MESSAGGI
	 * ALL'UTENTE
	 * 
	 * @param mappa
	 * @return
	 */
	public int lanciaDado() {

		System.out.printf("\n\n%s LANCIA UN DADO", nome);
		InputDati.leggiIntero("\nPER LANCIARE UN DADO INSERIRE 0 --> ");
		int numero = EstrazioniCasuali.estraiIntero(2, 11);
		System.out.printf("\nE' USCITO %d \n", numero);
		return numero;

	}

	/**
	 * controlla se il giocatore è diventato milionario
	 * 
	 * @return
	 */
	public boolean isMilionario() {

		if (denaro >= 1_000_000) {
			System.out.printf("\nGIOCATORE %s HAI VINTO!", nome);
			return true;
		}
		return false;
	}

	/**
	 * controlla se il giocatore è andato in bancarotta
	 * 
	 * @return
	 */
	public boolean inBancarotta() {

		if (denaro <= 0) {
			System.out.printf("\nGIOCATORE %s HAI PERSO", nome);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "\nGiocatore [nome=" + nome + ", denaro posseduto=" + denaro + ", posizone pedina= " + posizione + "]";
	}

}
