package com.promineotech.multimediadatabase.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	private Long reviewId;
	private String content;
	private double rating;
	private User user;
	@JsonIgnore
	private Media media;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return reviewId;
	}

	public void setId(Long id) {
		this.reviewId = id;
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
	public Media getMedia() {
		return media;
	}
	
	public void setMedia(Media mediaId) {
		this.media = mediaId;
	}

}
