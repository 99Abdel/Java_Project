package ieee.esame;

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
	
	public void muoviPedina(Mappa mappa, int valore) {
	
		posizione += valore%mappa.getPercorso().size();
		System.out.printf("%s ORA SEI NELLA CELLA %d",nome, posizione);
		System.out.println(mappa.getPercorso().get(posizione).toString());
	}

	
	public int lanciaDado(Mappa mappa) {

		System.out.println("\n\n%s LANCIA UN DADO ");
		InputDati.leggiIntero("\nPER LANCIARE UN DADO INSERIRE 0 --> ");
		int numero =EstrazioniCasuali.estraiIntero(2, 11); 
		System.out.printf("E' USCITO %d", numero);
		return numero;

	}
	
	public boolean isMilionario() {
		
		if(denaro >= 1_000_000) {
			System.out.printf("GIOCATORE %s HAI VINTO!", nome);
			return true;
		}
		return false;
	}
	
	public boolean inBancarotta() {
		
		if(denaro <= 0) {
			System.out.printf("GIOCATORE %s HAI PERSO", nome);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Giocatore [nome=" + nome + ", denaro possedudo=" + denaro + ", posizone pedina= " + posizione +"]";
	}

}
