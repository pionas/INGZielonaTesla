package info.pionas.ing.rest;

import info.pionas.ing.model.onlinegame.Order;
import info.pionas.ing.model.onlinegame.Players;
import info.pionas.ing.service.OnlineGameService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onlinegame/calculate")
class OnlineGameRestResource {

    private final OnlineGameService service;

    OnlineGameRestResource(OnlineGameService service) {
        this.service = service;
    }

    @PostMapping
    @Cacheable(value = "onlinegame", keyGenerator = "customKeyGenerator")
    public ResponseEntity<Order> getOrder(@RequestBody Players players) {
        return ResponseEntity.ok(service.getOrder(players));
    }
}
