package com.promineotech.multimediadatabase.entity;

import java.util.Set;

public class Suggestion {

	private Long id;
	private Long mediaId;
	private Long suggestedId;
	private Set<Media> media;

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

	public Set<Media> getMedia() {
		return media;
	}

	public void setMedia(Set<Media> media) {
		this.media = media;
	}

}
