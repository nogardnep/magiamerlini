package org.nl.magiamerlini.controllers.api;

import java.util.List;

import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public interface SelectionController {
	public void emptySelectedItems();

	public void applyParameter(ParameterSnapshot parameter);

	public List<Item> getSelectedItems();

	public boolean toggleSelected(Item item);

	public ParameterSnapshot getEditingParameter();

	public int getEditingParameterIndex();

	public void setEditingParameterIndex(int selectedParameterIndex);

	public void modifyEditingParameterValue(float factor);

	public void modifyEditingParameterIndex(int factor);

	public boolean isSelected(Item item);

	public boolean isFirstSelected(Item item);

	public Item getFirstSelectedItem();

	public boolean isSelecting();

	public void setSelecting(boolean selecting);

	public void applySwapping();

	public void applyCopying();
}
