package etg.example.demo.unit.repository;

import etg.example.demo.models.Meter;
import etg.example.demo.repository.IMeterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IMeterRepositoryTest {
    @Autowired
    private IMeterRepository meterRepository;

    @Test
    public void save_success(){
        Meter newMeter = new Meter(12l,12345678);
        Meter savedMeter = meterRepository.save(newMeter);

        assertThat(savedMeter.getMeterNumber()).isEqualTo(newMeter.getMeterNumber());
    }
}
