package tech.drufontael.carshop.modules.customer.application;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import tech.drufontael.carshop.modules.consignment.application.ConsignmentService;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.consignment.infrastructure.ConsignmentManager;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.CustomerAction;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

import java.math.BigDecimal;


public class Consign implements CustomerAction {


    @Override
    public Consignment doAction(Customer customer, Vehicle vehicle, String... args) {
        Consignment consignment=new Consignment();
        String cleanValue = args[0].replaceAll("[^0-9.,]", "");
        cleanValue = cleanValue.replace(",", ".");
        Double commission = Double.parseDouble(cleanValue);
        BigDecimal price=BigDecimal.ZERO;
        if(args.length>=2){
            String minimumPrice = args[1].replaceAll("[^0-9.,]", "");
            minimumPrice = minimumPrice.replace(",", ".");
            price=new BigDecimal(minimumPrice);
        }
        return new Consignment(customer,vehicle,commission,price);
    }
}
