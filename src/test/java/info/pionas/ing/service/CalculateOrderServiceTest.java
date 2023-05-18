package info.pionas.ing.service;

import info.pionas.ing.model.atmservice.ATM;
import info.pionas.ing.model.atmservice.ServiceTasks;
import info.pionas.ing.model.atmservice.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculateOrderServiceTest {

    private final CalculateOrderService service = new CalculateOrderService();

    @Test
    void getOrder() {
        // given
        // when
        final var order = service.getOrder(getServiceTasks());
        //then
        Assertions.assertThat(order).isNotEmpty();
        Assertions.assertThat(order.get(0)).isEqualTo(getATM(1, 2));
        Assertions.assertThat(order.get(1)).isEqualTo(getATM(1, 1));
        Assertions.assertThat(order.get(2)).isEqualTo(getATM(2, 3));
        Assertions.assertThat(order.get(3)).isEqualTo(getATM(3, 1));
        Assertions.assertThat(order.get(4)).isEqualTo(getATM(3, 2));
        Assertions.assertThat(order.get(5)).isEqualTo(getATM(3, 3));
        Assertions.assertThat(order.get(6)).isEqualTo(getATM(3, 4));
        Assertions.assertThat(order.get(7)).isEqualTo(getATM(4, 5));
        Assertions.assertThat(order.get(8)).isEqualTo(getATM(5, 2));
        Assertions.assertThat(order.get(9)).isEqualTo(getATM(5, 1));
    }

    private ServiceTasks getServiceTasks() {
        ServiceTasks serviceTasks = new ServiceTasks();
        serviceTasks.add(getServiceTask(1, 2, Task.RequestTypeEnum.STANDARD));
        serviceTasks.add(getServiceTask(1, 1, Task.RequestTypeEnum.STANDARD));
        serviceTasks.add(getServiceTask(2, 3, Task.RequestTypeEnum.PRIORITY));
        serviceTasks.add(getServiceTask(3, 4, Task.RequestTypeEnum.STANDARD));
        serviceTasks.add(getServiceTask(4, 5, Task.RequestTypeEnum.STANDARD));
        serviceTasks.add(getServiceTask(5, 2, Task.RequestTypeEnum.PRIORITY));
        serviceTasks.add(getServiceTask(5, 1, Task.RequestTypeEnum.STANDARD));
        serviceTasks.add(getServiceTask(3, 2, Task.RequestTypeEnum.SIGNAL_LOW));
        serviceTasks.add(getServiceTask(3, 1, Task.RequestTypeEnum.FAILURE_RESTART));
        serviceTasks.add(getServiceTask(3, 3, Task.RequestTypeEnum.PRIORITY));
        return serviceTasks;
    }

    private Task getServiceTask(int region, int atmId, Task.RequestTypeEnum standard) {
        return new Task().region(region).atmId(atmId).requestType(standard);
    }

    private ATM getATM(int region, int atmId) {
        return new ATM().region(region).atmId(atmId);
    }
}