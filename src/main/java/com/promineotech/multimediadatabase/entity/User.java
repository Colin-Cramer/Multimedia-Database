package com.promineotech.multimediadatabase.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.promineotech.multimediadatabase.util.AccountLevel;

@Entity
public class User {

	private Long id;
	private String username;
	private String hash;
	private AccountLevel level;

	@JsonIgnore
	private Set<Media> media;
	
	@JsonIgnore
	private Set<Review> reviews;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getHash() {
		return hash;
	}
	
	@JsonProperty
	public void setHash(String hash) {
		this.hash = hash;
	}

	@OneToMany(mappedBy = "user")
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public AccountLevel getLevel() {
		return level;
	}

	public void setLevel(AccountLevel level) {
		this.level = level;
	}

	@OneToMany(mappedBy = "user")
	public Set<Media> getMedia() {
		return media;
	}

	public void setMedia(Set<Media> media) {
		this.media = media;
	}
}