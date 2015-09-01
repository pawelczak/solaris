package pl.pawelczak.solaris.webapp.site.api.photo.service;

import java.util.List;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.common.photo.PhotoServiceBase;

public interface PhotoApiService extends PhotoServiceBase {

	public List<Photo> findAllByGalleryId(Long galleryId);
}
