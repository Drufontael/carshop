package tech.drufontael.carshop.modules.customer.domain;

import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

public interface CustomerAction {
    <T> T doAction(Customer customer,Vehicle vehicle,String... args);
}
