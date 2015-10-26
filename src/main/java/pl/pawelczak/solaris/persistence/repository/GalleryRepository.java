package pl.pawelczak.solaris.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pawelczak.solaris.persistence.model.Gallery;


public interface GalleryRepository extends JpaRepository<Gallery, Long> {

	public List<Gallery> findByVisibleTrue();

}

