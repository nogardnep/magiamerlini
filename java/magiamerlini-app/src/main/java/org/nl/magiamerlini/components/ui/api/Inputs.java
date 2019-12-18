package org.nl.magiamerlini.components.ui.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.ui.tools.ButtonName;
import org.nl.magiamerlini.components.ui.tools.InputSection;
import org.nl.magiamerlini.controllers.tools.FileType;

public interface Inputs extends Component {
	public void buttonPressed(String name, InputSection section, float velocity);

	public void buttonPressed(ButtonName name, InputSection section);

	public void buttonPressed(String name, InputSection section);

	public void buttonLeaved(String name, InputSection section);

	public void selectorChanged(InputSection section, int newValue);

	public void padLeaved(int number);

	public void fileLoaded(FileType type, String path); // TODO: temporary

	public void padPressed(int number, float velocity);

	public void fileCreated(FileType type, String name, String path);

	public void switchPressed(InputSection section);

	public void switchLeaved(InputSection section);

	public void wheelChanged(InputSection section, int value);

	public void switchChanged(InputSection section, int value);
}
