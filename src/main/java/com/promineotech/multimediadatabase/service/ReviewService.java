package com.promineotech.multimediadatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(ReviewService.class);
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Iterable<Review> getAllReviews() throws Exception {
		try {
			return reviewRepo.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve all reviews." + e);
			throw new Exception("Unable to retrieve reviews.");
		}
	}
	
	public Review getReview(Long reviewId) throws Exception {
		try {
			return reviewRepo.findOne(reviewId);
		} catch (Exception e) {
			logger.error("Exception occurred while tring to retrieve review: " + reviewId, e);
			throw new Exception("Unable to retrieve the review.");
		}
	}
	
	public Review createReview(Review review, Long userId, Long mediaId) throws Exception {
		User user = userRepo.findOne(userId);
		Media media = mediaRepo.findOne(mediaId);
		if(user == null || media == null) {
			throw new Exception("User or Media does not Exist.");
		}
		review.setUser(user);
		review.setMedia(media);
		media.setAvgRating(calculateAvgRating(mediaId));
		mediaRepo.save(media);
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
	
	public void deleteReview(Long reviewId) throws Exception {
		try {
			reviewRepo.delete(reviewId);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete review" + e);
			throw new Exception("Unable to delete review.");
		}
	}
	
	public double calculateAvgRating(Long mediaId) {
		List<Review> allReviews = (List<Review>) reviewRepo.findAll();
		List<Review> specificReviews = new ArrayList<Review>();
		for(int i = 0; i < allReviews.size(); i++) {
			if(allReviews.get(i).getMedia().getMediaId() == mediaId) {
				specificReviews.add(allReviews.get(i));
			}
		}
		List<Double> ratings = new ArrayList<Double>();
		double avgRating = 0;
		for(int i = 0; i < specificReviews.size(); i++) {
			ratings.add(specificReviews.get(i).getRating());
		}
		for(double rating : ratings) {
			avgRating += rating;
		}
		return avgRating / ratings.size();
	}
}
