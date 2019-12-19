package org.nl.magiamerlini.components.ui.implementations;

import org.nl.magiamerlini.App;
import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.controllers.tools.FileType;
import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.utils.EnumUtils;

public class CommunicatingFileExplorer extends CommunicatingComponent implements FileExplorer {
	public final static String OPEN = "open";
	public final static String NEW = "new";

	private Item waitingItem;

	public CommunicatingFileExplorer(Communication communication) {
		super(communication, "file_explorer");
	}

	@Override
	public void open(FileType type) {
		open(type, null);
	}

	@Override
	public void open(FileType type, Item item) {
		String rootPath = App.ROOT_DIR_FOR_FILES + "/" + type.getPath();

		waitingItem = item;
		sendMessage(OPEN + " " + EnumUtils.getCorrespondingString(type.name()) + " " + rootPath);
	}

	@Override
	public void create(FileType type) {
		sendMessage(NEW + " " + EnumUtils.getCorrespondingString(type.name()));
	}

	@Override
	public Item getWaitingItem() {
		return waitingItem;
	}

}
