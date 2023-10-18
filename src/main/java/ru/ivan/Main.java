package ru.ivan;

import ru.ivan.data.converter.PhotoConverter;
import ru.ivan.data.repository.PhotoRepositoryImpl;
import ru.ivan.domain.usecase.SortPhotosByFolderUseCase;

public class Main {
  private final static SortPhotosByFolderUseCase SORT_PHOTOS_BY_FOLDER_USE_CASE =
          new SortPhotosByFolderUseCase(
          new PhotoRepositoryImpl(new PhotoConverter()));

  public static void main(String[] args) {
    final String rootFolderPath = "C:\\Users\\Acer\\Downloads\\Telegram " +
            "Desktop\\ChatExport_2023-10-18\\photos";
    System.out.println(SORT_PHOTOS_BY_FOLDER_USE_CASE.invoke(rootFolderPath));
  }
}