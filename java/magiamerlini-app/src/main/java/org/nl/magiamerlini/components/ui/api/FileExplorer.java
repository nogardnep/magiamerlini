package org.nl.magiamerlini.components.ui.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.controllers.tools.FileType;
import org.nl.magiamerlini.data.items.Item;

public interface FileExplorer extends Component {
	public void open(FileType type);

	public void open(FileType type, Item item);

	public void create(FileType type);

	public Item getWaitingItem();
}
