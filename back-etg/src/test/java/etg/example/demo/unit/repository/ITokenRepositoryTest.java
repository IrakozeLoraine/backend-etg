package etg.example.demo.unit.repository;

import etg.example.demo.enums.ETokenStatus;
import etg.example.demo.models.Meter;
import etg.example.demo.models.Token;
import etg.example.demo.repository.IMeterRepository;
import etg.example.demo.repository.ITokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ITokenRepositoryTest {
    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired IMeterRepository meterRepository;

    @Test
    public void save_success(){
        Meter newMeter = meterRepository.save(new Meter(12l,123450));
        Token newToken = new Token(12l,12345678, 7, 5000,ETokenStatus.USED, newMeter);
        Token savedToken = tokenRepository.save(newToken);

        assertEquals(12l, newToken.getId());
    }
}
