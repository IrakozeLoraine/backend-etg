package etg.example.demo.unit.controllers;

import etg.example.demo.controllers.TokenController;
import etg.example.demo.dto.ElectricityDto;
import etg.example.demo.enums.ETokenStatus;
import etg.example.demo.models.Meter;
import etg.example.demo.models.Token;
import etg.example.demo.services.TokenService;
import etg.example.demo.utils.ErrorExceptionHandler;
import etg.example.demo.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TokenController.class)
public class TokenControllerTest {
    @MockBean
    private TokenService tokenService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void buy_electricity_success() throws Exception, ErrorExceptionHandler {
        Token token = new Token(12l,12345678, 7, 5000, ETokenStatus.USED, new Meter(12l,123450));
        ElectricityDto electricityDto = new ElectricityDto(5000,12345678);
        when(tokenService.generateToken(electricityDto)).thenReturn(token);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/electricity/buy")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(token));

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void getByMeter_success() throws Exception, ErrorExceptionHandler {
        Token token = new Token(12l,12345678, 7, 5000, ETokenStatus.USED, new Meter(12l,123450));

        when(tokenService.getByMeter(token.getMeter().getId())).thenReturn(token);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/electricity/123450")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
//                .andExpect(content().contentType("{\"id\":12l,\"value\":12345678,\"duration\":7,\"amount\":5000,\"status\":\"USED\",\"meter_id\":123450}"))
                .andReturn();
    }
}
