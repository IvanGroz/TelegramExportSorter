package ru.ivan.data.repository;

import ru.ivan.data.converter.PhotoConverter;
import ru.ivan.data.model.PhotoModel;
import ru.ivan.domain.entity.PhotoEntity;
import ru.ivan.domain.repository.PhotoRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PhotoRepositoryImpl implements PhotoRepository {
  private final DateTimeFormatter dateFormatter =
          DateTimeFormatter
                  .ofPattern("dd-MM-uuuu", Locale.US)
                  .withResolverStyle(ResolverStyle.STRICT);
  private final Map<Integer, String> month = Map.ofEntries(Map.entry(1, "1 Январь"),
                                                           Map.entry(2, "2 Февраль"),
                                                           Map.entry(3, "3 Март"),
                                                           Map.entry(4, "4 Апрель"),
                                                           Map.entry(5, "5 Май"),
                                                           Map.entry(6, "6 Июнь"),
                                                           Map.entry(7, "7 Июль"),
                                                           Map.entry(8, "8 Август"),
                                                           Map.entry(9, "9 Сентябрь"),
                                                           Map.entry(10, "10 Октябрь"),
                                                           Map.entry(11, "11 Ноябрь"),
                                                           Map.entry(12, "12 Декабрь")
  );
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
      photoModels.add(new PhotoModel(entry.getName(), entry));
    }
    return photoModels.stream()
                      .map(converter::convert)
                      .collect(Collectors.toCollection(TreeSet::new));
  }

  @Override
  public void sortPhotos(TreeSet<PhotoEntity> photos) {
    for (PhotoEntity photo : photos) {
      String data = extractData(photo.getFileName());
      LocalDate photoDate;
      try {
        TemporalAccessor temporalAccessor = dateFormatter.parse(data);
        photoDate = temporalAccessor.query(LocalDate::from);
      } catch (DateTimeParseException e) {
        continue;
      }

      int m = photoDate.getMonthValue();
      String monthName = month.get(m);
      try {
        Path newPath = new File("C:/Users/Acer/Downloads/Telegram Desktop/Sorted2/" +
                                        photoDate.getYear() +
                                        "/" + monthName +
                                        "/" + photo.getFileName()).toPath();
        Files.createDirectories(newPath);
        Files.move(
                photo.getPhotoFile().toPath(),
                newPath,
                StandardCopyOption.REPLACE_EXISTING
        );
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private String extractData(String photoName) {
    Pattern pattern = Pattern.compile("([0123])\\d-([01])\\d-\\d{4}"); //dd-mm-yyyy
    Matcher matcher = pattern.matcher(photoName);
    if (matcher.find()) {
      return matcher.group();
    }
    return "";
  }
}
