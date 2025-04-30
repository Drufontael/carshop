package tech.drufontael.carshop.modules.vehicle.domain.enuns;

import tech.drufontael.carshop.exceptions.ResourceNotFoundException;

import java.util.Arrays;

public enum Color {
    RED("VERMELHO"),
    BLUE("AZUL"),
    WHITE("BRANCO"),
    BLACK("PRETO"),
    GRAY("CINZA"),
    ORANGE("LARANJA"),
    GREEN("VERDE"),
    YELLOW("AMARELO"),
    SILVER("PRATA");

    private String cor;

    Color(String cor){
        this.cor=cor;
    }

    public String getCor() {
        return cor;
    }
    public Color getByCor(String cor){
        return Arrays.stream(Color.values())
                .filter(color -> color.cor.equalsIgnoreCase(cor))
                .findFirst().orElseThrow(()->new ResourceNotFoundException("Cor "+cor+" não encontrada!"));
    }
}
