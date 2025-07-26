package com.ecommerce.project.payload.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
@JsonTest
public class CategoryJsonTest {
    @Autowired
    private JacksonTester<CategoryResponseDTO> json;

    @Test
    void categoryResponseSerializationTest() throws IOException {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(
            21l,
            "My Category"
        );
        assertThat(json.write(categoryResponseDTO)).isStrictlyEqualToJson("category.json");
        // assertThat(json.write(categoryResponseDTO)).hasJsonPathNumberValue("@.categoryId");
        // assertThat(json.write(categoryResponseDTO)).extractingJsonPathNumberValue("@.categoryId")
        //         .isEqualTo(21);
        // assertThat(json.write(categoryResponseDTO)).hasJsonPathStringValue("categoryName");
        // assertThat(json.write(categoryResponseDTO)).extractingJsonPathStringValue("@.categoryName")
        //         .isEqualTo("My Category");
    }

    @Test
    void categoryResponseDeserializationTest() throws IOException {
        String expectedResponse = """
                {
                    "categoryId": 21,
                    "categoryName": "My Category"
                }
                """;
        assertThat(json.parse(expectedResponse)).isEqualTo(new CategoryResponseDTO(21l, "My Category"));
        // assertThat(json.parseObject(expectedResponse).getCategoryId()).isEqualTo(21l);
        // assertThat(json.parseObject(expectedResponse).getCategoryName()).isEqualTo("My Category");
    }
}
