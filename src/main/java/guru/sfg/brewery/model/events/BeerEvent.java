package guru.sfg.brewery.model.events;


import guru.sfg.brewery.model.BeerDto;
import lombok.*;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -1221301598159835088L;

    private BeerDto beerDto;
}
