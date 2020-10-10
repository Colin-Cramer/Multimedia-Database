package com.promineotech.multimediadatabase.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Genre;
import com.promineotech.multimediadatabase.entity.Media;
import com.promineotech.multimediadatabase.entity.User;
import com.promineotech.multimediadatabase.repository.MediaRepository;
import com.promineotech.multimediadatabase.repository.UserRepository;
import com.promineotech.multimediadatabase.util.AccountLevel;

@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepo;

	@Autowired
	private UserRepository userRepo;
	
	public Iterable<Media> getAllMedia() {
		return mediaRepo.findAll();
	}
	
	public Media getMedia(Long id) {
		return mediaRepo.findOne(id);
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
	
//	public List<Media> suggestByGenreId(List<Genre> genres, Long genreId) {
//		List<Genre> genres1 = new ArrayList<Genre>();
//		List<Media> media = new ArrayList<Media>();
//		for(int i = 0; i < genres.size(); i++) {
//			if(genres.get(i).getGenreId() == genreId) {
//				genres1
//			}
//		}
//		List<Media> media = (List<Media>) mediaRepo.findAllByGenreId(genres);
//		return media;
//	}
}
