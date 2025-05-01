package tech.drufontael.carshop.modules.shared.dto.requests;

public record AddressRequest(
        String cep,
        String street,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country
) {}
