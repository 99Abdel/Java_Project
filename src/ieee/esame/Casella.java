package ieee.esame;

public class Casella {

	private String nome;
	private int id;
	private int tipologia;
	private StringBuffer messaggio = new StringBuffer(" ");
	private double ammonto;
	
	public Casella(int id, String nome, int tipologia) {
		
		this.id = id;
		this.nome = nome;
		this.tipologia = tipologia;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipologia() {
		return tipologia;
	}

	public void setTipologia(int tipologia) {
		this.tipologia = tipologia;
	}

	public StringBuffer getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(StringBuffer messaggio) {
		this.messaggio = messaggio;
	}

	public double getAmmonto() {
		return ammonto;
	}

	public void setAmmonto(double ammonto) {
		this.ammonto = ammonto;
	}
	
	@Override
	public String toString() {
		return "Casella [nome=" + nome + ", id=" + id + ", messaggio=" + messaggio
				+ ", ammonto=" + ammonto + "]";
	}
	
}
