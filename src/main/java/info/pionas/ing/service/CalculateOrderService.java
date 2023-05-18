package info.pionas.ing.service;

import info.pionas.ing.model.atmservice.ATM;
import info.pionas.ing.model.atmservice.Order;
import info.pionas.ing.model.atmservice.ServiceTasks;
import info.pionas.ing.model.atmservice.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

@Service
public class CalculateOrderService {

    public Order getOrder(ServiceTasks serviceTasks) {
        final var order = new Order();
        order.addAll(getSortServiceTasks(serviceTasks));
        return order;
    }

    private Collection<ATM> getSortServiceTasks(ServiceTasks serviceTasks) {
        return Optional.ofNullable(serviceTasks)
                .stream()
                .flatMap(Collection::stream)
                .sorted(taskComparator())
                .map(this::getAtm)
                .toList();
    }

    private Comparator<? super Task> taskComparator() {
        return Comparator.comparing(Task::getRegion)
                .thenComparing(Task::getRequestType, Comparator.reverseOrder());
    }

    private ATM getAtm(Task task) {
        return new ATM()
                .atmId(task.getAtmId())
                .region(task.getRegion());
    }
}
