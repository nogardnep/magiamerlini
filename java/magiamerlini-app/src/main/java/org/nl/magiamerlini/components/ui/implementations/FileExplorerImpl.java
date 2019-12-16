package org.nl.magiamerlini.components.ui.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.ui.api.FileExplorer;

public class FileExplorerImpl extends CommunicatingComponent implements FileExplorer {
	private final static String COMPONENT_NAME = "file_explorer";

	public FileExplorerImpl(Communication communication) {
		super(communication);
	}

	@Override
	public void open(String rootPath) {
		communication.sendMessage(COMPONENT_NAME + " open " + rootPath);
	}

	@Override
	public void create() {
		communication.sendMessage(COMPONENT_NAME + " new");
	}

}
