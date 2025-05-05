package tech.drufontael.carshop.modules.shared;

import tech.drufontael.carshop.modules.customer.domain.enums.CustomerType;
import tech.drufontael.carshop.modules.shared.value_object.CEP;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final Map<CustomerType, List<String>> CANONICAL_VARIANTS = Map.of(
            CustomerType.BUYER, List.of("buyer", "comprador", "acheteur", "käufer"),
            CustomerType.SELLER, List.of("seller", "vendedor", "vendeur", "verkäufer"),
            CustomerType.CONSIGNOR, List.of("consignor", "consignador", "expéditeur"),
            CustomerType.OWNER, List.of("owner", "proprietário", "proprietaire", "besitzer")
    );

    public static final Map<String, CustomerType> VARIANT_TO_TYPE;

    static {
        Map<String, CustomerType> map = new HashMap<>();
        for (Map.Entry<CustomerType, List<String>> entry : CANONICAL_VARIANTS.entrySet()) {
            CustomerType type = entry.getKey();
            for (String variant : entry.getValue()) {
                map.put(variant.toLowerCase(), type); // Normaliza grafias para minúsculas
            }
        }
        VARIANT_TO_TYPE = Collections.unmodifiableMap(map);
    }
}
