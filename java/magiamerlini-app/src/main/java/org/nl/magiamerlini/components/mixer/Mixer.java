package org.nl.magiamerlini.components.mixer;

import java.util.logging.Level;

import org.nl.magiamerlini.communication.InputCommunication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.mixer.items.Effect;
import org.nl.magiamerlini.components.mixer.items.MixerTrack;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

import com.google.common.base.CaseFormat;

public abstract class Mixer extends CommunicatingComponent  {
	private final static String EDIT_PARAMETER = "edit_parameter";
	private final static String ACTIVATED = "activated";
	private final static String MUTED = "muted";
	private final static String EFFECT = "effect";

	public Mixer(InputCommunication communication, String name) {
		super(communication, name);
	}

	public void trackParameterEdited(MixerTrack mixerTrack, ParameterSnapshot parameter) {
		String[] message = { getIdentifierFor(mixerTrack), EDIT_PARAMETER, parameter.getName(),
				parameter.getDisplayedValue() };
		sendMessage(message);
	}

	public void effectParameterEdited(Effect effect, ParameterSnapshot parameter) {
		MixerTrack mixerTrack = effect.getMixerTrack();
		logger.log(Level.ALL, mixerTrack);
		String[] message = { getIdentifierFor(mixerTrack), EFFECT, getIdentifierFor(effect), EDIT_PARAMETER,
				parameter.getName(), parameter.getDisplayedValue() };
		sendMessage(message);
	}

	public void setEffectActivated(Effect effect, boolean state) {
		effect.setActivated(state);
		effectActivatedEdited(effect);
	}

	public void setTrackMuted(MixerTrack mixerTrack, boolean state) {
		mixerTrack.setMuted(state);
		trackMutedEdited(mixerTrack);
	}

	public void trackEdited(MixerTrack mixerTrack) {
		for (ParameterSnapshot parameter : mixerTrack.getParameters()) {
			trackParameterEdited(mixerTrack, parameter);
		}
		
		trackMutedEdited(mixerTrack);
	}

	public void effectEdited(Effect effect) {
		for (ParameterSnapshot parameter : effect.getParameters()) {
			effectParameterEdited(effect, parameter);
		}
		
		effectActivatedEdited(effect);
	}
	
	private void effectActivatedEdited(Effect effect) {
		mainController.getProjectManager().update(effect);
		MixerTrack mixerTrack = effect.getMixerTrack();
		String[] message = { getIdentifierFor(mixerTrack), EFFECT, getIdentifierFor(effect), ACTIVATED,
				(effect.isActivated() ? "1" : "0") };
		sendMessage(message);
		updateDisplay();
	}
	
	private void trackMutedEdited(MixerTrack mixerTrack) {
		mainController.getProjectManager().update(mixerTrack);
		String[] message = { getIdentifierFor(mixerTrack), MUTED, (mixerTrack.isMuted() ? "1" : "0") };
		sendMessage(message);
		updateDisplay();		
	}

	protected void updateDisplay() {
		mainController.updateDisplay();
	}

	protected String getIdentifierFor(Effect effect) {
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, effect.getClass().getSimpleName());
	}

	protected String getIdentifierFor(MixerTrack mixerTrack) {
		return String.valueOf(mixerTrack.getNumber());
	}

}
