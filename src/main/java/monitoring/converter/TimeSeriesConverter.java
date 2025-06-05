package monitoring.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Converter
public class TimeSeriesConverter implements AttributeConverter<Map<LocalDate, Double>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(Map<LocalDate, Double> attribute) {
        if (attribute == null) return null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeObject(attribute);
            oos.flush();
            return Base64.getEncoder().encodeToString(baos.toByteArray());

        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot serialize Map<LocalDate, Double>", e);
        }
    }


    @Override
    public Map<LocalDate, Double> convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        byte[] data = Base64.getDecoder().decode(dbData);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (Map<LocalDate, Double>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Cannot deserialize Map<LocalDate, Double>", e);
        }
    }

}

