package etg.example.demo.unit.controllers;

import etg.example.demo.dto.TokenDto;
import etg.example.demo.models.Token;
import etg.example.demo.unit.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.APICustomResponse;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/tokens")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") int id) {

        Optional<Token> token = tokenService.getById(id);

        if (token.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(token.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new APICustomResponse(false, "Book not found"));
    }

    @GetMapping("/all")
    public List<Token> getAll() {
        return tokenService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveItem(@RequestBody TokenDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
