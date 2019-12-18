package org.nl.magiamerlini.components.audio.implementations;

import org.nl.magiamerlini.App;
import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.audio.api.AudioSampler;
import org.nl.magiamerlini.components.audio.items.AudioSamplerTrack;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public class CommunicatingAudioSampler extends CommunicatingComponent implements AudioSampler {
	private final static String COMPONENT_NAME = "sampler";
	private final static String PLAY = "play";
	private final static String STOP = "stop";
	private final static String EDIT_PARAMETER = "edit_parameter";
	private final static String LOAD = "load";

	public CommunicatingAudioSampler(Communication communication) {
		super(communication);
	}

	@Override
	public void playTrack(AudioSamplerTrack samplerTrack, float velocity) {
		System.out.println("PLAY " + samplerTrack);
		sendMessage(samplerTrack, PLAY, "velocity", String.valueOf(velocity));
	}

	@Override
	public void stopTrack(AudioSamplerTrack samplerTrack) {
		sendMessage(samplerTrack, STOP, "", "");
	}

	@Override
	public void editTrackParameter(AudioSamplerTrack samplerTrack, ParameterSnapshot parameter) {
		sendMessage(samplerTrack, EDIT_PARAMETER, parameter.getName(), parameter.getDisplayedValue());
	}

	@Override
	public void loadTrackSample(AudioSamplerTrack samplerTrack, String path) {
		samplerTrack.setFilePath(path);
		mainController.getProjectManager().updateEntity(samplerTrack);
		sendMessage(samplerTrack, LOAD, App.ROOT_DIR_FOR_FILES + "/" + samplerTrack.getFilePath(), "");
	}

	@Override
	public void armTrackForRecording() {
		// TODO
	}

	private void sendMessage(AudioSamplerTrack samplerTrack, String action, String parameter1, String parameter2) {
		communication.sendMessage(COMPONENT_NAME + " " + samplerTrack.getBank() + " " + samplerTrack.getNumber() + " "
				+ action + " " + parameter1 + " " + parameter2);
	}
}
