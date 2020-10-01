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
	private ReviewRepository repo;
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Review createReview(Review review, Long userId, Long mediaId) throws Exception {
		User user = userRepo.findOne(userId);
		Media media = mediaRepo.findOne(mediaId);
		if(user == null || media == null) {
			throw new Exception("User or Post does not Exist.");
		}
		review.setUser(user);
		review.setMedia(media);
		return repo.save(review);
	}
	
	public Review updateReview(Review review, Long id, Long mediaId) throws Exception {
		Review foundReview = repo.findOne(id);
		if(foundReview == null) {
			throw new Exception("Review not found.");
		}
		foundReview.setContent(review.getContent());
		foundReview.setRating(review.getRating());
		return repo.save(foundReview);
	}
	
	public void deleteReview(Long reviewId) {
		repo.delete(reviewId);
	}
	
}
