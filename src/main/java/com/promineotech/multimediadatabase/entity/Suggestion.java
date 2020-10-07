package com.promineotech.multimediadatabase.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Suggestion {

	private Long id;
	private Long mediaId;
	private Long suggestedId;
	private Set<Media> suggestion;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public Long getSuggestedId() {
		return suggestedId;
	}

	public void setSuggestedId(Long suggestedId) {
		this.suggestedId = suggestedId;
	}

	public Set<Media> getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Set<Media> media) {
		this.suggestion = media;
	}

}
