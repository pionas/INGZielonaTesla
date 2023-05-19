package info.pionas.ing.rest;

import info.pionas.ing.model.transactions.Accounts;
import info.pionas.ing.model.transactions.Transactions;
import info.pionas.ing.service.TransactionsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions/report")
class TransactionsRestResource {

    private final TransactionsService service;

    TransactionsRestResource(TransactionsService service) {
        this.service = service;
    }

    @PostMapping
    @Cacheable(value = "transactions", keyGenerator = "customKeyGenerator")
    public ResponseEntity<Accounts> getOrder(@RequestBody Transactions transactions) {
        return ResponseEntity.ok(service.getAccounts(transactions));
    }
}
