package tech.drufontael.carshop.modules.vehicle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)  // Garante que o nome seja único
    private String name;

    public Brand(String name){
        this.name = formatBrand(name);
    }

    public static String formatBrand(String name){
        StringBuilder formatedBrand=new StringBuilder();
        if(name.split(" ").length>1){
            String[] pieces=name.split(" ");
            for (int i=0;i< pieces.length;i++){
                String piece= normalizeWord(pieces[i]);
                formatedBrand.append(piece).append(" ");
            }
        }
        if(name.split("-").length>1){
            String[] pieces=name.split("-");
            for (int i=0;i< pieces.length;i++){
                String piece= normalizeWord(pieces[i]);
                formatedBrand.append(piece);
                if(i!=pieces.length-1) formatedBrand.append("-");
            }
        }
        if(formatedBrand.isEmpty()) formatedBrand.append(normalizeWord(name));
        return formatedBrand.toString().trim();
    }

    private static String normalizeWord(String word){
        word=word.toLowerCase();
        return word.substring(0,1).toUpperCase()+word.substring(1);
    }
}
