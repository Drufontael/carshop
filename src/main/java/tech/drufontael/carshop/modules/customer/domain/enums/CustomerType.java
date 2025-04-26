package tech.drufontael.carshop.modules.customer.domain.enums;

import tech.drufontael.carshop.modules.customer.application.Buy;
import tech.drufontael.carshop.modules.customer.application.Consign;
import tech.drufontael.carshop.modules.customer.application.Sell;
import tech.drufontael.carshop.modules.customer.domain.CustomerAction;

public enum CustomerType {
    CONSIGNOR(new Consign()),
    SELLER(new Sell()),
    BUYER(new Buy());

    private  CustomerAction action;

    CustomerType(CustomerAction action){
        this.action=action;
    }

    void action(){
        action.action();
    }
}
