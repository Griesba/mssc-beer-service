package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
