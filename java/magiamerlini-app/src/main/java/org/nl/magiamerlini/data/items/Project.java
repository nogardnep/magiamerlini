package org.nl.magiamerlini.data.items;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.nl.magiamerlini.components.audio.items.AudioMixerTrack;
import org.nl.magiamerlini.components.audio.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sequencer.items.Pattern;
import org.nl.magiamerlini.components.sequencer.items.Sequence;
import org.nl.magiamerlini.components.sequencer.items.Song;
import org.nl.magiamerlini.components.video.items.VideoMixerTrack;
import org.nl.magiamerlini.components.video.items.VideoSamplerTrack;
import org.nl.magiamerlini.data.tools.Alias;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "project")
public class Project extends Item {
	public final static String SONG_PARAMETER = "song";
	public final static String METRONOME_VOLUME_PARAMETER = "metronome_volume";
	public final static String PRECOUNT_PARAMETER = "precount";
	public final static float PRECOUNT_BASED_ON_SELECTION = 0;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "path")
	private String path;

	@OneToMany(targetEntity = AudioSamplerTrack.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<AudioSamplerTrack> audioSamplerTracks;

	@OneToMany(targetEntity = AudioMixerTrack.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<AudioMixerTrack> audioMixerTracks;

	@OneToMany(targetEntity = VideoSamplerTrack.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<VideoSamplerTrack> videoSamplerTracks;

	@OneToMany(targetEntity = VideoMixerTrack.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<VideoMixerTrack> videoMixerTracks;

	@OneToMany(targetEntity = Pattern.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Pattern> patterns;

	@OneToMany(targetEntity = Sequence.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Sequence> sequences;

	@OneToMany(targetEntity = Song.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Song> songs;

	@Column(name = SONG_PARAMETER)
	@Parameter(min = 1, max = 16, step = 1, defaultValue = 1)
	private float song;

	@Column(name = METRONOME_VOLUME_PARAMETER)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0.5f)
	private float metronomeVolume;

	@Column(name = PRECOUNT_PARAMETER)
	@Parameter(min = 0, max = 8, step = 1, defaultValue = PRECOUNT_BASED_ON_SELECTION, aliases = {
			@Alias(name = "based_on_selection", value = PRECOUNT_BASED_ON_SELECTION) })
	private float precount;

	public Project() {

	}

	public Project(String name, String location) {
		this();
		this.name = name;
		this.path = location;
		audioSamplerTracks = new ArrayList<AudioSamplerTrack>();
		audioMixerTracks = new ArrayList<AudioMixerTrack>();
		videoSamplerTracks = new ArrayList<VideoSamplerTrack>();
		videoMixerTracks = new ArrayList<VideoMixerTrack>();
		patterns = new ArrayList<Pattern>();
		sequences = new ArrayList<Sequence>();
		songs = new ArrayList<Song>();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", name=" + name + ", location=" + path + "]";
	}

	@Override
	public String toDisplay() {
		return "project";
	}

	public void addSamplerTrack(AudioSamplerTrack samplerTrack) {
		audioSamplerTracks.add(samplerTrack);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public float getMetronomeVolume() {
		return metronomeVolume;
	}

	public void setMetronomeVolume(float metronomeVolume) {
		this.metronomeVolume = metronomeVolume;
	}

	public float getPrecount() {
		return precount;
	}

	public void setPrecount(float precount) {
		this.precount = precount;
	}

	public Collection<AudioSamplerTrack> getAudioSamplerTracks() {
		return audioSamplerTracks;
	}

	public void setAudioSamplerTracks(Collection<AudioSamplerTrack> audioSamplerTracks) {
		this.audioSamplerTracks = audioSamplerTracks;
	}

	public Collection<AudioMixerTrack> getAudioMixerTracks() {
		return audioMixerTracks;
	}

	public void setAudioMixerTracks(Collection<AudioMixerTrack> audioMixerTracks) {
		this.audioMixerTracks = audioMixerTracks;
	}

	public Collection<VideoSamplerTrack> getVideoSamplerTracks() {
		return videoSamplerTracks;
	}

	public void setVideoSamplerTracks(Collection<VideoSamplerTrack> videoSamplerTracks) {
		this.videoSamplerTracks = videoSamplerTracks;
	}

	public Collection<VideoMixerTrack> getVideoMixerTracks() {
		return videoMixerTracks;
	}

	public void setVideoMixerTracks(Collection<VideoMixerTrack> videoMixerTracks) {
		this.videoMixerTracks = videoMixerTracks;
	}

	public Collection<Pattern> getPatterns() {
		return patterns;
	}

	public void setPatterns(Collection<Pattern> patterns) {
		this.patterns = patterns;
	}

	public Collection<Sequence> getSequences() {
		return sequences;
	}

	public void setSequences(Collection<Sequence> sequences) {
		this.sequences = sequences;
	}

	public Collection<Song> getSongs() {
		return songs;
	}

	public void setSongs(Collection<Song> songs) {
		this.songs = songs;
	}

	public float getSong() {
		return song;
	}

	public void setSong(float song) {
		this.song = song;
	}

}