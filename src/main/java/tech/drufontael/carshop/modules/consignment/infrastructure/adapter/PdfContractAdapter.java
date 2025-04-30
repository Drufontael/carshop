package tech.drufontael.carshop.modules.consignment.infrastructure.adapter;


import tech.drufontael.carshop.modules.consignment.domain.Consignment;

public interface PdfContractAdapter {
    byte[] documentCreator(Consignment consignment);
}
