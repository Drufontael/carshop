package tech.drufontael.carshop.modules.customer.domain.enums;

import tech.drufontael.carshop.modules.customer.application.Buy;
import tech.drufontael.carshop.modules.customer.application.Consign;
import tech.drufontael.carshop.modules.customer.application.Sell;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.CustomerAction;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

public enum CustomerType {
    CONSIGNOR(new Consign()),
    SELLER(new Sell()),
    BUYER(new Buy()),
    OWNER(new Sell());

    private  CustomerAction action;

    CustomerType(CustomerAction action){
        this.action=action;
    }

    void action(Customer customer, Vehicle vehicle, String... args){
        action.doAction(customer,vehicle,args);
    }
}
