package org.nl.magiamerlini.components.mixer.items;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "video_mixer_track")
public class VideoMixerTrack extends MixerTrack implements Serializable {
	private final static String OUTPUT_CHANNEL_PARAMETER = "output_channel";
	private final static String VOLUME_PARAMETER = "volume";
	private final static String EFFECTS_SEND_PARAMETER = "effects_send";


	@Column(name = VOLUME_PARAMETER)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float volume;

	@Column(name = OUTPUT_CHANNEL_PARAMETER)
	@Parameter(min = 1, max = 16, step = 1, defaultValue = 1)
	private float outputChannel;

	@Column(name = EFFECTS_SEND_PARAMETER)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float effectsSend;

	public VideoMixerTrack() {
		super();
	}

	public VideoMixerTrack(int number) {
		super(number);
	}
	
	public float getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(float outputChannel) {
		this.outputChannel = outputChannel;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getEffectsSend() {
		return effectsSend;
	}

	public void setEffectsSend(float effectsSend) {
		this.effectsSend = effectsSend;
	}

}
