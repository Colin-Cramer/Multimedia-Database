package com.promineotech.multimediadatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Genre;
import com.promineotech.multimediadatabase.entity.Media;
import com.promineotech.multimediadatabase.entity.Suggestion;
import com.promineotech.multimediadatabase.repository.MediaRepository;
import com.promineotech.multimediadatabase.repository.SuggestionRepository;

@Service
public class SuggestionService {

	@Autowired
	private SuggestionRepository suggestionRepo;
	
	@Autowired
	private MediaRepository mediaRepo;
	
	public Iterable<Suggestion> getSuggestionByGenre(String genreType) {
	
		return suggestionRepo
		
	}
	
}
