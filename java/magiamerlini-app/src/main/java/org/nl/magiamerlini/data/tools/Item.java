package org.nl.magiamerlini.data.tools;

import java.util.List;
import java.util.Map;

public interface Item {
	public List<Parameter> getParameters();
	public void applyParameter(Parameter parameter);
	public String toDisplay();
}
