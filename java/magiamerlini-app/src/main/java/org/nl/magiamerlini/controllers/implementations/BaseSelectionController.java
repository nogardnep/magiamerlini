package org.nl.magiamerlini.controllers.implementations;

import java.util.ArrayList;
import java.util.List;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.controllers.api.SelectionController;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.tools.BaseController;
import org.nl.magiamerlini.data.items.MixerTrack;
import org.nl.magiamerlini.data.items.SamplerTrack;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

public class BaseSelectionController extends BaseController implements SelectionController {
	MainController mainController;
	List<Item> selectedItems;
	int editingParameterIndex;
	boolean selecting;

	public BaseSelectionController(MainController mainController) {
		super(mainController);
		
		selectedItems = new ArrayList<Item>();
		this.mainController = mainController;
		editingParameterIndex = 0;
		selecting = false;
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
				mainController.getAudioSampler().editTrackParameter((SamplerTrack) item, parameter);
			} else if (item instanceof MixerTrack) {
				mainController.getAudioMixer().editTrackParameter((MixerTrack) item, parameter);
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

	@Override
	public boolean isSelecting() {
		return selecting;
	}

	@Override
	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
	}

	private void updateDisplay() {
		mainController.getDisplay().displaySelectedItem();
		mainController.getDisplay().displaySelectedParameter();
	}

	@Override
	public void applySwapping() {
		// TODO Auto-generated method stub
	}

	@Override
	public void applyCopying() {
		// TODO Auto-generated method stub	
	}

	private void addSelectedItem(Item item) {
		selectedItems.add(item);
		updateDisplay();
	}

	private void removeSelectedItem(Item item) {
		selectedItems.removeIf(editingItem -> (editingItem == item));

		if (selectedItems.isEmpty()) {
			selectDefaultSelectedItem();
		}

		updateDisplay();
	}

}
