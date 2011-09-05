package cn.loveapple.client.android.damtomo.listener;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class StateList {
	@ElementList(inline = true)
	private List<State> states;

	public StateList() {
	}

	public StateList(List<State> states) {
		this.states = states;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
}
