package com.promineotech.multimediadatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.multimediadatabase.entity.Genre;
import com.promineotech.multimediadatabase.service.SuggestionService;


@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

	@Autowired
	private SuggestionService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> getSuggestionByGenre(@RequestBody String genreType) {
		try {
			return new ResponseEntity<Object>(service.getSuggestionByGenre(genreType), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
