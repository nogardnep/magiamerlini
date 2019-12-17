package org.nl.magiamerlini.components.ui.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.controllers.tools.FileType;

public interface FileExplorer extends Component {
	public void open(FileType type);

	public void create(FileType type);
}
