package etg.example.demo.integration;

import etg.example.demo.dto.ElectricityDto;
import etg.example.demo.enums.ETokenStatus;
import etg.example.demo.models.Meter;
import etg.example.demo.models.Token;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import etg.example.demo.utils.APICustomResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TokenIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void create_success() {
        ElectricityDto request = new ElectricityDto(5000,12345678);
        ResponseEntity<Token> response = this.restTemplate.postForEntity("/api/v1/electricity/buy", request, Token.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(5000, response.getBody().getAmount());
    }
}
