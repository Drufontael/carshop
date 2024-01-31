package tech.drufontael.carshop.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI docConfig(){
        return new OpenAPI()
                .info(new Info()
                        .title("Carshop API RestFull")
                        .version("0.0.1-SNAPSHOT")
                        .description("API para loja de compra, venda e consignação de veiculos.")
                );
    }
}
