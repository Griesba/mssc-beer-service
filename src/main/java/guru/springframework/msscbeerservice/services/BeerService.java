package guru.springframework.msscbeerservice.services;

import guru.brewery.model.BeerDto;
import guru.brewery.model.BeerPagedList;
import guru.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto update(UUID beerId, BeerDto beerDto);

    List<BeerDto> findAll();

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getByUpc(String beerUpc, Boolean showInventoryOnHand);
}
