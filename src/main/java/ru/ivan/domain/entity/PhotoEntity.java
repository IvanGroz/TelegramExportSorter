package ru.ivan.domain.entity;

import ru.ivan.data.model.PhotoModel;

public class PhotoEntity implements Comparable<PhotoEntity>{
  private final String fileName;
  private final String filePath;

  public PhotoEntity(String fileName, String filePath) {
    this.fileName = fileName;
    this.filePath = filePath;
  }
  @Override
  public int compareTo(PhotoEntity photoModel2) {
    return this.fileName.compareTo(photoModel2.fileName);
  }
  public String getFilePath() {
    return filePath;
  }

  public String getFileName() {
    return fileName;
  }
}
