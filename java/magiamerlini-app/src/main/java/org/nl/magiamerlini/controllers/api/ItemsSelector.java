package org.nl.magiamerlini.controllers.api;

import java.util.List;

import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

public interface ItemsSelector {
	public void addSelectedItem(Item item);

	public void removeSelectedItem(Item item);

	public void emptySelectedItems();

	public void applyParameter(Parameter parameter);

	public List<Item> getSelectedItems();

	public boolean toggleSelected(Item item);

	public Parameter getEditingParameter();

	public int getEditingParameterIndex();

	public void setEditingParameterIndex(int selectedParameterIndex);

	public void modifyEditingParameterValue(float factor);

	public void modifyEditingParameterIndex(int factor) ;

	public boolean isSelected(Item item);

	public boolean isFirstSelected(Item item);

	public Item getFirstSelectedItem();
}
