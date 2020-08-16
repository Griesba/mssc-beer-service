package guru.springframework.msscbeerservice.services.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient{

    private final InventoryFailoverFeignClient failoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        return failoverFeignClient.getOnHandInventory();
    }
}
