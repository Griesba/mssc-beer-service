package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(BeerController.BASE_URL)
public class BeerController {

    public static final String BASE_URL = "/api/v1/beer";

    @GetMapping("/{beerId}")
    public ResponseEntity getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity(
                BeerDto.builder().id(beerId).beerStyleEnum(BeerStyleEnum.GOSE).build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BeerDto beerDto){
        return new ResponseEntity(BeerDto.builder().id(UUID.randomUUID()).build(), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity update(@PathVariable UUID beerId, @Validated @RequestBody BeerDto beerDto){
        return new ResponseEntity(BeerDto.builder().build(), HttpStatus.NO_CONTENT);
    }
}
