package com.promineotech.multimediadatabase.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.promineotech.multimediadatabase.entity.Media;

public interface MediaRepository extends CrudRepository<Media, Long>{

	public List<Media> findAllByGenreId(Long genreId);
	
}
