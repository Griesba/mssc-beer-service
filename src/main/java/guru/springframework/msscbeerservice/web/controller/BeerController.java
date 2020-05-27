package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.brewery.model.BeerDto;
import guru.brewery.model.BeerPagedList;
import guru.brewery.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static guru.springframework.msscbeerservice.web.controller.Constant.API_V_1;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_V_1)
public class BeerController {

    public static final String BASE_URL = API_V_1;

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(path = "/beer", produces = {"application/json"})
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @GetMapping("/beer/{beerId}")
    public ResponseEntity getBeerById(@PathVariable UUID beerId,
                                      @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
        return new ResponseEntity(
                beerService.getById(beerId, showInventoryOnHand),
                HttpStatus.OK);
    }

    @PostMapping("/beer")
    public ResponseEntity create(@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity(
                beerService.saveNewBeer(beerDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/beer/{beerId}")
    public ResponseEntity update(@PathVariable UUID beerId, @Validated @RequestBody BeerDto beerDto) {
        return new ResponseEntity(beerService.update(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/beerUpc/{upc}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto listBeerByUpc(@PathVariable String upc,
                                 @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        return beerService.getByUpc(upc, showInventoryOnHand);
    }
}
