package tech.drufontael.carshop.modules.customer.application;

import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.CustomerAction;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

public class Buy implements CustomerAction {


    @Override
    public PurchaseOrder doAction(Customer customer, Vehicle vehicle,String... args) {
        //PaymentMethod paymentMethod=new PaymentMethod(args[0]);
        //SellContract contract=generateContract(customer,vehicle,paymentMethod);
        //BuyAnSellOrder order=generateOrder(contract);
        return new PurchaseOrder();
    }
}

