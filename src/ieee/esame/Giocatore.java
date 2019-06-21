package ieee.esame;

import java.util.ArrayList;

import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.mylib.InputDati;

public class Giocatore {

	private String nome;
	private double denaro = 10000;
	private int posizione = 0;

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

	public void muoviPedina(Mappa mappa, int valore, ArrayList<Casella> caselle_stazione) {

		posizione = ((posizione + valore) % mappa.getPercorso().size());

		if (mappa.getPercorso().get(posizione).getTipologia() == LetturaXML.CELLA_STAZIONE) {
			
			int i = 0;
			for (i = 0; i < caselle_stazione.size(); i++) {
				System.out.println(i + ")" + caselle_stazione.get(i).toString());
			}
			int scelta = InputDati.leggiIntero("\n SEI FINITO IN UNA Casella STAZIONE SCEGLI QUALE ALTRA CASELLA STAZIONE ANDARE: ", 0, i-1);
			posizione = caselle_stazione.get(scelta).getId();
			
		} else {
			System.out.printf("%s ORA SEI NELLA CELLA %d\n", nome, posizione);
			System.out.println(mappa.getPercorso().get(posizione).toString());
			double soldi = mappa.getPercorso().get(posizione).getAmmonto();
			setDenaro(soldi);
		}
	}

	public int lanciaDado(Mappa mappa) {

		System.out.printf("\n\n%s LANCIA UN DADO", nome);
		InputDati.leggiIntero("\nPER LANCIARE UN DADO INSERIRE 0 --> ");
		int numero = EstrazioniCasuali.estraiIntero(2, 11);
		System.out.printf("\nE' USCITO %d \n", numero);
		return numero;

	}

	public boolean isMilionario() {

		if (denaro >= 1_000_000) {
			System.out.printf("\nGIOCATORE %s HAI VINTO!", nome);
			return true;
		}
		return false;
	}

	public boolean inBancarotta() {

		if (denaro <= 0) {
			System.out.printf("\nGIOCATORE %s HAI PERSO", nome);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "\nGiocatore [nome=" + nome + ", denaro possedudo=" + denaro + ", posizone pedina= " + posizione + "]";
	}

}
