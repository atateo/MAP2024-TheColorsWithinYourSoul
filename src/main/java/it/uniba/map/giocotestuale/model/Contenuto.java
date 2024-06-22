/**
 * 
 */
package it.uniba.map.giocotestuale.model;

/**
 * 
 */
public class Contenuto {
	private int id;
	private String label;
	private String messaggio;
	private boolean isRisposta;
	private int idItem;

	public Contenuto() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public boolean isRisposta() {
		return isRisposta;
	}
	public void setRisposta(boolean isRisposta) {
		this.isRisposta = isRisposta;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	@Override
    public String toString() {
        return "Contenuto{" +
            "id=" + getId() +
            ", label=" + getLabel() +
            ", messaggio='" + getMessaggio() + "'" +
            ", isRisposta='" + isRisposta() + "'" +
            ", idTest='" + getIdItem() + "'" +
            "}";
    }
}
