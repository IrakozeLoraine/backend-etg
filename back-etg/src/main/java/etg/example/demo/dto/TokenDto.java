package etg.example.demo.dto;

import lombok.Data;

@Data
public class TokenDto {
    private Number amount;

    private Number meter;

    private boolean isTokenStatus;
}
