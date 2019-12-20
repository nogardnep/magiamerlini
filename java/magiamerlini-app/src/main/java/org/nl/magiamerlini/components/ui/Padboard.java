package org.nl.magiamerlini.components.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.communication.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.mixer.items.AudioMixerTrack;
import org.nl.magiamerlini.components.mixer.items.Effect;
import org.nl.magiamerlini.components.mixer.items.MixerTrack;
import org.nl.magiamerlini.components.mixer.items.VideoMixerTrack;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.SamplerTrack;
import org.nl.magiamerlini.components.sequencer.items.PatternEvent;
import org.nl.magiamerlini.components.sequencer.items.SequenceEvent;
import org.nl.magiamerlini.components.ui.tools.Color;
import org.nl.magiamerlini.components.ui.tools.Pad;
import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.utils.EnumUtils;

public class Padboard extends CommunicatingComponent  {
	private List<Pad> pads;

	public Padboard(Communication communication) {
		super(communication, "pad");
		pads = new ArrayList<Pad>();

		for (int i = 0; i < Configuration.Pads.getValue(); i++) {
			pads.add(new Pad(i));
			pads.get(i).setLightColor(Color.Red);
		}
	}

	public void updateDisplay() {
		for (Pad pad : pads) {
			pad.setLightColor(Color.Blue);
			updateDisplay(pad);
		}
	}

	public void updateDisplay(int padNum) {
		updateDisplay(pads.get(padNum));
	}

	private void updateDisplay(Pad pad) {
		if (mainController.isReady()) {
			Item item = mainController.getItemCorrespondingTo(pad.getNumber());
			logger.log(Level.ALL, "for pad n°" + pad.getNumber() + " " + item);

			pad.setLightIntensity(1);
			pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);

			switch (mainController.getCurrentMode()) {
			case Project:
				pad.setLightIntensity(0);
				pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);
				break;
			case Song:
				pad.setLightColor(Color.Blue);
				pad.setLightIntensity(0.1f);
				pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);
				break;
			case SequenceEdit:
				pad.setLightColor(Color.Blue);
				if (item != null && item instanceof SequenceEvent) {
					switch (((SequenceEvent) item).getState()) {
					case SequenceEvent.PLAY_STATE:
						pad.setLightIntensity(1);
						break;
					case SequenceEvent.STOP_STATE:
						pad.setLightColor(Color.Red);
						pad.setLightIntensity(1);
						break;
					default:
						pad.setLightIntensity(0.1f);
						break;
					}
				} else {
					pad.setLightIntensity(0);
				}
				pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);
				break;
			case PatternEdit:
				pad.setLightColor(Color.Blue);
				if (item != null && item instanceof PatternEvent) {
					switch (((PatternEvent) item).getState()) {
					case PatternEvent.PLAY_STATE:
						pad.setLightIntensity(((PatternEvent) item).getVelocity());
						break;
					case PatternEvent.STOP_STATE:
						pad.setLightColor(Color.Red);
						pad.setLightIntensity(1);
						break;
					default:
						pad.setLightIntensity(0.1f);
						break;
					}
				} else {
					pad.setLightIntensity(0);
				}
				pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);
				break;
			case PatternLaunch:
				pad.setLightColor(Color.Blue);
				pad.setLightIntensity(0.1f);
				pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);
				break;
			case AudioSampler:
			case VideoSampler:
				pad.setLightColor(Color.Red);
				if (item instanceof SamplerTrack) {
					SamplerTrack samplerTrack = (SamplerTrack) item;
					if (samplerTrack.isPlaying()) {
						pad.setLightIntensity(0.8f);
					} else {
						pad.setLightIntensity(0.2f);
					}
					if (item instanceof AudioSamplerTrack && ((AudioSamplerTrack) samplerTrack).isArmed()) {
						pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NORMAL);
					} else {
						pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);
					}
				}
				break;
			case AudioMixer:
			case VideoMixer:
				pad.setLightColor(Color.Green);
				if (item instanceof MixerTrack) {
					MixerTrack mixerTrack = (MixerTrack) item;
					if (mixerTrack.isMuted()) {
						pad.setLightIntensity(0);
					} else {
						if (mixerTrack instanceof AudioMixerTrack) {
							pad.setLightIntensity(((AudioMixerTrack) mixerTrack).getVolume());
						} else if (mixerTrack instanceof VideoMixerTrack) {
							pad.setLightIntensity(((VideoMixerTrack) mixerTrack).getOpacity());
						}
					}
				}
				pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);
				break;
			case AudioEffects:
			case VideoEffects:
				pad.setLightColor(Color.Pink);
				if (item != null && item instanceof Effect) {
					if (((Effect) item).isActivated()) {
						pad.setLightIntensity(1f);
					} else {
						pad.setLightIntensity(0.2f);
					}
				} else {
					pad.setLightIntensity(0);
				}
				pad.setLightBlinkingSpeed(Pad.BLINKING_SPEED_NO_BLINKING);
				break;
			default:
				break;
			}

			if (mainController.getSelectionController().isFirstSelected(item)) {
				pad.setLightColor(Color.Orange);
			} else if (mainController.getSelectionController().isSelected(item)) {
				pad.setLightColor(Color.Yellow);
			}
		} else {
			pad.setLightIntensity(0);
		}

		displayPad(pad);
	}

	private void displayPad(Pad pad) {
		sendMessage(pad.getNumber() + " intensity " + pad.getLightIntensity());
		sendMessage(pad.getNumber() + " color " + EnumUtils.getCorrespondingString(pad.getLightColor().name()));
		sendMessage(pad.getNumber() + " speed " + pad.getLightBlinkingSpeed());
	}
}
