package tech.drufontael.carshop.modules.customer.domain;

import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

public interface CustomerAction {
    void doAction(Customer customer,Vehicle vehicle,String... args);
}
