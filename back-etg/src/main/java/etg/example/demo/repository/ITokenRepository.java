package etg.example.demo.repository;

import etg.example.demo.enums.ETokenStatus;
import etg.example.demo.models.Meter;
import etg.example.demo.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITokenRepository extends JpaRepository<Token, Long> {
    boolean existsByValue(Integer value);

    Optional<Token> findTokenByMeter_Id(Long meter_id);
}