package com.promineotech.multimediadatabase.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genre {

	private Long genreId;
	private String genreType;
	
	@JsonIgnore
	private List<Media> media;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long id) {
		this.genreId = id;
	}

	public String getGenreType() {
		return genreType;
	}

	public void setGenreType(String genre) {
		this.genreType = genre;
	}

	@ManyToMany(mappedBy = "genres")
	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}
}
