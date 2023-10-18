package ru.ivan.domain.usecase;

import ru.ivan.domain.entity.PhotoEntity;
import ru.ivan.domain.repository.PhotoRepository;

import java.util.TreeSet;

public class SortPhotosByFolderUseCase {
  private final PhotoRepository photoRepository;

  public SortPhotosByFolderUseCase( PhotoRepository photoRepository) {
    this.photoRepository = photoRepository;
  }


  public TreeSet<PhotoEntity> invoke(String rootFolderPath) {
    return photoRepository.getPhotos(rootFolderPath);
  }
}
