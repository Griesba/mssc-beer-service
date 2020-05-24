package guru.springframework.msscbeerservice.events;


import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
@Builder
public class BeerEvent implements Serializable {

    private static  final long serialVersionUID = -1221301598159835088L;

    private final BeerDto beerDto;
}
