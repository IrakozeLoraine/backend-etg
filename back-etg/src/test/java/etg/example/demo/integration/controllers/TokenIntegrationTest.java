package etg.example.demo.integration.controllers;

import etg.example.demo.models.Token;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import utils.APICustomResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TokenIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_success() throws JSONException {
        String response = this.restTemplate.getForObject("/", String.class);

        JSONAssert.assertEquals("[{id:001}, {id:002}]", response, false);
    }
    @Test
    public void getById_success() {
        ResponseEntity<Token> response = this.restTemplate.getForEntity("/1", Token.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2003, response.getBody().getAmount());
        assertEquals(500009, response.getBody().getMeter());
    }

    @Test
    public void getById_404() {
        ResponseEntity<APICustomResponse> response = this.restTemplate.getForEntity("/1", APICustomResponse.class);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Token not found", response.getBody().getMessage());
    }

    @Test
    public void getByMeter_success() {
        ResponseEntity<Token> response = this.restTemplate.getForEntity("/by-meter/500009", Token.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(5000, response.getBody().getAmount());
    }

    @Test
    public void getByMeter_404() {
        ResponseEntity<APICustomResponse> response = this.restTemplate.getForEntity("/by-meter/2000", APICustomResponse.class);
        assertEquals("Token not found", response.getBody().getMessage());
    }

    @Test
    public void create_success() {
        Token request = new Token(5000, 500009, true);
        ResponseEntity<Token> response = this.restTemplate.postForEntity("/", request, Token.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(5000, response.getBody().getAmount());
    }

    @Test
    public void delete_success() {
        ResponseEntity<String> response = this.restTemplate.exchange("/002", HttpMethod.DELETE, null, String.class);
        assertEquals(202, response.getStatusCodeValue());
        assertEquals("Removed", response.getBody());
    }
}
