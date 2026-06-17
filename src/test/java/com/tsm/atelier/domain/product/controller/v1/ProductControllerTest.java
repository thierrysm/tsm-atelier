package com.tsm.atelier.domain.product.controller.v1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.tsm.atelier.domain.product.ProductCategory;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.TargetAudience;
import com.tsm.atelier.domain.product.dto.v1.request.ProductFilterDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.domain.product.service.ProductService;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.exception.handler.GlobalExceptionHandler;
import com.tsm.atelier.factory.ProductTestFactory;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductController")
class ProductControllerTest {

  private MockMvc mockMvc;

  @Mock private ProductService productService;

  @InjectMocks private ProductController productController;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new Jdk8Module()); // For Optional support in ProductPatchDTO
    mockMvc =
        MockMvcBuilders.standaloneSetup(productController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  private ProductDetailsResponseDTO mockProductDetailsActive() {
    return new ProductDetailsResponseDTO(
        1L,
        "Vestido Linho",
        "vestido-linho",
        "Descricao",
        BigDecimal.valueOf(299.90),
        null,
        ProductStatus.DRAFT,
        List.of(),
        ProductCategory.DRESS,
        TargetAudience.WOMENSWEAR,
        "Collection Primavera",
        List.of(),
        List.of());
  }

  private ProductDetailsResponseDTO mockProductDetailsDraft() {
    return new ProductDetailsResponseDTO(
        1L,
        "Vestido Linho",
        "vestido-linho",
        "Descricao",
        BigDecimal.valueOf(299.90),
        null,
        ProductStatus.DRAFT,
        List.of(),
        ProductCategory.DRESS,
        TargetAudience.WOMENSWEAR,
        "Collection Primavera",
        List.of(),
        List.of());
  }

  @Nested
  @DisplayName("Find All")
  class FindAll {

    @Test
    @DisplayName("Should return page of filtered products")
    void shouldReturnPageOfFilteredProducts() throws Exception {
      ProductSummaryResponseDTO summary =
          ProductSummaryResponseDTO.builder()
              .id(1L)
              .name("Vestido Linho")
              .price(BigDecimal.valueOf(299.90))
              .build();

      Pageable pageable = PageRequest.of(0, 10);

      Page<ProductSummaryResponseDTO> page = new PageImpl<>(List.of(summary), pageable, 1);

      when(productService.findAllFiltered(any(ProductFilterDTO.class), any(Pageable.class)))
          .thenReturn(page);

      mockMvc
          .perform(get("/api/v1/products").param("page", "0").param("size", "10"))
          .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.content[0].name").value("Vestido Linho"));

      verify(productService).findAllFiltered(any(ProductFilterDTO.class), any(Pageable.class));
    }
  }

  @Nested
  @DisplayName("Find By Id")
  class FindById {

    @Test
    @DisplayName("Should return product details")
    void shouldReturnProductDetails() throws Exception {
      ProductDetailsResponseDTO details = mockProductDetailsActive();
      when(productService.findProductDetailsById(1L)).thenReturn(details);

      mockMvc
          .perform(get("/api/v1/products/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name").value("Vestido Linho"));

      verify(productService).findProductDetailsById(1L);
    }

    @Test
    @DisplayName("Should return 404 when product not found")
    void shouldReturn404WhenNotFound() throws Exception {
      when(productService.findProductDetailsById(1L))
          .thenThrow(new EntityNotFoundException("Produto", "id", 1L));

      mockMvc.perform(get("/api/v1/products/1")).andExpect(status().isNotFound());

      verify(productService).findProductDetailsById(1L);
    }
  }

  @Nested
  @DisplayName("Find By Slug")
  class FindBySlug {

    @Test
    @DisplayName("Should return product details by slug")
    void shouldReturnProductDetailsBySlug() throws Exception {
      ProductDetailsResponseDTO details = mockProductDetailsActive();
      when(productService.findProductDetailsBySlug("vestido-linho")).thenReturn(details);

      mockMvc
          .perform(get("/api/v1/products/slug/vestido-linho"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name").value("Vestido Linho"));

      verify(productService).findProductDetailsBySlug("vestido-linho");
    }

    @Test
    @DisplayName("Should return 404 when product not found by slug")
    void shouldReturn404WhenNotFound() throws Exception {
      String slug = "vestido-linho";
      when(productService.findProductDetailsBySlug(slug))
          .thenThrow(new EntityNotFoundException("Produto", "slug", slug));

      mockMvc.perform(get("/api/v1/products/slug/" + slug)).andExpect(status().isNotFound());

      verify(productService).findProductDetailsBySlug(slug);
    }
  }

  @Nested
  @DisplayName("Create Product")
  class Create {

    @Test
    @DisplayName("Should create product and return 201")
    void shouldCreateProduct() throws Exception {
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();
      ProductDetailsResponseDTO response = mockProductDetailsActive();

      when(productService.create(any(ProductRequestDTO.class))).thenReturn(response);

      mockMvc
          .perform(
              post("/api/v1/products")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(header().exists("Location"))
          .andExpect(jsonPath("$.id").value(1L));

      verify(productService).create(any(ProductRequestDTO.class));
    }
  }

  @Nested
  @DisplayName("Update Product")
  class Update {

    @Test
    @DisplayName("Should update product partially")
    void shouldUpdateProductPartially() throws Exception {
      ProductPatchDTO request = ProductTestFactory.aProductPatch();
      ProductDetailsResponseDTO response = mockProductDetailsActive();

      when(productService.partialUpdate(eq(1L), any(ProductPatchDTO.class))).thenReturn(response);

      mockMvc
          .perform(
              patch("/api/v1/products/1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(1L));

      verify(productService).partialUpdate(eq(1L), any(ProductPatchDTO.class));
    }
  }

  @Nested
  @DisplayName("State Transitions")
  class StateTransitions {

    @Test
    @DisplayName("Should publish product")
    void shouldPublishProduct() throws Exception {
      Long productId = 1L;
      ProductDetailsResponseDTO response = mockProductDetailsDraft();

      when(productService.publish(productId)).thenReturn(response);

      mockMvc.perform(patch("/api/v1/products/1/publish")).andExpect(status().isOk());

      verify(productService).publish(1L);
    }

    @Test
    @DisplayName("Should archive product")
    void shouldArchiveProduct() throws Exception {
      Long productId = 1L;
      ProductDetailsResponseDTO response = mockProductDetailsActive();

      when(productService.archive(productId)).thenReturn(response);

      mockMvc.perform(patch("/api/v1/products/1/archive")).andExpect(status().isOk());

      verify(productService).archive(1L);
    }
  }
}
