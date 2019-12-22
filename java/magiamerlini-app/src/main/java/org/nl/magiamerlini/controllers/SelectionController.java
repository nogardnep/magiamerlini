package org.nl.magiamerlini.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.mixer.items.AudioEffect;
import org.nl.magiamerlini.components.mixer.items.AudioMixerTrack;
import org.nl.magiamerlini.components.mixer.items.VideoEffect;
import org.nl.magiamerlini.components.mixer.items.VideoMixerTrack;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.VideoSamplerTrack;
import org.nl.magiamerlini.controllers.tools.BaseController;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public class SelectionController extends BaseController {
	MainController mainController;
	List<Item> selectedItems;
	int editingParameterIndex;
	boolean selecting;

	public SelectionController(MainController mainController) {
		super(mainController);

		selectedItems = new ArrayList<Item>();
		this.mainController = mainController;
		editingParameterIndex = 0;
		selecting = false;
	}

	public void emptySelectedItems() {
		selectedItems.clear();
		selectDefaultSelectedItem();

		updateDisplay();
	}

	private void selectDefaultSelectedItem() {
		// TODO
		updateDisplay();
	}

	public void applyParameter(ParameterSnapshot parameter) {
		for (Item item : selectedItems) {
			item.applyParameter(parameter);
			mainController.getProjectManager().update(item);

			if (item instanceof AudioSamplerTrack) {
				mainController.getAudioSampler().trackParameterEdited((AudioSamplerTrack) item, parameter);
			} else if (item instanceof AudioMixerTrack) {
				mainController.getAudioMixer().trackParameterEdited((AudioMixerTrack) item, parameter);
			} else if (item instanceof AudioEffect) {mainController.getAudioMixer().effectParameterEdited((AudioEffect) item, parameter);
			} else if (item instanceof VideoSamplerTrack) {
				mainController.getVideoSampler().trackParameterEdited((VideoSamplerTrack) item, parameter);
			} else if (item instanceof VideoMixerTrack) {
				mainController.getVideoMixer().trackParameterEdited((VideoMixerTrack) item, parameter);
			} else if (item instanceof VideoEffect) {
				mainController.getVideoMixer().effectParameterEdited((VideoEffect) item, parameter);
			}
		}

		updateDisplay();
	}

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
		
		logger.log(Level.ALL, selectedItems);

		updateDisplay();

		return selected;
	}

	public void setEditingParameterIndex(int newIndex) {
		if (newIndex < 0) {
			newIndex = Configuration.Parameters.getValue() - 1;
		} else if (newIndex > (Configuration.Parameters.getValue() - 1)) {
			newIndex = 0;
		}

		editingParameterIndex = newIndex;
		updateDisplay();
	}

	public void modifyEditingParameterValue(float factor) {
		ParameterSnapshot parameter = getEditingParameter();

		if (parameter != null) {
			parameter.modifyValue(factor);
			applyParameter(parameter);
		}

		updateDisplay();
	}

	public void modifyEditingParameterIndex(int factor) {
		setEditingParameterIndex(editingParameterIndex + factor);
		updateDisplay();
	}

	public void setSelecting(boolean selecting) {
		//emptySelectedItems(); // TODO: remove
		this.selecting = selecting;
		updateDisplay();
	}

	public void applySwapping() {
		// TODO: swapping for more than 2 items
		// TODO: swap toggether video and audio

		if (selectedItems.size() > 1) {
			Item first = selectedItems.get(0);
			Item second = selectedItems.get(1);

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

			mainController.getProjectManager().update(first);
			mainController.getProjectManager().update(second);

			updateDisplay();
		}
	}

	public void applyCopying() {
		// TODO
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

	public boolean isSelected(Item item) {
		return selectedItems.contains(item);
	}

	public int getEditingParameterIndex() {
		return editingParameterIndex;
	}

	public boolean isFirstSelected(Item item) {
		boolean firstSelected = false;

		if (selectedItems.size() > 0) {
			firstSelected = (selectedItems.get(0) == item);
		}

		return firstSelected;
	}

	public Item getFirstSelectedItem() {
		Item found = null;

		if (selectedItems.size() > 0) {
			found = selectedItems.get(0);
		}

		return found;
	}

	public List<Item> getSelectedItems() {
		return selectedItems;
	}

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

	public boolean isSelecting() {
		return selecting;
	}

	private void updateDisplay() {
		mainController.getDisplay().displaySelectedItem();
		mainController.getDisplay().displaySelectedParameter();
		mainController.getPadboard().updateDisplay();
	}

}
