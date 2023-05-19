package info.pionas.ing.rest;

import info.pionas.ing.model.atmservice.Order;
import info.pionas.ing.model.atmservice.ServiceTasks;
import info.pionas.ing.service.AtmsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atms/calculateOrder")
class AtmsRestResource {

    private final AtmsService service;

    AtmsRestResource(AtmsService service) {
        this.service = service;
    }

    @PostMapping
    @Cacheable(value = "atms", keyGenerator = "customKeyGenerator")
    public ResponseEntity<Order> getOrder(@RequestBody ServiceTasks serviceTasks) {
        return ResponseEntity.ok(service.getOrder(serviceTasks));
    }
}
