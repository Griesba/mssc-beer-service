package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/api/v1/beer")
@RequestMapping
public class BeerController {
    @GetMapping("/{beerId}")
    public ResponseEntity getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BeerDto beerDto){
        return new ResponseEntity(BeerDto.builder().build(), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity update(@PathVariable UUID beerId, @RequestBody BeerDto beerDto){
        return new ResponseEntity(BeerDto.builder().build(), HttpStatus.NO_CONTENT);
    }
}
