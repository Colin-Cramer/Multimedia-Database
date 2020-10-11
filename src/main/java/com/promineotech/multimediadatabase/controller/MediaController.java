package com.promineotech.multimediadatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.multimediadatabase.entity.Media;
import com.promineotech.multimediadatabase.service.MediaService;

@RestController
@RequestMapping("/users/{userId}/media")
public class MediaController {

	@Autowired
	private MediaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllMedia() throws Exception {
		return new ResponseEntity<Object>(service.getAllMedia(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{mediaId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMedia(@PathVariable Long mediaId) throws Exception {
		return new ResponseEntity<Object>(service.getMedia(mediaId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createMedia(@RequestBody Media media, @PathVariable Long userId) {
		try {
			return new ResponseEntity<Object>(service.createMedia(media, userId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{mediaId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateMedia(@RequestBody Media media, @PathVariable Long userId, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateMedia(media, userId, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}