package etg.example.demo.controllers;

import etg.example.demo.dto.ElectricityDto;
import etg.example.demo.enums.ETokenStatus;
import etg.example.demo.models.Meter;
import etg.example.demo.models.Token;
import etg.example.demo.repository.IMeterRepository;
import etg.example.demo.services.TokenService;
import etg.example.demo.utils.ErrorExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import etg.example.demo.utils.APICustomResponse;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/electricity")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/buy")
    public ResponseEntity<APICustomResponse> buyElectricity(@RequestBody ElectricityDto dto) throws ErrorExceptionHandler {
        return ResponseEntity.status(HttpStatus.OK).body(new APICustomResponse(true, "Electricity generated successfully", tokenService.generateToken(dto) ));
    }

    @GetMapping("/meter/{meter_number}")
    public ResponseEntity<?> getByMeter(@PathVariable(name = "meter_number") int meter_number) {
        Optional<Meter> meter = tokenService.findMeterByMeterNumber(meter_number);
        return ResponseEntity.status(HttpStatus.OK).body(meter);
    }

    @GetMapping("/{meter}")
    public ResponseEntity<?> getByMeterStatus(@PathVariable(name = "meter") long meter_id) throws ErrorExceptionHandler {
        Token token = tokenService.getByMeter(meter_id);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
