package org.nl.magiamerlini.controllers.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.mixer.items.AudioMixerTrack;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.api.SelectionController;
import org.nl.magiamerlini.controllers.tools.BaseController;
import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

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
	public void applyParameter(ParameterSnapshot parameter) {
		for (Item item : selectedItems) {
			item.applyParameter(parameter);
			mainController.getProjectManager().updateEntity(item);

			if (item instanceof AudioSamplerTrack) {
				mainController.getAudioSampler().editTrackParameter((AudioSamplerTrack) item, parameter);
			} else if (item instanceof AudioMixerTrack) {
				mainController.getAudioMixer().editTrackParameter((AudioMixerTrack) item, parameter);
			}
		}

		updateDisplay();
	}

	@Override
	public boolean toggleSelected(Item item) {
		boolean selected;

		if (selectedItems.contains(item)) {
			removeSelectedItem(item);
			selected = false;
		} else {
			if (selectedItems.size() > 0 && item.getClass() != selectedItems.get(0).getClass()) {
				emptySelectedItems();
			}

			addSelectedItem(item);
			selected = true;
		}

		updateDisplay();

		return selected;
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
		ParameterSnapshot parameter = getEditingParameter();

		if (parameter != null) {
			parameter.modifyValue(factor);
			applyParameter(parameter);
		}

		updateDisplay();
	}

	@Override
	public void modifyEditingParameterIndex(int factor) {
		setEditingParameterIndex(editingParameterIndex + factor);
		updateDisplay();
	}

	@Override
	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
		updateDisplay();
	}

	@Override
	public void applySwapping() {
		// TODO: swapping for more than 2 items

		if (selectedItems.size() > 1) {
			Item first = selectedItems.get(0);
			Item second = selectedItems.get(1);
			logger.log(Level.FINE, "SWAP " + first);
			logger.log(Level.FINE, "SWAP " + second);

			if (first instanceof AudioSamplerTrack) {
				int firstBank = ((AudioSamplerTrack) first).getBank();
				int firstNumber = ((AudioSamplerTrack) first).getNumber();
				int secondBank = ((AudioSamplerTrack) second).getBank();
				int secondNumber = ((AudioSamplerTrack) second).getNumber();
				((AudioSamplerTrack) first).setBank(secondBank);
				((AudioSamplerTrack) first).setNumber(secondNumber);
				((AudioSamplerTrack) second).setBank(firstBank);
				((AudioSamplerTrack) second).setNumber(firstNumber);
			}

			mainController.getProjectManager().updateEntity(first);
			mainController.getProjectManager().updateEntity(second);

			updateDisplay();
		}
	}

	@Override
	public void applyCopying() {
		// TODO Auto-generated method stub
		updateDisplay();
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

	@Override
	public boolean isSelected(Item item) {
		return selectedItems.contains(item);
	}

	@Override
	public int getEditingParameterIndex() {
		return editingParameterIndex;
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
	public List<Item> getSelectedItems() {
		return selectedItems;
	}

	@Override
	public ParameterSnapshot getEditingParameter() {
		ParameterSnapshot parameter = null;

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
	public boolean isSelecting() {
		return selecting;
	}

	private void updateDisplay() {
		mainController.getDisplay().displaySelectedItem();
		mainController.getDisplay().displaySelectedParameter();
		mainController.getPadboard().updateDisplay();
	}

}
