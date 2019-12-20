package org.nl.magiamerlini.components.sampler.implementations;

import java.util.logging.Level;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.sampler.api.Sampler;
import org.nl.magiamerlini.components.sampler.items.SamplerTrack;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public abstract class CommunicatingSampler extends CommunicatingComponent implements Sampler {
	private final static String EDIT_PARAMETER = "edit_parameter";
	private final static String PLAY = "play";
	private final static String STOP = "stop";
	private final static String VELOCITY = "velocity";

	public CommunicatingSampler(Communication communication, String name) {
		super(communication, name);
	}

	@Override
	public void playTrack(SamplerTrack samplerTrack, float velocity) {
		samplerTrack.setPlaying(true);
		String[] message = { getIdentifierFor(samplerTrack), PLAY, VELOCITY, String.valueOf(velocity) };
		sendMessage(message);
		updateDisplay();
	}

	@Override
	public void trackStopped(SamplerTrack samplerTrack) {
		samplerTrack.setPlaying(false);
		updateDisplay();
	}

	@Override
	public void trackParameterEdited(SamplerTrack samplerTrack, ParameterSnapshot parameter) {
		String[] message = { getIdentifierFor(samplerTrack), EDIT_PARAMETER, parameter.getName(),
				parameter.getDisplayedValue() };
		sendMessage(message);
	}

	@Override
	public void stopTrack(SamplerTrack samplerTrack) {
		String[] message = { getIdentifierFor(samplerTrack), STOP, "", "" };
		sendMessage(message);
	}

	@Override
	public void loadTrackSample(SamplerTrack samplerTrack, String path) {
		samplerTrack.setFilePath(path);
		mainController.getProjectManager().update(samplerTrack);
		// TODO: load sample in puredata
	}
	
	@Override
	public void trackEdited(SamplerTrack samplerTrack) {
		for (ParameterSnapshot parameter : samplerTrack.getParameters()) {
			trackParameterEdited(samplerTrack, parameter);
		}
		
		// TODO: load sample in puredata
	}

	protected void updateDisplay() {
		mainController.updateDisplay();
	}

	protected String getIdentifierFor(SamplerTrack samplerTrack) {
		return samplerTrack.getBank() + " " + samplerTrack.getNumber();
	}
}
