package guru.springframework.msscbeerservice.services.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "inventor-failover")
public interface InventoryFailoverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/inventor-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();
}
