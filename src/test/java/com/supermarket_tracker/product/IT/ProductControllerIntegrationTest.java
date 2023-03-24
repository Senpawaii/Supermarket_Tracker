package com.supermarket_tracker.product.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarket_tracker.product.Product;
import com.supermarket_tracker.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createProduct_ReturnValidCreatedProduct() throws Exception {
        // Given
        Product product = new Product("Test Product", "Test Brand", 10.0, "Test Category");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        // When
        MvcResult mvcResult = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        String responseJson = mvcResult.getResponse().getContentAsString();
        Product createdProduct = objectMapper.readValue(responseJson, Product.class);

        assertEquals(product.getName(), createdProduct.getName());
        assertEquals(product.getBrand(), createdProduct.getBrand());
        assertEquals(product.getCategory(), createdProduct.getCategory());
        assertEquals(product.getPrice(), createdProduct.getPrice());
    }
}