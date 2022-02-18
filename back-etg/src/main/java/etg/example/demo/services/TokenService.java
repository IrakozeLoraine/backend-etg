package etg.example.demo.services;

import etg.example.demo.dto.ElectricityDto;
import etg.example.demo.enums.ETokenStatus;
import etg.example.demo.models.Meter;
import etg.example.demo.models.Token;
import etg.example.demo.repository.IMeterRepository;
import etg.example.demo.repository.ITokenRepository;
import etg.example.demo.utils.ErrorExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TokenService {


    IMeterRepository meterRepository;

    ITokenRepository tokenRepository;

    public TokenService(@Lazy IMeterRepository meterRepository, @Lazy ITokenRepository tokenRepository) {
        this.meterRepository = meterRepository;
        this.tokenRepository = tokenRepository;
    }

    public Token generateToken(ElectricityDto dto) throws ErrorExceptionHandler {

        if(String.valueOf(dto.getMeter()).length() != 6){
            throw new ErrorExceptionHandler("Invalid meter, only 6 digits accepted", HttpStatus.BAD_REQUEST);
        }

        Optional<Meter> foundMeter = meterRepository.findByMeterNumber(dto.getMeter());
        if(foundMeter.isEmpty()){
            throw new ErrorExceptionHandler("Meter number is not found", HttpStatus.BAD_REQUEST);
        }

        if((dto.getAmount() % 100 != 0) || (dto.getAmount() < 100) || (dto.getAmount() > 182500 )){
            throw new ErrorExceptionHandler("invalid amount, only\n" +
                    "multiples of 100 not greater than 182,500 is valid", HttpStatus.BAD_REQUEST);
        }

        //Generate random Token
        //Re-Generate if it already exists
        Random random = new Random();
        Integer randomToken;
        do {
            randomToken = random.nextInt(99999999 - 10000000) + 10000000;
        } while (tokenRepository.existsByValue(randomToken));

        Integer duration = dto.getAmount() / 100;
        Token generatedToken = new Token();
        generatedToken.setValue(randomToken);
        generatedToken.setAmount(dto.getAmount());
        generatedToken.setDuration(duration);
        generatedToken.setMeter(foundMeter.get());
        generatedToken.setStatus(ETokenStatus.USED);

        //Save Token
        return tokenRepository.save(generatedToken);
    }

    public Token getByMeter(long id) throws ErrorExceptionHandler {
        Optional<Token> findById = tokenRepository.findTokenByMeter_Id(id);
        if (findById.isPresent()) {
            Token token = findById.get();
            return token;
        }
        throw new ErrorExceptionHandler("Token with this id not found", HttpStatus.NOT_FOUND);
    }

    public Optional<Meter> findMeterByMeterNumber(int meter_number) {
        return meterRepository.findByMeterNumber(meter_number);
    }
}
