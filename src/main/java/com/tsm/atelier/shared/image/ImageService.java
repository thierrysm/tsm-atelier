package com.tsm.atelier.shared.image;

import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.shared.StorageService;
import com.tsm.atelier.shared.UploadResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

  private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
  private static final List<String> ALLOWED_TYPES =
      List.of("image/jpeg", "image/png", "image/webp");

  private final StorageService storageService;

  public UploadResult upload(MultipartFile file, ImageFolder folder) {
    validateFile(file);
    return storageService.upload(file, folder);
  }

  public void delete(String url) {
    storageService.delete(url);
  }

  private void validateFile(MultipartFile file) {
    if (file == null || file.isEmpty()) {
      throw new BusinessException("Arquivo não pode ser vazio");
    }

    if (file.getContentType() == null || !ALLOWED_TYPES.contains(file.getContentType())) {
      throw new BusinessException("Tipo de arquivo não permitido. Aceitos: jpeg, png, webp");
    }

    if (file.getSize() > MAX_FILE_SIZE) {
      throw new BusinessException("Tamanho máximo permitido é 5MB");
    }
  }
}
