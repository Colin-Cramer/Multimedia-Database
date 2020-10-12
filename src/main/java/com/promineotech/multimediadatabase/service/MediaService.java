package com.promineotech.multimediadatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Genre;
import com.promineotech.multimediadatabase.entity.Media;
import com.promineotech.multimediadatabase.entity.Review;
import com.promineotech.multimediadatabase.entity.User;
import com.promineotech.multimediadatabase.repository.MediaRepository;
import com.promineotech.multimediadatabase.repository.ReviewRepository;
import com.promineotech.multimediadatabase.repository.UserRepository;
import com.promineotech.multimediadatabase.util.AccountLevel;

@Service
public class MediaService {
	
	private static final Logger logger = LogManager.getLogger(MediaService.class);

	@Autowired
	private MediaRepository mediaRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	public Iterable<Media> getAllMedia() throws Exception {
		try {
			return mediaRepo.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve all media." + e);
			throw new Exception("Unable to retrieve all media.");
		}
	}
	
	public Media getMedia(Long mediaId) throws Exception {
		try {
			return mediaRepo.findOne(mediaId);
			return calculateAvgRating(mediaId);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve media: " + mediaId, e);
			throw new Exception("Unable to retrieve media.");
		}
	}
	
	public Media createMedia(Media media, Long userId) throws Exception {
		User user = userRepo.findOne(userId);
		if(user == null) {
			throw new Exception("User not found.");
		} else if(user.getLevel() != AccountLevel.ADMIN) {
			throw new Exception("Only an admin can create a media page.");
		}
		return mediaRepo.save(media);
	}
	
	public Media updateMedia(Media media, Long userId, Long id) throws Exception {
		User user = userRepo.findOne(userId);
		Media foundMedia = mediaRepo.findOne(id);
		if(user.getLevel() != AccountLevel.ADMIN) {
			throw new Exception("Only an admin can update a media page.");
		}
		if(foundMedia == null) {
			throw new Exception("Media not found.");
		}
		foundMedia.setTitle(media.getTitle());
		foundMedia.setGenres(media.getGenres());
		foundMedia.setSummary(media.getSummary());
		return mediaRepo.save(foundMedia);
	}
	
	public double calculateAvgRating(Long mediaId) {
		List<Review> allReviews = (List<Review>) reviewRepo.findAll();
		List<Review> specificReviews = new ArrayList<Review>();
		for(int i = 0; i < allReviews.size(); i++) {
			if(allReviews.get(i).getMediaId() == mediaId) {
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
	
	public List<Media> suggestByGenreId(List<Media> media, List<Genre> genres, Long genreId) {
		List<Genre> newGenres = new ArrayList<Genre>();
		for(int i = 0; i < genres.size(); i++) {
			if(genres.get(i).getGenreId() == genreId) {
				newGenres.add(genres.get(i));
			}
		}
		List<Media> newMedia = (List<Media>) mediaRepo.findAllByGenreId(media, newGenres, genreId);
		return newMedia;
	}
}
