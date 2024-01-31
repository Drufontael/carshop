package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.model.Image;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private String description;
    private String path;

    public Image toImage(){
        return new Image(getDescription(),getPath());
    }
}
