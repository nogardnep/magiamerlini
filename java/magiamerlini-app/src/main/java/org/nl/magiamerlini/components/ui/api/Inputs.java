package org.nl.magiamerlini.components.ui.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.ui.tools.Button;
import org.nl.magiamerlini.components.ui.tools.Selector;
import org.nl.magiamerlini.components.ui.tools.Switch;

public interface Inputs extends Component {
	// TODO: consider long button pressing

	public void buttonPressed(Button button);

	public void buttonLeaved(Button button);

	public void selectorChanged(Selector selector, int newValue);

	public void padLeaved(int padNum);

	public void fileLoaded(String path); // TODO: temporary

	void padPressed(int padNum, float velocity);

	public void fileCreated(String name, String path);

	void switchPressed(Switch oneSwitch);

	void switchLeaved(Switch oneSwitch);

	public void bankButtonPressed(int number);

	public void bankButtonLeaved(int number);
}
