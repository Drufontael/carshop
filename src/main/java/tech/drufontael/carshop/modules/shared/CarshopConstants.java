package tech.drufontael.carshop.modules.shared;

import tech.drufontael.carshop.modules.shared.value_object.CEP;

public class CarshopConstants {
    public static final Double COMMISSION=4.0;
    public static final Address SHOP_ADDRESS=Address.builder()
            .cep(new CEP("74215-240"))
            .street("Avenida Multirão")
            .complement("nº 2496, Qd. 78, Lt. 23E, Loja 01")
            .neighborhood("St. Bueno")
            .city("Goiânia")
            .state("GO")
            .country("Brasil")
            .build();
}
