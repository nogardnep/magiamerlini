package org.nl.magiamerlini.components.ui.api;

import org.nl.magiamerlini.components.Component;

public interface FileExplorer extends Component {
	public void open(String rootPath);

	public void create();
}
