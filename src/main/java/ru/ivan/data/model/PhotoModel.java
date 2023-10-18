package ru.ivan.data.model;

import java.io.File;

public class PhotoModel {
  public String fileName;
  public File photoFile;

  public PhotoModel(String fileName, File photoFile) {
    this.fileName = fileName;
    this.photoFile = photoFile;
  }
}
