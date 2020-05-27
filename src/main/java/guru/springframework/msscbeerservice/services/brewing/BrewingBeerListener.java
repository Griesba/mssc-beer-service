package guru.springframework.msscbeerservice.services.brewing;

import guru.sfg.common.events.NewInventoryEvent;
import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.sfg.common.events.BeerEvent;
import guru.sfg.common.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional// prevent Hibernate to run out of session when using lazy initialization
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BeerEvent beerEvent) {
        BeerDto beerDto = beerEvent.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer " + beer.getMinOnHand() + " QOH: " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);

    }
}
