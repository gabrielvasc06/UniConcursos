package com.project.uniconcursos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.datasource.url=jdbc:h2:mem:web-pages-test;DB_CLOSE_DELAY=-1")
class WebPagesTests {

    @Value("${local.server.port}")
    private int port;

    @Test
    void inicioRedirecionaParaDisciplinas() throws Exception {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(
                    HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/")).GET().build(),
                    HttpResponse.BodyHandlers.ofString());

            assertEquals(302, response.statusCode());
            assertEquals("/disciplinas", URI.create(response.headers().firstValue("location").orElseThrow()).getPath());
        }
    }

    @Test
    void paginasRenderizamDadosECss() throws Exception {
        try (HttpClient client = HttpClient.newHttpClient()) {
            for (String path : new String[]{"/disciplinas", "/simulados", "/style.css"}) {
                HttpResponse<String> response = client.send(
                        HttpRequest.newBuilder(URI.create("http://localhost:" + port + path)).GET().build(),
                        HttpResponse.BodyHandlers.ofString());

                assertEquals(200, response.statusCode(), path);
                if (path.endsWith(".css")) {
                    assertTrue(response.body().contains(":root"));
                } else {
                    assertTrue(response.body().contains("Direito Constitucional"), path);
                    assertTrue(response.body().contains("href=\"/style.css\""), path);
                }
            }
        }
    }
}
