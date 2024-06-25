package it.uniba.map.giocotestuale.entities;

public class Link {
	private String href;
	private boolean templated;  // This field is only present in some cases

	// Getters and setters

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isTemplated() {
		return templated;
	}

	public void setTemplated(boolean templated) {
		this.templated = templated;
	}
}
