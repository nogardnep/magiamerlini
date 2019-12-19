package org.nl.magiamerlini.data.items;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.nl.magiamerlini.components.mixer.items.AudioMixerTrack;
import org.nl.magiamerlini.components.mixer.items.VideoMixerTrack;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.VideoSamplerTrack;
import org.nl.magiamerlini.components.sequencer.items.Pattern;
import org.nl.magiamerlini.components.sequencer.items.Sequence;
import org.nl.magiamerlini.components.sequencer.items.Song;
import org.nl.magiamerlini.data.tools.Alias;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "project")
public class Project extends Item implements Serializable {
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

	@OneToMany(targetEntity = AudioSamplerTrack.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AudioSamplerTrack> audioSamplerTracks;

	@OneToMany(targetEntity = AudioMixerTrack.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AudioMixerTrack> audioMixerTracks;

	@OneToMany(targetEntity = VideoSamplerTrack.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<VideoSamplerTrack> videoSamplerTracks;

	@OneToMany(targetEntity = VideoMixerTrack.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<VideoMixerTrack> videoMixerTracks;

	@OneToMany(targetEntity = Pattern.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Pattern> patterns;

	@OneToMany(targetEntity = Sequence.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Sequence> sequences;

	@OneToMany(targetEntity = Song.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Song> songs;

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
		audioSamplerTracks = new HashSet<AudioSamplerTrack>();
		audioMixerTracks = new HashSet<AudioMixerTrack>();
		videoSamplerTracks = new HashSet<VideoSamplerTrack>();
		videoMixerTracks = new HashSet<VideoMixerTrack>();
		patterns = new HashSet<Pattern>();
		sequences = new HashSet<Sequence>();
		songs = new HashSet<Song>();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", name=" + name + ", location=" + path + "]";
	}

	@Override
	public String toDisplay() {
		return "project";
	}

	public void addAudioSamplerTrack(AudioSamplerTrack audioSamplerTrack) {
		audioSamplerTracks.add(audioSamplerTrack);
	}

	public void addVideoSamplerTrack(VideoSamplerTrack videoSamplerTrack) {
		videoSamplerTracks.add(videoSamplerTrack);
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

	public Set<AudioSamplerTrack> getAudioSamplerTracks() {
		return audioSamplerTracks;
	}

	public void setAudioSamplerTracks(Set<AudioSamplerTrack> audioSamplerTracks) {
		this.audioSamplerTracks = audioSamplerTracks;
	}

	public Set<AudioMixerTrack> getAudioMixerTracks() {
		return audioMixerTracks;
	}

	public void setAudioMixerTracks(Set<AudioMixerTrack> audioMixerTracks) {
		this.audioMixerTracks = audioMixerTracks;
	}

	public Set<VideoSamplerTrack> getVideoSamplerTracks() {
		return videoSamplerTracks;
	}

	public void setVideoSamplerTracks(Set<VideoSamplerTrack> videoSamplerTracks) {
		this.videoSamplerTracks = videoSamplerTracks;
	}

	public Set<VideoMixerTrack> getVideoMixerTracks() {
		return videoMixerTracks;
	}

	public void setVideoMixerTracks(Set<VideoMixerTrack> videoMixerTracks) {
		this.videoMixerTracks = videoMixerTracks;
	}

	public Set<Pattern> getPatterns() {
		return patterns;
	}

	public void setPatterns(Set<Pattern> patterns) {
		this.patterns = patterns;
	}

	public Set<Sequence> getSequences() {
		return sequences;
	}

	public void setSequences(Set<Sequence> sequences) {
		this.sequences = sequences;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	public float getSong() {
		return song;
	}

	public void setSong(float song) {
		this.song = song;
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

}