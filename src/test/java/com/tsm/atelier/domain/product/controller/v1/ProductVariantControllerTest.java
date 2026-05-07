package com.tsm.atelier.domain.product.controller.v1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.tsm.atelier.domain.product.ProductSize;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductVariantResponseDTO;
import com.tsm.atelier.domain.product.service.ProductVariantService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductVariantController")
class ProductVariantControllerTest {

  private MockMvc mockMvc;

  @Mock private ProductVariantService productVariantService;

  @InjectMocks private ProductVariantController productVariantController;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new Jdk8Module());
    mockMvc =
        MockMvcBuilders.standaloneSetup(productVariantController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Nested
  @DisplayName("Add Variant")
  class AddVariant {

    @Test
    @DisplayName("Should add variant successfully and return 201")
    void shouldAddVariantSuccessfully() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      ProductVariantRequestDTO request = new ProductVariantRequestDTO(ProductSize.M, 10);
      ProductVariantResponseDTO response = new ProductVariantResponseDTO(1L, ProductSize.M, 10);

      when(productVariantService.addVariant(
              eq(productId), eq(colorId), any(ProductVariantRequestDTO.class)))
          .thenReturn(response);

      mockMvc
          .perform(
              post("/api/v1/products/{productId}/colors/{colorId}/variants", productId, colorId)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(header().exists("Location"))
          .andExpect(jsonPath("$.id").value(1L))
          .andExpect(jsonPath("$.size").value("M"))
          .andExpect(jsonPath("$.stock").value(10));

      verify(productVariantService)
          .addVariant(eq(productId), eq(colorId), any(ProductVariantRequestDTO.class));
    }
  }

  @Nested
  @DisplayName("Update Stock")
  class UpdateStock {

    @Test
    @DisplayName("Should update stock successfully and return 200")
    void shouldUpdateStockSuccessfully() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;
      ProductVariantPatchDTO request = new ProductVariantPatchDTO(20);
      ProductVariantResponseDTO response =
          new ProductVariantResponseDTO(variantId, ProductSize.M, 20);

      when(productVariantService.updateStock(
              eq(productId), eq(colorId), eq(variantId), any(ProductVariantPatchDTO.class)))
          .thenReturn(response);

      mockMvc
          .perform(
              patch(
                      "/api/v1/products/{productId}/colors/{colorId}/variants/{variantId}/stock",
                      productId,
                      colorId,
                      variantId)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.stock").value(20));

      verify(productVariantService)
          .updateStock(
              eq(productId), eq(colorId), eq(variantId), any(ProductVariantPatchDTO.class));
    }
  }

  @Nested
  @DisplayName("Delete Variant")
  class DeleteVariant {

    @Test
    @DisplayName("Should delete variant successfully and return 204")
    void shouldDeleteVariantSuccessfully() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;

      doNothing().when(productVariantService).delete(productId, colorId, variantId);

      mockMvc
          .perform(
              delete(
                  "/api/v1/products/{productId}/colors/{colorId}/variants/{variantId}",
                  productId,
                  colorId,
                  variantId))
          .andExpect(status().isNoContent());

      verify(productVariantService).delete(productId, colorId, variantId);
    }

    @Test
    @DisplayName("Should return 404 when variant not found")
    void shouldReturn404WhenVariantNotFound() throws Exception {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;

      doThrow(new EntityNotFoundException("Variante do produto", "id", variantId))
          .when(productVariantService)
          .delete(productId, colorId, variantId);

      mockMvc
          .perform(
              delete(
                  "/api/v1/products/{productId}/colors/{colorId}/variants/{variantId}",
                  productId,
                  colorId,
                  variantId))
          .andExpect(status().isNotFound());

      verify(productVariantService).delete(productId, colorId, variantId);
    }
  }
}
