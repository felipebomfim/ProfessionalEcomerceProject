package com.ecommerce.project.payload.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.project.model.Category;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import jakarta.persistence.EntityManager;
import net.minidev.json.JSONArray;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    EntityManager entityManager;

    @Test
    void sanityCheckDataLoaded() {
        List<Category> all = entityManager.createQuery("from Category", Category.class).getResultList();
        System.out.println("Categorias carregadas no banco: " + all.size());
    }

    @Test
    void deveRetornarTodasAsCategoriasQuandoFeitaRequisicao() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/public/categories", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(response.getBody());
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int cashCardCount = documentContext.read("$.content.length()");
        assertThat(cashCardCount).isEqualTo(4);

        JSONArray ids = documentContext.read("$.content..categoryId");
        assertThat(ids).containsExactlyInAnyOrder(1, 2, 3, 4);

        JSONArray amounts = documentContext.read("$.content..categoryName");
        assertThat(amounts).containsExactlyInAnyOrder(
                "Alimentos",
                "Eletrônicos",
                "Livros",
                "Roupas");
    }
}
