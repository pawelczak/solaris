package pl.pawelczak.solaris.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pawelczak.solaris.persistence.model.Photo;


public interface PhotoRepository extends JpaRepository<Photo, Long> {

}

