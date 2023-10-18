package ru.ivan.domain.entity;

import ru.ivan.data.model.PhotoModel;

import java.io.File;

public class PhotoEntity implements Comparable<PhotoEntity>{
  private final String fileName;
  private final File photoFile;

  public PhotoEntity(String fileName, File photoFile) {
    this.fileName = fileName;
    this.photoFile = photoFile;
  }
  @Override
  public int compareTo(PhotoEntity photoModel2) {
    return this.fileName.compareTo(photoModel2.fileName);
  }

  public File getPhotoFile() {
    return photoFile;
  }

  public String getFileName() {
    return fileName;
  }
}
