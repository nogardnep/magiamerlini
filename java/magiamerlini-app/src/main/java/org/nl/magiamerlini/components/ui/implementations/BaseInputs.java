package org.nl.magiamerlini.components.ui.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.nl.magiamerlini.components.BaseComponent;
import org.nl.magiamerlini.components.mixer.items.AudioEffect;
import org.nl.magiamerlini.components.mixer.items.AudioMixerTrack;
import org.nl.magiamerlini.components.mixer.items.Effect;
import org.nl.magiamerlini.components.mixer.items.VideoEffect;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.SamplerTrack;
import org.nl.magiamerlini.components.sampler.items.VideoSamplerTrack;
import org.nl.magiamerlini.components.ui.api.Inputs;
import org.nl.magiamerlini.components.ui.tools.ButtonEvent;
import org.nl.magiamerlini.components.ui.tools.ButtonName;
import org.nl.magiamerlini.components.ui.tools.InputSection;
import org.nl.magiamerlini.controllers.tools.FileType;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.utils.EnumUtils;

public class BaseInputs extends BaseComponent implements Inputs {
	private final static boolean EMPTY_SELECTED_ITEMS_ON_LEAVING_EDIT_BUTTON = false;
	public List<ButtonEvent> queue;

	public BaseInputs() {
		queue = new ArrayList<ButtonEvent>();
	}

	@Override
	public void buttonPressed(InputSection section, String name) {
		buttonPressed(section, name, ButtonEvent.MAX_VELOCITY);
	}

	@Override
	public void buttonPressed(InputSection section, ButtonName buttonName) {
		String stringName = EnumUtils.getCorrespondingString(buttonName.name());

		buttonPressed(section, stringName, ButtonEvent.MAX_VELOCITY);
	}

	@Override
	public void buttonPressed(InputSection section, String name, float velocity) {
		logger.log(Level.INFO, "buttonPressed -----------");
		addToQueue(new ButtonEvent(name, section, velocity));
		ButtonEvent last = getLastInQueue();
		ButtonEvent first = getFirstInQueue();

		int index;
		int number;
		Item item;

		if (!mainController.isLoadingProject()) {
			if (mainController.getCurrentMode() == Mode.Project && last.equals(first)
					&& last.getSection() == InputSection.Action) {
				if (last.hasName(ButtonName.Load)) {
					mainController.getFileExplorer().open(FileType.Project);
				} else if (last.hasName(ButtonName.New)) {
					mainController.getFileExplorer().create(FileType.Project);
				}
			}

			logger.log(Level.INFO, "first=" + first);
			logger.log(Level.INFO, "last=" + last);

			if (mainController.isReady()) {
				switch (last.getSection()) {
				case Action:
					if (last.equals(first)) {
						if (isASelectingButton(last)) {
							logger.log(Level.ALL, "START SELECTING *****************************");
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
					item = mainController.getItemCorrespondingTo(number);
					switch (first.getSection()) {
					case Padboard:
						switch (mainController.getCurrentMode()) {
						case AudioSampler:
						case VideoSampler:
							mainController.getSequencer().playTrack(((SamplerTrack) item).getBank(),
									((SamplerTrack) item).getNumber(), velocity);
							break;
						default:
							break;
						}
						break;
					case Action:
						if (mainController.getSelectionController().isSelecting()) {
							if (item != null) {
								logger.log(Level.ALL, "*** select ***");
								mainController.getSelectionController().toggleSelected(item);
							}
						} else if (first.hasName(ButtonName.Special) && item instanceof AudioSamplerTrack) {
							mainController.getAudioSampler().setTrackArmed((AudioSamplerTrack) item,
									!((AudioSamplerTrack) item).isArmed());
						} else if (first.hasName(ButtonName.Special) && item instanceof AudioMixerTrack) {
							mainController.getAudioMixer().setTrackMuted((AudioMixerTrack) item,
									!((AudioMixerTrack) item).isMuted());
						} else if (first.hasName(ButtonName.Special) && item instanceof AudioEffect) {
							mainController.getAudioMixer().setEffectActivated((Effect) item,
									!((Effect) item).isActivated());
						} else if (first.hasName(ButtonName.Special) && item instanceof VideoEffect) {
							mainController.getVideoMixer().setEffectActivated((Effect) item,
									!((Effect) item).isActivated());
						} else if (first.hasName(ButtonName.Load) && item instanceof AudioSamplerTrack) {
							mainController.getFileExplorer().open(FileType.Sound, item);
						} else if (first.hasName(ButtonName.Load) && item instanceof VideoSamplerTrack) {
							mainController.getFileExplorer().open(FileType.Image, item);
						}
						break;
					default:
						break;
					}
					break;
				case Player:
					if (last.hasName(ButtonName.Forward)) {
						mainController.getSequencer().moveForward();
					} else if (last.hasName(ButtonName.Backward)) {
						if (first.hasName(ButtonName.Pause)) {
							mainController.getSequencer().stop();
						} else {
							mainController.getSequencer().moveBackward();
						}
					} else if (last.hasName(ButtonName.Play)) {
						mainController.getSequencer().play();
					} else if (last.hasName(ButtonName.Pause)) {
						mainController.getSequencer().pause();
					}
					break;
				default:
					break;
				}
			}

			switch (last.getSection()) {
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

	@Override
	public void buttonLeaved(InputSection section, ButtonName buttonName) {
		String stringName = EnumUtils.getCorrespondingString(buttonName.name());
		buttonLeaved(section, stringName);
	}

	@Override
	public void buttonLeaved(InputSection section, String name) {
		logger.log(Level.INFO, "buttonLeaved -----------");
		ButtonEvent button = new ButtonEvent(name, section);

		if (mainController.isReady()) {
			if (button.equals(getFirstInQueue())) {
				if (isASelectingButton(button)) {
					logger.log(Level.ALL, "STOP SELECTING *****************************");
					mainController.getSelectionController().setSelecting(false);
				}

				if (button.hasName(ButtonName.Edit)) {
					if (EMPTY_SELECTED_ITEMS_ON_LEAVING_EDIT_BUTTON) {
						mainController.getSelectionController().emptySelectedItems();
					}
				} else if (button.hasName(ButtonName.Move)) {
					switch (mainController.getCurrentMode()) {
					case AudioSampler:
					case VideoSampler:
						mainController.getSelectionController().applySwapping();
						mainController.getSelectionController().emptySelectedItems();
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
						break;
					default:
						break;
					}
				} else if (button.hasName(ButtonName.Load)) {

				}
			}
		}

		// TODO: stop sound when button is a pad and playing_mode parameter is "hold"

		removeFromQueue(button);
	}

	@Override
	public void padPressed(int number, float velocity) {
		buttonPressed(InputSection.Padboard, String.valueOf(number), velocity);
	}

	@Override
	public void padLeaved(int number) {
		buttonLeaved(InputSection.Padboard, String.valueOf(number));
	}

	@Override
	public void wheelChanged(InputSection section, int value) {
		if (value < 0) {
			buttonPressed(section, ButtonName.Down);
		} else {
			buttonPressed(section, ButtonName.Up);
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
		Item item;
		logger.log(Level.FINE, "fileLoaded " + path);

		switch (type) {
		case Image:
			break;
		case Preset:
			break;
		case Project:
			if (path != null) {
				mainController.loadProject(path);
			} else {
				logger.log(Level.SEVERE, "Can't load project (path is null)");
			}
			break;
		case Sound:
			item = mainController.getFileExplorer().getWaitingItem();
			if (item instanceof AudioSamplerTrack) {
				mainController.getAudioSampler().loadTrackSample((AudioSamplerTrack) item, path);
			}
			break;
		default:
			break;
		}
	}

	// TODO: Move
	@Override
	public void clockTicked() {
		mainController.getSequencer().clockTicked();
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

	@Override
	public void networkConnected() {
		mainController.updateDisplay();
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

	private boolean isASelectingButton(ButtonEvent button) {
		return button.hasName(ButtonName.Edit) || button.hasName(ButtonName.Edit) || button.hasName(ButtonName.Edit);
	}

}
