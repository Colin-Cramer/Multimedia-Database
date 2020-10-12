package com.promineotech.multimediadatabase.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Media {

	private Long mediaId;
	private String title;
	private String summary;
//	private double avgRating;
	private String creator;
	private User user;
	private Long genreId;
	private List<Genre> genres;
	private List<Review> reviews;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return mediaId;
	}

	public void setId(Long id) {
		this.mediaId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

//	public double getAvgRating() {
//		return avgRating;
//	}
//
//	public void setAvgRating(double avgRating) {
//		this.avgRating = avgRating;
//	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@OneToMany(mappedBy = "media")
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany
	@JoinTable(name = "media_genre", 
				joinColumns = @JoinColumn(name = "mediaId"), 
				inverseJoinColumns = @JoinColumn(name = "genreId"))
	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}
}
