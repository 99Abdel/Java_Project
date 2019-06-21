package ieee.esame;

import java.util.ArrayList;

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
	
	public void caricaCelle() {
		
	}
}
