package etg.example.demo.repository;

import etg.example.demo.models.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMeterRepository extends JpaRepository<Meter, Long> {
    Optional<Meter> findByMeterNumber(Integer meterNumber);
}
