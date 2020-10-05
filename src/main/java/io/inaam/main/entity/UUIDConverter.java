//package io.inaam.main.entity;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.util.UUID;
//
//@Converter(autoApply = true)
//public class UUIDConverter implements AttributeConverter<UUID,String>
//{
//    @Override
//    public String convertToDatabaseColumn(UUID uuid)
//    {
//        return uuid.toString();
//    }
//
//    @Override
//    public UUID convertToEntityAttribute(String s)
//    {
//        return UUID.fromString(s);
//    }
//}
