package org.nl.magiamerlini.components.ui.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.nl.magiamerlini.components.BaseComponent;
import org.nl.magiamerlini.components.audio.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.ui.api.Inputs;
import org.nl.magiamerlini.components.ui.tools.ButtonEvent;
import org.nl.magiamerlini.components.ui.tools.ButtonName;
import org.nl.magiamerlini.components.ui.tools.InputSection;
import org.nl.magiamerlini.controllers.tools.FileType;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.utils.EnumUtils;

public class BaseInputs extends BaseComponent implements Inputs {
	private final static boolean EMPTY_SELECTED_ITEMS_ON_LEAVING_EDIT_BUTTON = false;
	public List<ButtonEvent> queue;

	public BaseInputs() {
		queue = new ArrayList<ButtonEvent>();
	}

	@Override
	public void buttonPressed(String name, InputSection section) {
		buttonPressed(name, section, ButtonEvent.MAX_VELOCITY);
	}

	@Override
	public void buttonPressed(ButtonName buttonName, InputSection section) {
		String stringName = EnumUtils.getCorrespondingString(buttonName.name());

		buttonPressed(stringName, section, ButtonEvent.MAX_VELOCITY);
	}

	@Override
	public void buttonPressed(String name, InputSection section, float velocity) {
		addToQueue(new ButtonEvent(name, section, velocity));
		ButtonEvent last = getLastInQueue();
		ButtonEvent first = getFirstInQueue();

		int index;
		int number;

		if (!mainController.isLoadingProject()) {
			if (mainController.getCurrentMode() == Mode.Project && last.equals(first)
					&& last.getSection() == InputSection.Action) {
				if (last.hasName(ButtonName.Load)) {
					mainController.getFileExplorer().open(FileType.Project);
				} else if (last.hasName(ButtonName.New)) {
					mainController.getFileExplorer().create(FileType.Project);
				}
			}

			if (mainController.isReady()) {
				switch (last.getSection()) {
				case Action:
					if (last.equals(first)) {
						if (first.hasName(ButtonName.Edit) || first.hasName(ButtonName.Move)
								|| first.hasName(ButtonName.Copy)) {
							mainController.getSelectionController().setSelecting(true);
						} else {
							switch (mainController.getCurrentMode()) {
							case Song:
								break;
							case SequenceEdit:
								break;
							case PatternEdit:
								break;
							case PatternLaunch:
								break;
							case AudioSampler:
								if (last.hasName(ButtonName.Load)) {
									mainController.getFileExplorer().open(FileType.Sound);
								}
								break;
							case AudioMixer:
								break;
							case AudioEffects:
								break;
							case VideoSampler:
								break;
							case VideoMixer:
								break;
							case VideoEffects:
								break;
							default:
								break;
							}
						}
					}
					break;
				case Bank:
					number = Integer.parseInt(last.getName());
					switch (first.getSection()) {
					case Bank:
						mainController.changeBank(number);
						break;
					case Action:
						if (first.hasName(ButtonName.New)) {
							mainController.getPresetController().createBank(number);
						} else if (first.hasName(ButtonName.Load)) {
							mainController.getPresetController().loadBank(number);
						} else if (first.hasName(ButtonName.Save)) {
							mainController.getPresetController().saveBank(number);
						}
						break;
					default:
						break;
					}

					break;
				case Navigation:
					if (last.hasName(ButtonName.Up)) {
						mainController.getSelectionController().modifyEditingParameterValue(1);
					} else if (last.hasName(ButtonName.Down)) {
						mainController.getSelectionController().modifyEditingParameterValue(-1);
					} else if (last.hasName(ButtonName.Left)) {
						mainController.getSelectionController().modifyEditingParameterIndex(-1);
					} else if (last.hasName(ButtonName.Right)) {
						mainController.getSelectionController().modifyEditingParameterIndex(1);
					}
					break;
				case Padboard:
					number = Integer.parseInt(last.getName());
					switch (first.getSection()) {
					case Padboard:
						mainController.getPlayerController().playTrack(number, velocity);
						break;
					case Action:
						if (first.hasName(ButtonName.Edit) || first.hasName(ButtonName.Move)
								|| first.hasName(ButtonName.Copy)) {
							mainController.getSelectionController()
									.toggleSelected(mainController.getItemCorrespondingTo(number));
						}
						break;
					default:
						break;
					}
					break;
				case Player:
					if (last.hasName(ButtonName.Forward)) {
						mainController.getPlayerController().moveForward();
					} else if (last.hasName(ButtonName.Backward)) {
						if (first.hasName(ButtonName.Pause)) {
							mainController.getPlayerController().stop();
						} else {
							mainController.getPlayerController().moveBackward();
						}
					} else if (last.hasName(ButtonName.Play)) {
						mainController.getPlayerController().play();
					} else if (last.hasName(ButtonName.Pause)) {
						mainController.getPlayerController().pause();
					}
					break;
				case Mode:
					index = mainController.getCurrentModeIndex();
					if (last.hasName(ButtonName.Up)) {
						index++;
					} else if (last.hasName(ButtonName.Down)) {
						index--;
					}
					mainController.changeMode(index);
					break;
				case Pattern:
					index = mainController.getCurrentPatternIndex();
					if (last.hasName(ButtonName.Up)) {
						index++;
					} else if (last.hasName(ButtonName.Down)) {
						index--;
					}
					mainController.changePattern(index);
					break;
				case Track:
					index = mainController.getCurrentTrackIndex();
					if (last.hasName(ButtonName.Up)) {
						index++;
					} else if (last.hasName(ButtonName.Down)) {
						index--;
					}
					mainController.changeTrack(index);
					break;
				case Sequence:
					index = mainController.getCurrentSequenceIndex();
					if (last.hasName(ButtonName.Up)) {
						index++;
					} else if (last.hasName(ButtonName.Down)) {
						index--;
					}
					mainController.changeSequence(index);
					break;
				case Page:
					index = mainController.getCurrentPageIndex();
					if (last.hasName(ButtonName.Up)) {
						index++;
					} else if (last.hasName(ButtonName.Down)) {
						index--;
					}
					mainController.changePage(index);
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public void buttonLeaved(String name, InputSection section) {
		ButtonEvent button = new ButtonEvent(name, section);

		if (mainController.isReady()) {
			if (button.equals(getFirstInQueue())) {
				if (button.hasName(ButtonName.Edit)) {
					if (EMPTY_SELECTED_ITEMS_ON_LEAVING_EDIT_BUTTON) {
						mainController.getSelectionController().emptySelectedItems();
					}
					mainController.getSelectionController().setSelecting(false);
				} else if (button.hasName(ButtonName.Move)) {
					switch (mainController.getCurrentMode()) {
					case AudioSampler:
					case VideoSampler:
						mainController.getSelectionController().applySwapping();
						mainController.getSelectionController().emptySelectedItems();
						mainController.getSelectionController().setSelecting(false);
						break;
					default:
						break;
					}
				} else if (button.hasName(ButtonName.Copy)) {
					switch (mainController.getCurrentMode()) {
					case AudioSampler:
					case VideoSampler:
						mainController.getSelectionController().applyCopying();
						mainController.getSelectionController().emptySelectedItems();
						mainController.getSelectionController().setSelecting(false);
						break;
					default:
						break;
					}
				}
			}
		}

		// TODO: stop sound when button is a pad and playing_mode parameter is "hold"

		removeFromQueue(button);
	}

	@Override
	public void padPressed(int number, float velocity) {
		buttonPressed(String.valueOf(number), InputSection.Padboard, velocity);
	}

	@Override
	public void padLeaved(int number) {
		buttonLeaved(String.valueOf(number), InputSection.Padboard);
	}

	@Override
	public void wheelChanged(InputSection section, int value) {
		if (value < 0) {
			buttonPressed(ButtonName.Down, section);
		} else {
			buttonPressed(ButtonName.Up, section);
		}
	}

	@Override
	public void switchPressed(InputSection section) {

	}

	@Override
	public void switchLeaved(InputSection section) {

	}

	@Override
	public void fileCreated(FileType type, String name, String path) {
		// TODO: why? if (mainController.isReady()) {
		switch (type) {
		case Image:
			break;
		case Preset:
			break;
		case Project:
			mainController.createProject(name, path);
			break;
		case Sound:
			break;
		default:
			break;
		}
		// TODO: }
	}

	@Override
	public void fileLoaded(FileType type, String path) {
		switch (type) {
		case Image:
			break;
		case Preset:
			break;
		case Project:
			mainController.loadProject(path);
			break;
		case Sound:
			AudioSamplerTrack samplerTrack = mainController.getProjectManager()
					.getAudioSamplerTrack(mainController.getCurrentBankIndex(), mainController.getCurrentTrackIndex());
			mainController.getAudioSampler().loadTrackSample(samplerTrack, path);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void switchChanged(InputSection section, int value) {
		// TODO
	}

	@Override
	public void selectorChanged(InputSection section, int value) {
		switch (section) {
		case Mode:
			mainController.changeMode(value);
			break;
		default:
			if (mainController.isReady()) {
				switch (section) {
				case Sequence:
					mainController.changeSequence(value);
					break;
				case Pattern:
					mainController.changePattern(value);
					break;
				case Page:
					mainController.changePage(value);
					break;
				case Track:
					mainController.changeTrack(value);
					break;
				default:
					break;
				}
			}
			break;
		}
	}

	private void addToQueue(ButtonEvent button) {
		queue.add(button);
	}

	private void removeFromQueue(ButtonEvent event) {
		for (Iterator<ButtonEvent> iterator = queue.listIterator(); iterator.hasNext();) {
			if (iterator.next().equals(event)) {
				iterator.remove();
				break;
			}
		}
	}

	@SuppressWarnings("unused")
	private boolean isFirstInQueue(ButtonEvent event) {
		return getFirstInQueue().equals(event);
	}

	@SuppressWarnings("unused")
	private boolean isLastInQueue(ButtonEvent event) {
		return getLastInQueue() != null && getLastInQueue().equals(event);
	}

	@SuppressWarnings("unused")
	private ButtonEvent getFirstInQueue() {
		if (queue.size() > 0) {
			return queue.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unused")
	private ButtonEvent getLastInQueue() {
		if (queue.size() > 0) {
			return queue.get(queue.size() - 1);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unused")
	private ButtonEvent getBeforeLastInQueue() {
		if (queue.size() > 1) {
			return queue.get(queue.size() - 2);
		} else {
			return null;
		}
	}

}
