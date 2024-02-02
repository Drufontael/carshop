package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import tech.drufontael.carshop.model.Image;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private String description;
    private String path;

    public ImageDto(Image image){
        BeanUtils.copyProperties(image,this);
    }

    public Image toImage(){
        return new Image(getDescription(),getPath());
    }
}
