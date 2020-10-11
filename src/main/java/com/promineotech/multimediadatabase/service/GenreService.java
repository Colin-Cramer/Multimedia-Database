package com.promineotech.multimediadatabase.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Genre;
import com.promineotech.multimediadatabase.repository.GenreRepository;

@Service
public class GenreService {
	
	private static final Logger logger = LogManager.getLogger(GenreService.class);

	@Autowired
	private GenreRepository genreRepo;
	
	public Iterable<Genre> getAllGenres() throws Exception {
		try {
			return genreRepo.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve all genres." + e);
			throw new Exception("Unable to retrieve genres.");
		}
	}
	
	public Genre getGenre(Long genreId) throws Exception {
		try {
			return genreRepo.findOne(genreId);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve a genre: " + genreId, e);
			throw new Exception("Unable to retrieve the genre.");
		}
	}
	
	public Genre createGenre(Genre genre) throws Exception {
		try {
			return genreRepo.save(genre);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to create a genre." + e);
			throw new Exception("Unable to create genre.");
		}
	}

	public Genre updateGenre(Genre genre, Long genreId) throws Exception {
		try {
			Genre foundGenre = genreRepo.findOne(genreId);
			foundGenre.setGenreType(genre.getGenreType());
			return genreRepo.save(foundGenre);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update genre: " + genreId, e);
			throw new Exception("Unable to update genre.");
		}
	}	
	
	public void deleteGenre(Long genreId) throws Exception {
		try {
			genreRepo.delete(genreId);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete genre: " + genreId, e);
			throw new Exception("Unable to delete genre.");
		}
	}
}
