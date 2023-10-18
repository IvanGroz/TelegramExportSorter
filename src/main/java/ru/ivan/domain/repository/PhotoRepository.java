package ru.ivan.domain.repository;

import ru.ivan.domain.entity.PhotoEntity;

import java.util.TreeSet;

public interface PhotoRepository {
  TreeSet<PhotoEntity> getPhotos(String folderPath);
  void sortPhotos(TreeSet<PhotoEntity> photos);
}
