package ru.ivan.data.repository;

import ru.ivan.data.converter.PhotoConverter;
import ru.ivan.data.model.PhotoModel;
import ru.ivan.domain.entity.PhotoEntity;
import ru.ivan.domain.repository.PhotoRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PhotoRepositoryImpl implements PhotoRepository {
  private final PhotoConverter converter;

  public PhotoRepositoryImpl(PhotoConverter converter) {this.converter = converter;}

  @Override
  public TreeSet<PhotoEntity> getPhotos(String folderPath) {
    File rootFolder = new File(folderPath);
    File[] folderEntries = rootFolder.listFiles();
    List<PhotoModel> photoModels = new ArrayList<>();
    assert folderEntries != null;
    for (File entry : folderEntries) {

      if (entry.isDirectory()) {
        continue;
      }
      photoModels.add(new PhotoModel(entry.getName(), entry.getPath()));
    }
    TreeSet<PhotoEntity> photoEntities =
            photoModels.stream()
                    .map(converter::convert)
                    .collect(Collectors.toCollection(TreeSet::new));
    return null;
  }
}
