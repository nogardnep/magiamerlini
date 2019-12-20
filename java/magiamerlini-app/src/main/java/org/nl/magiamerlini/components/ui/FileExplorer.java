package org.nl.magiamerlini.components.ui;

import org.nl.magiamerlini.App;
import org.nl.magiamerlini.communication.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.controllers.tools.FileType;
import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.utils.EnumUtils;

public class FileExplorer extends CommunicatingComponent {
	public final static String OPEN = "open";
	public final static String NEW = "new";

	private Item waitingItem;

	public FileExplorer(Communication communication) {
		super(communication, "file_explorer");
	}

	public void open(FileType type) {
		open(type, null);
	}

	public void open(FileType type, Item item) {
		String rootPath = App.ROOT_DIR_FOR_FILES + "/" + type.getPath();

		waitingItem = item;
		sendMessage(OPEN + " " + EnumUtils.getCorrespondingString(type.name()) + " " + rootPath);
	}


public void create(FileType type) {
		sendMessage(NEW + " " + EnumUtils.getCorrespondingString(type.name()));
	}

	public Item getWaitingItem() {
		return waitingItem;
	}

}
