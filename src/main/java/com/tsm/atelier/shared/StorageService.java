package com.tsm.atelier.shared;

import com.tsm.atelier.config.StorageProperties;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.shared.image.ImageFolder;
import java.io.IOException;
import java.text.Normalizer;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class StorageService {

  private static final Logger log = LoggerFactory.getLogger(StorageService.class);

  private final S3Client s3Client;
  private final StorageProperties properties;

  public UploadResult upload(MultipartFile file, ImageFolder folder) {
    String sanitizedFileName = generateSafeAndUniqueFileName(file.getOriginalFilename());
    String key = folder.getPath() + "/" + sanitizedFileName;

    log.debug("Uploading file with key: {}", key);

    try {
      s3Client.putObject(
          PutObjectRequest.builder()
              .bucket(properties.bucket())
              .key(key)
              .contentType(file.getContentType())
              .build(),
          RequestBody.fromBytes(file.getBytes()));
    } catch (IOException ex) {
      throw new BusinessException("Falha ao fazer upload do arquivo: " + ex.getMessage());
    }

    return new UploadResult(properties.baseUrl() + "/" + key, sanitizedFileName);
  }

  public void delete(String url) {
    if (url == null || url.isBlank()) return;

    String baseUrlWithSlash = properties.baseUrl() + "/";
    String key;

    if (url.startsWith(baseUrlWithSlash)) {
      key = url.replace(baseUrlWithSlash, "");
    } else if (url.startsWith("http")) {
      return;
    } else {
      key = url.startsWith("/") ? url.substring(1) : url;
    }

    try {
      s3Client.deleteObject(
          DeleteObjectRequest.builder().bucket(properties.bucket()).key(key).build());
    } catch (Exception e) {
      log.error("Erro ao deletar objeto no S3 com key {}: {}", key, e.getMessage());
    }
  }

  private String generateSafeAndUniqueFileName(String originalFileName) {
    if (originalFileName == null || originalFileName.isBlank()) {
      return UUID.randomUUID() + ".jpg";
    }

    String extension = StringUtils.getFilenameExtension(originalFileName);
    String nameOnly = StringUtils.stripFilenameExtension(originalFileName);

    if (nameOnly == null) nameOnly = "file";

    String sanitized =
        Normalizer.normalize(nameOnly, Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}", "")
            .toLowerCase()
            .trim()
            .replaceAll("[^a-z0-9\\-_]", "-")
            .replaceAll("-+", "-");

    if (sanitized.length() > 60) sanitized = sanitized.substring(0, 60);
    if (sanitized.endsWith("-")) sanitized = sanitized.substring(0, sanitized.length() - 1);

    String shortId = UUID.randomUUID().toString().substring(0, 8);

    return sanitized + "-" + shortId + (extension != null ? "." + extension : "");
  }
}
