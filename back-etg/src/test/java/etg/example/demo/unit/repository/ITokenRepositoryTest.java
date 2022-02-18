package etg.example.demo.unit.repository;

import etg.example.demo.models.Token;
import etg.example.demo.repository.ITokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ITokenRepositoryTest {
    @Autowired
    private ITokenRepository tokenRepository;

    @Test
    public void findAll_success () {
        List<Token> books = tokenRepository.findAll();
        assertEquals(2, books.size());
    }

    @Test
    public void findOne_success() {
        Optional<Token> token = tokenRepository.findById(1);
        assertTrue(token.isPresent());
    }

    @Test
    public void findOne_fail() {
        Optional<Token> token = tokenRepository.findById(003);
        assertEquals(false, token.isPresent());
    }

    @Test
    public void save_success(){
        Token newToken = new Token(50000,002005,true);
        tokenRepository.save(newToken);

        Optional<Token> foundToken = tokenRepository.findById(newToken.getId());
        assertTrue(foundToken.isPresent());
    }

    @Test
    public void remove_success(){
        Optional<Token> token = tokenRepository.findById(002);
        if (token.isPresent()) tokenRepository.deleteById(002);

        assertEquals(true, token.isPresent());
    }

    @Test
    public void remove_fail(){
        Optional<Token> token = tokenRepository.findById(003);
        if (token.isPresent()) tokenRepository.deleteById(003);

        assertEquals(false, token.isPresent());
    }

    @Test
    public void findByTokenStatusAndMeter_success() {
        List<Token> book = tokenRepository.findByTokenStatusAndMeter(true, 100040);
        assertEquals(1, book.size());
    }

}
