package etg.example.demo.services;

import etg.example.demo.dto.TokenDto;
import etg.example.demo.models.Token;
import etg.example.demo.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ExpiredTokenChecker;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {
    @Autowired
    private ITokenRepository tokenRepository;

    public Optional<Token> getById(long id) {

        java.util.Optional<Token> token =  tokenRepository.findById(id);

        return token;
    }

    public List<Token> getAll() {

        return tokenRepository.findAll();
    }

    public List<Token> getByTokenStatusAndMeter(boolean tokenStatus, int meter) {
        return tokenRepository.findByTokenStatusAndMeter(tokenStatus, meter);
    }


    public Token save(TokenDto dto) {
        Token token =  new Token();
        token.setAmount(dto.getAmount());
        token.setMeter(dto.getMeter());
        token.setTokenStatus(dto.isTokenStatus());
        return tokenRepository.save(token);
    }

    public void delete(Token token){
        ExpiredTokenChecker tokenChecker = new ExpiredTokenChecker();
        if(tokenChecker.hasExpired(token.getCreatedAt())){
            tokenRepository.deleteById(token.getId());
        }
    }
}
