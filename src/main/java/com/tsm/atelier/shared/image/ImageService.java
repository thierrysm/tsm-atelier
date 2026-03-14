package com.tsm.atelier.shared.image;

import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.shared.StorageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final StorageService storageService;

  public String upload(MultipartFile file, ImageFolder folder) {
    validateFile(file);
    return storageService.upload(file, folder.getPath());
  }

  public void delete(String url) {
    storageService.delete(url);
  }

  private void validateFile(MultipartFile file) {
    List<String> allowed = List.of("image/jpeg", "image/png", "image/webp");

    if (!allowed.contains(file.getContentType())) {
      throw new BusinessException("File type not allowed. Accepted: jpeg, png, webp");
    }

    if (file.getSize() > 5 * 1024 * 1024) {
      throw new BusinessException("File size exceeds 5MB limit");
    }
  }
}
