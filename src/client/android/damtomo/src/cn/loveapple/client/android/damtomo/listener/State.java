package cn.loveapple.client.android.damtomo.listener;

import org.simpleframework.xml.Element;

public class State {
	@Element
	private String name;

	@Element
	private String abbreviation;

	public State() {
	}

	public State(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getFormattedName() {
		return this.getName() + " (" + this.getAbbreviation() + ")";
	}
}
