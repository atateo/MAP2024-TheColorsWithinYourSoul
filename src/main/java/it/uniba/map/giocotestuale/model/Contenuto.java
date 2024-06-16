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
	private String idContenuto;

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
	public String getIdContenuto() {
		return idContenuto;
	}
	public void setIdContenuto(String idContenuto) {
		this.idContenuto = idContenuto;
	}

	@Override
    public String toString() {
        return "Fascicolo{" +
            "id=" + getId() +
            ", label=" + getLabel() +
            ", messaggio='" + getMessaggio() + "'" +
            ", isRisposta='" + isRisposta() + "'" +
            ", idContenuto='" + getIdContenuto() + "'" +
            "}";
    }
}
