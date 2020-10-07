package com.promineotech.multimediadatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Media;
import com.promineotech.multimediadatabase.entity.User;
import com.promineotech.multimediadatabase.repository.MediaRepository;
import com.promineotech.multimediadatabase.repository.UserRepository;
import com.promineotech.multimediadatabase.util.AccountLevel;

@Service
public class MediaService {
	
	@Autowired
	private MediaRepository repo;

	@Autowired
	private UserRepository userRepo;
	
	public Iterable<Media> getAllMedia() {
		return repo.findAll();
	}
	
	public Media getMedia(Long id) {
		return repo.findOne(id);
	}
	
	public Media createMedia(Media media, Long userId) throws Exception {
		User user = userRepo.findOne(userId);
		if(user == null) {
			throw new Exception("User not found.");
		} else if(user.getLevel() != AccountLevel.ADMIN) {
			throw new Exception("Only an admin can create a media page.");
		}
		return repo.save(media);
	}
	
	public Media updateMedia(Media media, Long userId, Long id) throws Exception {
		User user = userRepo.findOne(userId);
		Media foundMedia = repo.findOne(id);
		if(user.getLevel() != AccountLevel.ADMIN) {
			throw new Exception("Only an admin can update a media page.");
		}
		if(foundMedia == null) {
			throw new Exception("Media not found.");
		}
		foundMedia.setTitle(media.getTitle());
		foundMedia.setGenres(media.getGenres());
		foundMedia.setSummary(media.getSummary());
		return repo.save(foundMedia);
	}
	
}
