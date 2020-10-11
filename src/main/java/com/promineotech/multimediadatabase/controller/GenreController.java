package com.promineotech.multimediadatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.multimediadatabase.entity.Genre;
import com.promineotech.multimediadatabase.service.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {
	
	@Autowired
	private GenreService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllGenres() throws Exception {
		return new ResponseEntity<Object>(service.getAllGenres(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{genreId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getGenre(@PathVariable Long genreId) throws Exception {
		return new ResponseEntity<Object>(service.getGenre(genreId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createGenre(@RequestBody Genre genre) {
		try {
			return new ResponseEntity<Object>(service.createGenre(genre), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{genreId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateGenre(@RequestBody Genre genre, @PathVariable Long genreId) {
		try {
			return new ResponseEntity<Object> (service.updateGenre(genre, genreId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{genreId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteGenre(@PathVariable Long genreId) {
		try {
			service.deleteGenre(genreId);
			return new ResponseEntity<Object>("Deleted genre with id: " + genreId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
