package tech.drufontael.carshop.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Utils {
    public static void copyNonNullProperties(Object source,Object target){
        BeanUtils.copyProperties(source,target,getNullPropertyName(source));
    }
    public static String[] getNullPropertyName(Object source){
        final BeanWrapper src=new BeanWrapperImpl(source);
        PropertyDescriptor[] pds=src.getPropertyDescriptors();
        Set<String> empty=new HashSet<>();
        for(PropertyDescriptor pd:pds){
            Object srcValue=src.getPropertyValue(pd.getName());
            if(srcValue==null){
                empty.add(pd.getName());
            }
        }
        String[] result=new String[empty.size()];
        return empty.toArray(result);
    }
    public static String plateValidation(String plate){
        plate=plate.toUpperCase().trim().replace(" ","");
        Pattern pattern=Pattern.compile("([A-Z]{3}[0-9]{4})|([A-Z]{3}-[0-9]{4})|([A-Z]{3}-[0-9]{1}[A-Z]{1}[0-9]{2})|([A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2})");
        if(pattern.matcher(plate).matches()){
            plate=plate.replace("-","");
        }else {
            throw new InvalidArgumentFormatException("Invalid plate format.");
        }
        return plate;
    }
}
