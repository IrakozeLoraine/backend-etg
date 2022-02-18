package etg.example.demo.models;

import etg.example.demo.enums.ETokenStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @Max(8)
//    @Min(8)
    @Column(name = "value", nullable = false, unique = true)
    private Integer value;

    @Column(name = "duration", nullable = false, unique = true)
    private Integer duration;

    @Column(name = "amount")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ETokenStatus status = ETokenStatus.USED;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;

}
