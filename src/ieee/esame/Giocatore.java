package ieee.esame;

import it.unibs.fp.mylib.EstrazioniCasuali;

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
	
	public void muoviPedina() {
		
		posizione += lanciaDado();
		
	}

	
	public int lanciaDado() {

		return EstrazioniCasuali.estraiIntero(2, 11);

	}
	
	public boolean isMilionario() {
		
		if(denaro >= 1_000_000)
			return true;
		
		return false;
	}
	
	public boolean inBancarotta() {
		
		if(denaro <= 0)
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Giocatore [nome=" + nome + ", denaro possedudo=" + denaro + ", posizone pedina= " + posizione +"]";
	}

}
