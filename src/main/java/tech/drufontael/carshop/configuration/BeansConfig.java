package tech.drufontael.carshop.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.drufontael.carshop.adapter.PdfContractAdapter;
import tech.drufontael.carshop.adapter.impl.ItextPdfAdapter;
import tech.drufontael.carshop.adapter.impl.PdfboxAdapter;

@Configuration
public class BeansConfig {
    @Bean
    public OpenAPI docConfig(){
        return new OpenAPI()
                .info(new Info()
                        .title("Carshop API RestFull")
                        .version("0.0.1-SNAPSHOT")
                        .description("API para loja de compra, venda e consignação de veiculos.")
                );
    }

    @Bean
    public PdfContractAdapter pdfContractAdapter(){
        return new ItextPdfAdapter();
    }
}
