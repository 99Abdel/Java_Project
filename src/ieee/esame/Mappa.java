package ieee.esame;

import java.util.ArrayList;

/**
 * classe che contiene un array list di caselle, la mappa è il percorso del gioco.
 * le caselle sono ordinate in base al loro id (odine di primo caricamento vedi xml);
 * @author acer
 *
 */
public class Mappa {

	private ArrayList<Casella> percorso = new ArrayList<Casella>();
	
	private String nome;
	
	public ArrayList<Casella> getPercorso() {
		return percorso;
	}

	public void setNome(String nome) {
		
		this.nome = nome;
	}
	
	public String getNome() {
		
		return nome;
	}
	
}
