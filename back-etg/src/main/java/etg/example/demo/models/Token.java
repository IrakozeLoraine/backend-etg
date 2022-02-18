package etg.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tokens")
public class Token {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Number amount;

    @Column(name = "meter")
    private Number meter;

    @Column(name = "token_status")
    private boolean tokenStatus;

    @CreatedDate()
    private Date createdAt;
}
