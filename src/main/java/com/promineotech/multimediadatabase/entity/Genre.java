package com.promineotech.multimediadatabase.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genre {

	private Long id;
	private String genreType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenreType() {
		return genreType;
	}

	public void setGenreType(String genre) {
		this.genreType = genre;
	}

}
