package tech.drufontael.carshop.modules.customer.application;

import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.CustomerAction;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

public class Sell implements CustomerAction {


    @Override
    public SalesOrder doAction(Customer customer, Vehicle vehicle, String... args) {
        return new SalesOrder();
    }
}
