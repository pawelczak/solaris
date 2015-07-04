package pl.pawelczak.solaris.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pawelczak.solaris.persistence.model.Gallery;


public interface GalleryRepository extends JpaRepository<Gallery, Long> {

}

