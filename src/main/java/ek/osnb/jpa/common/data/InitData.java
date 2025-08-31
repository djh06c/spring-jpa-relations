package ek.osnb.jpa.common.data;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.model.OrderStatus;
import ek.osnb.jpa.orders.repository.OrderLineRepository;
import ek.osnb.jpa.orders.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class InitData implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    public InitData(OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public void run(String... args) {
        Order order1 = new Order(LocalDate.now(), OrderStatus.PENDING);
        Order order2 = new Order(LocalDate.now(), OrderStatus.PENDING);
        orderRepository.save(order1);
        orderRepository.save(order2);

        OrderLine orderline1 = new OrderLine("Pizza pie", 89.0, 2);
        OrderLine orderline2 = new OrderLine("Berliner Döner bror", 49.0, 1);
        OrderLine orderline3 = new OrderLine("French toast", 20.0, 1);

        orderline1.setOrder(order1);
        orderline2.setOrder(order2);
        orderline3.setOrder(order2);

        orderLineRepository.saveAll(List.of(orderline1, orderline2, orderline3));
    }
}
