package org.nl.magiamerlini.data.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "project")
public class Project implements Item {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String location;

//	 TODO: clear
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	 @JoinColumn(name = "sampler_track_id")
	@OneToMany(targetEntity = SamplerTrack.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@OneToMany
//	@JoinTable(joinColumns=@JoinColumn(name="project_id"), inverseJoinColumns=@JoinColumn(name="sampler_track_id"))
	private Collection<SamplerTrack> samplerTracks;

	public Project() {

	}

	public Project(String name, String location) {
		this.name = name;
		this.location = location;
		samplerTracks = new ArrayList<SamplerTrack>();
	}

	public Collection<SamplerTrack> getSamplerTracks() {
		return samplerTracks;
	}

	public void setSamplerTracks(Collection<SamplerTrack> samplerTracks) {
		this.samplerTracks = samplerTracks;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", name=" + name + ", location=" + location + "]";
	}

	public void displaySamplerTracks() {
		System.out.println("------ SAMPLER TRACKS -----");
		getSamplerTracks().forEach((SamplerTrack samplerTrack) -> {
			System.out.println("- " + samplerTrack);
		});
		System.out.println("-----------");
	}

	public void addSamplerTrack(SamplerTrack samplerTrack) {
		samplerTracks.add(samplerTrack);
	}

	@Override
	public List<Parameter> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyParameter(Parameter parameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toDisplay() {
		return "project";
	}
}