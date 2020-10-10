package com.promineotech.multimediadatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Media;
import com.promineotech.multimediadatabase.entity.Review;
import com.promineotech.multimediadatabase.entity.User;
import com.promineotech.multimediadatabase.repository.MediaRepository;
import com.promineotech.multimediadatabase.repository.ReviewRepository;
import com.promineotech.multimediadatabase.repository.UserRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Iterable<Review> getAllReviews() {
		return reviewRepo.findAll();
	}
	
	public Review getReview(Long reviewId) {
		return reviewRepo.findOne(reviewId);
	}
	
	public Review createReview(Review review, Long userId, Long mediaId) throws Exception {
		User user = userRepo.findOne(userId);
		Media media = mediaRepo.findOne(mediaId);
		if(user == null || media == null) {
			throw new Exception("User or Post does not Exist.");
		}
		review.setUser(user);
		review.setMedia(media);
		return reviewRepo.save(review);
	}
	
	public Review updateReview(Review review, Long reviewId, Long mediaId) throws Exception {
		Review foundReview = reviewRepo.findOne(reviewId);
		if(foundReview == null) {
			throw new Exception("Review not found.");
		}
		foundReview.setContent(review.getContent());
		foundReview.setRating(review.getRating());
		return reviewRepo.save(foundReview);
	}
	
	public void deleteReview(Long reviewId) {
		reviewRepo.delete(reviewId);
	}
	
}
