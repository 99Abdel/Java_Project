package ieee.esame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * CLASSE PER LA CREAZIOENE DI OGGETTI PER IL PARSING E LA SUCCESSIVA LETTURA DI
 * FILE XML
 * 
 * @author acer
 *
 */
public class LetturaXML {

	private final static String FILE_NOME = "4) C2 (12).xml";

	/**
	 * METODO PER LA CREAZIONE DEI PARSING OBJECTS
	 * 
	 * @param mappa
	 */
	public static void oggettiXml(Mappa mappa) {

		XMLInputFactory generatore = null;
		XMLStreamReader lettoreCelle = null;

		try {

			generatore = XMLInputFactory.newInstance();
			lettoreCelle = generatore.createXMLStreamReader(FILE_NOME, new FileInputStream(FILE_NOME));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}

		caricaDatiMappa(mappa, lettoreCelle);

	}

	// COSTANTI CON CUI HO NUMERATO LA TIPOLOGIA DI CELLA INVECE CHE LAVORARE CON LE
	// STRINGHE
	private final static int CELLA_INIZIALE = 0;
	private final static int CELLA_PROBABILITA = 1;
	private final static int CELLA_IMPREVISTO = 2;
	public static final int CELLA_STAZIONE = 3;
	public final static int CELLA_PRIGIONE = 4;
	public final static int CELLA_TIKET = 20;

	// COSTANTI DI APPOGGIO CHE MI SERVONO PER IDENTIFICARE IL TAG IN CUI MI TROVO
	private static final int CELL = 5;
	private static final int AMOUNT = 6;
	private static final int MESSAGE = 7;
	private static final int LINE = 8;
	private static final int MAP = 9;
	private static final int COSTS = 10;
	private static final int EARNINGS = 11;

	/**
	 * METODO PER CARICARE I DATI DA XML NELLE CASELLE CONTENUTE NELLA MAPPA
	 * 
	 * @param mappa
	 * @param lettoreDati
	 */
	public static void caricaDatiMappa(Mappa mappa, XMLStreamReader lettoreDati) {

		// VARIABILE DI APPOGGIO CHE ASSUME IL VALORE DELLE COSTANTI SOPRA DEFINITE
		int nometag = 0;
		// NUMERO DELLA CASELLA CHE CORRISPONDE ALL'ID;
		int numeroCella = 0;

		int id;
		int tipologia;
		String nome;
		boolean positivo = true;

		try {
			while (lettoreDati.hasNext()) {

				switch (lettoreDati.getEventType()) {

				case XMLStreamReader.START_ELEMENT:

					nometag = tipologiaTagApertura(lettoreDati);

					if (nometag == CELL) {

						id = numeroCella;
						nome = lettoreDati.getAttributeValue(1);

						// casistiche per la gestione della tipologia di cella che sono state numerate
						// sopra.

						if ("probabilita".equalsIgnoreCase(lettoreDati.getAttributeValue(2))) {
							tipologia = CELLA_PROBABILITA;
							positivo = true;
						} else if ("imprevisto".equalsIgnoreCase(lettoreDati.getAttributeValue(2))) {
							tipologia = CELLA_IMPREVISTO;
							positivo = false;
						} else if ("stazione".equalsIgnoreCase(lettoreDati.getAttributeValue(2)))
							tipologia = CELLA_STAZIONE;
						else if ("prigione".equalsIgnoreCase(lettoreDati.getAttributeValue(2))) {
							tipologia = CELLA_PRIGIONE;
							positivo = false;
						} else if ("tiket".equalsIgnoreCase(lettoreDati.getAttributeValue(2))) {
							tipologia = CELLA_TIKET;
						} else
							tipologia = CELLA_INIZIALE;
						//creazione della casella e la sua aggiunta nella mappa
						Casella casella = new Casella(id, nome, tipologia);
						mappa.getPercorso().add(casella);
						
					} else if (nometag == AMOUNT) {
						// ho usato la variabile positivo per differenziare da subito se
						// l'ammonto(soldi) � da pagare o da guadagnare
						double soldi = Double.parseDouble(lettoreDati.getAttributeValue(0));
						if (positivo) {
							mappa.getPercorso().get(numeroCella).setAmmonto(soldi);
						} else
							mappa.getPercorso().get(numeroCella).setAmmonto(-soldi);

					}

					else if (nometag == LINE) {

						mappa.getPercorso().get(numeroCella).getMessaggio().append(lettoreDati.getAttributeValue(0));

					} else if (nometag == MAP) {

						mappa.setNome(lettoreDati.getAttributeValue(1));

					}

					break;

				case XMLStreamReader.END_ELEMENT:

					// CONTROLLO PER PASSARE ALLA PROSSIMA CELLA DA LEGGERE

					if ("cell".equalsIgnoreCase(lettoreDati.getLocalName()))
						numeroCella++;
					break;

				case XMLStreamReader.END_DOCUMENT:

					lettoreDati.close();
					break;
				}

				lettoreDati.next();
			}

		} catch (XMLStreamException e) {

			e.printStackTrace();

		}

	}

	/**
	 * METODO PER VARIARE IL VALORE DELLA VARIABILE nomeTag DA USARE NEI SUCCESSIVI
	 * CONTROLLI IN START_ELEMENT;
	 * 
	 * @param lettoreDati
	 * @return
	 */
	private static int tipologiaTagApertura(XMLStreamReader lettoreDati) {

		if ("cell".equalsIgnoreCase(lettoreDati.getLocalName()))
			return CELL;

		else if ("amount".equalsIgnoreCase(lettoreDati.getLocalName()))
			return AMOUNT;

		else if ("message".equalsIgnoreCase(lettoreDati.getLocalName()))
			return MESSAGE;

		else if ("line".equalsIgnoreCase(lettoreDati.getLocalName()))
			return LINE;
		else if ("map".equalsIgnoreCase(lettoreDati.getLocalName()))
			return MAP;
		else if ("costs".equalsIgnoreCase(lettoreDati.getLocalName()))
			return COSTS;
		else if ("earnings".equalsIgnoreCase(lettoreDati.getLocalName()))
			return EARNINGS;

		return -1;
	}

}
