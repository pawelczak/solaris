package pl.pawelczak.solaris.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pawelczak.solaris.persistence.model.Photo;


public interface PhotoRepository extends JpaRepository<Photo, Long> {

	public List<Photo> findAllByGalleryId(Long galleryId);
	
	/*
	public void delete(Photo photo);
	
	@Modifying
	@Transactional
	@Query("delete from Photo photo where photo.gallery_id = ?1")
	public void deleteByGalleryId(Long galleryId);
	*/
}

