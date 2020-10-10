package com.promineotech.multimediadatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Genre;
import com.promineotech.multimediadatabase.repository.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository genreRepo;
	
	public Iterable<Genre> getAllGenres() {
		return genreRepo.findAll();
	}
	
	public Genre getGenre(Long genreId) {
		return genreRepo.findOne(genreId);
	}
	
	public Genre createGenre(Genre genre) {
		return genreRepo.save(genre);
	}

	public Genre updateGenre(Genre genre, Long genreId) throws Exception {
		Genre foundGenre = genreRepo.findOne(genreId);
		if(foundGenre == null) {
			throw new Exception("Genre not found.");
		}
		foundGenre.setGenreType(genre.getGenreType());
		return genreRepo.save(foundGenre);
	}
	
	public void deleteGenre(Long genreId) throws Exception {
		if(genreId == null) {
			throw new Exception("Genre not found.");
		}
		genreRepo.delete(genreId);
	}
}
