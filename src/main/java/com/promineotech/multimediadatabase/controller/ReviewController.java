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

import com.promineotech.multimediadatabase.entity.Review;
import com.promineotech.multimediadatabase.service.AuthService;
import com.promineotech.multimediadatabase.service.ReviewService;

@RestController
@RequestMapping("/users/{userId}/media/{mediaId}/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllReviews() throws Exception {
		return new ResponseEntity<Object>(reviewService.getAllReviews(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{reviewId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getReview(@PathVariable Long reviewId) throws Exception {
		return new ResponseEntity<Object>(reviewService.getReview(reviewId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createReview(@RequestBody Review review, @PathVariable Long userId, @PathVariable Long mediaId, HttpServletRequest request) {
		try {
			if(authService.isCorrectUser(authService.getToken(request), userId)) {
				return new ResponseEntity<Object>(reviewService.createReview(review,userId,mediaId), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Unauthorized request.", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{reviewId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateReview(@RequestBody Review review, @PathVariable Long reviewId, @PathVariable Long mediaId, @PathVariable Long userId, HttpServletRequest request) {
		try {
			if(authService.isCorrectUser(authService.getToken(request), userId)) {
				return new ResponseEntity<Object>(reviewService.updateReview(review, reviewId, mediaId), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Unauthorized request.", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{reviewId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteReview(@PathVariable Long reviewId) throws Exception {
		reviewService.deleteReview(reviewId);
		return new ResponseEntity<Object>("Deleted review with id: " + reviewId, HttpStatus.OK);
	}

}
