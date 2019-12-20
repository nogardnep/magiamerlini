package org.nl.magiamerlini.controllers.api;

import java.util.ArrayList;

import org.nl.magiamerlini.components.mixer.items.AudioMixerTrack;
import org.nl.magiamerlini.components.mixer.items.Effect;
import org.nl.magiamerlini.components.mixer.items.VideoMixerTrack;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.VideoSamplerTrack;
import org.nl.magiamerlini.data.items.Project;

/**
 * Every project stored in separated H2 databases, accessible through specific
 * path Every database contains only one Project entity, with other entities
 * related to that project
 */
public interface ProjectsController {
	public ArrayList<Project> getProjects();

	public Project getCurrentProject();

	public void createProject(String name);

	public void createProject(String name, String path);

	public void deleteCurrentProject();

	public void loadProject(String path);

	public AudioSamplerTrack getAudioSamplerTrack(int bank, int number);

	public void update(Object entity);

	public void persist(Object entity);

	public VideoSamplerTrack getVideoSamplerTrack(int bank, int number);

	public AudioMixerTrack getAudioMixerTrack(int number);

	public VideoMixerTrack getVideoMixerTrack(int number);

	public Effect getAudioEffect(int currentTrackIndex, int number);

	public Effect getVideoEffect(int currentTrackIndex, int number);
}