package ru.ivan.data.converter;

import ru.ivan.data.model.PhotoModel;
import ru.ivan.domain.entity.PhotoEntity;

public class PhotoConverter {
  public PhotoEntity convert(PhotoModel photoModel){
    return new PhotoEntity(photoModel.fileName, photoModel.photoFile);
  }
}
