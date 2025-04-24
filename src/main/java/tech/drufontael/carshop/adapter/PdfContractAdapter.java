package tech.drufontael.carshop.adapter;

import tech.drufontael.carshop.model.Consignment;

public interface PdfContractAdapter {
    byte[] documentCreator(Consignment consignment);
}
