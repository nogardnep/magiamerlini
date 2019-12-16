package org.nl.magiamerlini.controllers.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Mixer;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.sampler.api.Sampler;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.controllers.api.ItemsSelector;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.entities.MixerTrack;
import org.nl.magiamerlini.data.entities.SamplerTrack;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

public class ItemsSelectorImpl implements ItemsSelector {
	MainController mainController;
	List<Item> selectedItems;
	int editingParameterIndex;

	public ItemsSelectorImpl(MainController mainController) {
		selectedItems = new ArrayList<Item>();
		this.mainController = mainController;
		editingParameterIndex = 0;
	}

	@Override
	public void addSelectedItem(Item item) {
		selectedItems.add(item);
		updateDisplay();
	}

	@Override
	public void removeSelectedItem(Item item) {
		selectedItems.removeIf(editingItem -> (editingItem == item));

		if (selectedItems.isEmpty()) {
			selectDefaultSelectedItem();
		}

		updateDisplay();
	}

	@Override
	public void emptySelectedItems() {
		selectedItems.clear();
		selectDefaultSelectedItem();

		updateDisplay();
	}

	private void selectDefaultSelectedItem() {
		// TODO
		updateDisplay();
	}

	@Override
	public void applyParameter(Parameter parameter) {
		for (Item item : selectedItems) {
			item.applyParameter(parameter);
			mainController.getProjectManager().updateEntity(item);

			if (item instanceof SamplerTrack) {
				mainController.getSampler().editTrackParameter((SamplerTrack) item, parameter);
			} else if (item instanceof MixerTrack) {
				mainController.getMixer().editTrackParameter((MixerTrack) item, parameter);
			}
		}

		updateDisplay();
	}

	@Override
	public List<Item> getSelectedItems() {
		return selectedItems;
	}

	@Override
	public boolean toggleSelected(Item item) {
		boolean selected;

		if (selectedItems.contains(item)) {
			removeSelectedItem(item);
			selected = false;
		} else {
			addSelectedItem(item);
			selected = true;
		}

		return selected;
	}

	@Override
	public Parameter getEditingParameter() {
		Parameter parameter = null;

		if (selectedItems.size() > 0) {
			Item firstSelectedItem = selectedItems.get(0);

			if (editingParameterIndex < firstSelectedItem.getParameters().size()) {
				parameter = firstSelectedItem.getParameters().get(editingParameterIndex);
			}
		} else {
			// TODO: get default editing item
		}

		return parameter;
	}

	@Override
	public int getEditingParameterIndex() {
		return editingParameterIndex;
	}

	@Override
	public void setEditingParameterIndex(int newIndex) {
		if (newIndex < 0) {
			newIndex = Configuration.Parameters.getValue() - 1;
		} else if (newIndex > (Configuration.Parameters.getValue() - 1)) {
			newIndex = 0;
		}

		editingParameterIndex = newIndex;
		updateDisplay();
	}

	@Override
	public void modifyEditingParameterValue(float factor) {
		Parameter parameter = getEditingParameter();

		if (parameter != null) {
			parameter.modifyValue(factor);
			applyParameter(parameter);
		}
	}

	@Override
	public void modifyEditingParameterIndex(int factor) {
		setEditingParameterIndex(editingParameterIndex + factor);
	}

	@Override
	public boolean isSelected(Item item) {
		return selectedItems.contains(item);
	}

	@Override
	public boolean isFirstSelected(Item item) {
		boolean firstSelected = false;

		if (selectedItems.size() > 0) {
			firstSelected = (selectedItems.get(0) == item);
		}

		return firstSelected;
	}

	@Override
	public Item getFirstSelectedItem() {
		Item found = null;

		if (selectedItems.size() > 0) {
			found = selectedItems.get(0);
		}

		return found;
	}

	private void updateDisplay() {
		mainController.getDisplay().displaySelectedItem();
		mainController.getDisplay().displaySelectedParameter();
	}

}
