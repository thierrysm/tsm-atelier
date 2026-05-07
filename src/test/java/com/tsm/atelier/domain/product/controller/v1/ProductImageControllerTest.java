package com.tsm.atelier.domain.product.controller.v1;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tsm.atelier.domain.product.dto.v1.response.ProductImageResponseDTO;
import com.tsm.atelier.domain.product.service.ProductImageService;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.exception.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductImageController")
class ProductImageControllerTest {

  private MockMvc mockMvc;

  @Mock private ProductImageService productImageService;

  @InjectMocks private ProductImageController productImageController;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(productImageController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Nested
  @DisplayName("Upload Image")
  class UploadImage {

    @Test
    @DisplayName("Should upload image successfully and return 201")
    void shouldUploadImageSuccessfully() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      MockMultipartFile file =
          new MockMultipartFile("file", "test.jpg", "image/jpeg", "data".getBytes());
      ProductImageResponseDTO response =
          new ProductImageResponseDTO(1L, "url", "test.jpg", 1, true);

      when(productImageService.upload(
              eq(productId), eq(colorId), org.mockito.ArgumentMatchers.anyList(), eq(true)))
          .thenReturn(java.util.List.of(response));

      mockMvc
          .perform(
              multipart("/api/v1/products/{productId}/colors/{colorId}/images", productId, colorId)
                  .file(file)
                  .param("isCover", "true")
                  .contentType(MediaType.MULTIPART_FORM_DATA))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$[0].id").value(1L))
          .andExpect(jsonPath("$[0].url").value("url"))
          .andExpect(jsonPath("$[0].isCover").value(true));

      verify(productImageService)
          .upload(eq(productId), eq(colorId), org.mockito.ArgumentMatchers.anyList(), eq(true));
    }

    @Test
    @DisplayName("Should return 404 when color not found during upload")
    void shouldReturn404WhenColorNotFound() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      MockMultipartFile file =
          new MockMultipartFile("file", "test.jpg", "image/jpeg", "data".getBytes());

      when(productImageService.upload(
              eq(productId), eq(colorId), org.mockito.ArgumentMatchers.anyList(), eq(false)))
          .thenThrow(new EntityNotFoundException("Cor do produto", "id", colorId));

      mockMvc
          .perform(
              multipart("/api/v1/products/{productId}/colors/{colorId}/images", productId, colorId)
                  .file(file)
                  .param("isCover", "false")
                  .contentType(MediaType.MULTIPART_FORM_DATA))
          .andExpect(status().isNotFound());

      verify(productImageService)
          .upload(eq(productId), eq(colorId), org.mockito.ArgumentMatchers.anyList(), eq(false));
    }
  }

  @Nested
  @DisplayName("Set Cover")
  class SetCover {

    @Test
    @DisplayName("Should set image as cover successfully and return 204")
    void shouldSetCoverSuccessfully() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      doNothing().when(productImageService).setCover(productId, colorId, imageId);

      mockMvc
          .perform(
              patch(
                  "/api/v1/products/{productId}/colors/{colorId}/images/{imageId}/cover",
                  productId,
                  colorId,
                  imageId))
          .andExpect(status().isNoContent());

      verify(productImageService).setCover(productId, colorId, imageId);
    }

    @Test
    @DisplayName("Should return 400 when business exception occurs during set cover")
    void shouldReturn400WhenBusinessException() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      doThrow(new BusinessException("A cor do produto não pertence a este produto"))
          .when(productImageService)
          .setCover(productId, colorId, imageId);

      mockMvc
          .perform(
              patch(
                  "/api/v1/products/{productId}/colors/{colorId}/images/{imageId}/cover",
                  productId,
                  colorId,
                  imageId))
          .andExpect(status().isBadRequest());

      verify(productImageService).setCover(productId, colorId, imageId);
    }
  }

  @Nested
  @DisplayName("Delete Image")
  class DeleteImage {

    @Test
    @DisplayName("Should delete image successfully and return 204")
    void shouldDeleteImageSuccessfully() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      doNothing().when(productImageService).delete(productId, colorId, imageId);

      mockMvc
          .perform(
              delete(
                  "/api/v1/products/{productId}/colors/{colorId}/images/{imageId}",
                  productId,
                  colorId,
                  imageId))
          .andExpect(status().isNoContent());

      verify(productImageService).delete(productId, colorId, imageId);
    }

    @Test
    @DisplayName("Should return 404 when image not found during deletion")
    void shouldReturn404WhenImageNotFound() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      doThrow(new EntityNotFoundException("Imagem do produto", "id", imageId))
          .when(productImageService)
          .delete(productId, colorId, imageId);

      mockMvc
          .perform(
              delete(
                  "/api/v1/products/{productId}/colors/{colorId}/images/{imageId}",
                  productId,
                  colorId,
                  imageId))
          .andExpect(status().isNotFound());

      verify(productImageService).delete(productId, colorId, imageId);
    }
  }
}
