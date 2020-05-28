package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.events.ValidateBeerOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import guru.springframework.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator validator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    private void listen(ValidateBeerOrderRequest validateBeerOrderRequest) {

        Boolean isValid = validator.validateOrder(validateBeerOrderRequest.getBeerOrderDto());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .isValid(isValid)
                        .orderId(validateBeerOrderRequest.getBeerOrderDto().getId())
                        .build());


    }
}
