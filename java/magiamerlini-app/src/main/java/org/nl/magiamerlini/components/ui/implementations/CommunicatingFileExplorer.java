package org.nl.magiamerlini.components.ui.implementations;

import org.nl.magiamerlini.App;
import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.controllers.tools.FileType;

public class CommunicatingFileExplorer extends CommunicatingComponent implements FileExplorer {
	private final static String COMPONENT_NAME = "file_explorer";

	public CommunicatingFileExplorer(Communication communication) {
		super(communication);
	}

	@Override
	public void open(FileType type) {
		String rootPath = App.ROOT_DIR_FOR_FILES + "/" + type.getPath();

		communication.sendMessage(COMPONENT_NAME + " open " + type + " " + rootPath);
	}

	@Override
	public void create(FileType type) {
		communication.sendMessage(COMPONENT_NAME + " new " + type);
	}

}
