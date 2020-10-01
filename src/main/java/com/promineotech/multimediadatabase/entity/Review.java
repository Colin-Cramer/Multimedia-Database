package com.promineotech.multimediadatabase.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Review {

	private Long id;
	private String content;
	private double rating;
	private User user;
	
	@JsonIgnore
	private Media media;

	public Long getId() {
		return id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "mediaId")
	public Media getmedia() {
		return media;
	}
	
	public void setMedia(Media media) {
		this.media = media;
	}

}
