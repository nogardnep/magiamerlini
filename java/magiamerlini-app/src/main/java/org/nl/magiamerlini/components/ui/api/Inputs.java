package org.nl.magiamerlini.components.ui.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.ui.tools.ButtonName;
import org.nl.magiamerlini.components.ui.tools.ButtonSection;
import org.nl.magiamerlini.components.ui.tools.SelectorType;
import org.nl.magiamerlini.components.ui.tools.SwitchType;
import org.nl.magiamerlini.controllers.tools.FileType;

public interface Inputs extends Component {
	public void buttonPressed(String name, ButtonSection section, float velocity);

	public void buttonPressed(String name, ButtonSection section);

	public void buttonLeaved(String name, ButtonSection section);

	public void selectorChanged(SelectorType selector, int newValue);

	public void padLeaved(int number);

	public void fileLoaded(FileType type, String path); // TODO: temporary

	void padPressed(int number, float velocity);

	public void fileCreated(FileType type, String name, String path);

	void switchPressed(SwitchType oneSwitch);

	void switchLeaved(SwitchType oneSwitch);
}
