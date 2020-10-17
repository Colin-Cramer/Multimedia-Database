package com.promineotech.multimediadatabase.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.multimediadatabase.entity.Media;
import com.promineotech.multimediadatabase.service.AuthService;
import com.promineotech.multimediadatabase.service.MediaService;

@RestController
@RequestMapping("/users/{userId}/media")
public class MediaController {

	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllMedia() throws Exception {
		return new ResponseEntity<Object>(mediaService.getAllMedia(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{mediaId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMedia(@PathVariable Long mediaId) throws Exception {
		return new ResponseEntity<Object>(mediaService.getMedia(mediaId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createMedia(@RequestBody Media media, @PathVariable Long userId, HttpServletRequest request) {
		try {
			if(authService.isCorrectUser(authService.getToken(request), userId)) {
				return new ResponseEntity<Object>(mediaService.createMedia(media, userId), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>("Unauthorized request.", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{mediaId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateMedia(@RequestBody Media media, @PathVariable Long userId, HttpServletRequest request) {
		try {
			if(authService.isCorrectUser(authService.getToken(request), userId)) {
				return new ResponseEntity<Object>(mediaService.updateMedia(media, userId), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Unauthorized request.", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}