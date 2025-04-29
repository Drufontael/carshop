package tech.drufontael.carshop.modules.customer.application;

import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.CustomerAction;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

public class Consign implements CustomerAction {

    @Override
    public void doAction(Customer customer, Vehicle vehicle, String... args) {
        String commission=args[0].replaceAll("[^0-9]","");
        String minimumPrice=args[1].replaceAll("[^0-9]","");
        //Consignment consignment=new Consignment(customer,vehicle,new BigDecimal(comission),new Bigdecimal(minimumPrice);
    }
}
