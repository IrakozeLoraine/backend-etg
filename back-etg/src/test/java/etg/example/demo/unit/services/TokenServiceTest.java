package etg.example.demo.unit.services;

import etg.example.demo.dto.ElectricityDto;
import etg.example.demo.enums.ETokenStatus;
import etg.example.demo.models.Meter;
import etg.example.demo.models.Token;
import etg.example.demo.repository.IMeterRepository;
import etg.example.demo.repository.ITokenRepository;
import etg.example.demo.services.TokenService;
import etg.example.demo.utils.ErrorExceptionHandler;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.then;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {
    @Mock
    private ITokenRepository tokenRepository;

    @Captor
    private ArgumentCaptor<Token> tokenArgumentCaptor;

    @InjectMocks
    private TokenService tokenService;

    @Test
    public void generate_token_successfully() throws ErrorExceptionHandler {
        Integer amount = 2000;
        Integer meter = 123456;

        ElectricityDto dto = new ElectricityDto(amount, meter);

        Token generatedToken = tokenService.generateToken(dto);
        then(tokenRepository).should().save(tokenArgumentCaptor.capture());
        Token tokenArgumentCaptorValue = tokenArgumentCaptor.getValue();
        assertThat(tokenArgumentCaptorValue).isEqualTo(generatedToken);
    }
    @Test
    public void getByMeter_success() throws ErrorExceptionHandler{
        Token token = new Token(12l,12345678, 7, 5000, ETokenStatus.USED, new Meter(12l,123450));
        token.setId(1l);

        when(tokenRepository.findTokenByMeter_Id(token.getMeter().getId())).thenReturn(Optional.of(token));

        Token expected = tokenService.getByMeter(token.getId());


        AssertionsForClassTypes.assertThat(expected).isSameAs(token);

        verify(tokenRepository).findById(token.getId());
    }
}
