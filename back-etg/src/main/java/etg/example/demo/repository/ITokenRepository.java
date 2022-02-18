package etg.example.demo.repository;

import etg.example.demo.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITokenRepository extends JpaRepository<Token, Integer> {
    List<Token> findByTokenStatusAndMeter(boolean tokenStatus, Number meter);
}
