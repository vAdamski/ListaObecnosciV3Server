package Shared.Helpers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Klasa pomocnicza do konwersji obiektów na JSON i odwrotnie.
 */
public class JsonConverter {
    /**
     * Konwertuje JSON na obiekt określonego typu.
     *
     * @param json           JSON, który ma zostać przekonwertowany.
     * @param typeReference  Referencja do typu obiektu, na który ma być przekonwertowany JSON.
     * @param <T>            Typ obiektu, na który ma być przekonwertowany JSON.
     * @return Obiekt przekonwertowany z JSON.
     * @throws Exception Rzucany w przypadku wystąpienia błędu podczas konwersji.
     */
    public static <T> T convertJsonToClass(String json, TypeReference<T> typeReference) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, typeReference);
    }

    /**
     * Konwertuje obiekt na JSON.
     *
     * @param object Obiekt, który ma zostać przekonwertowany na JSON.
     * @return JSON przekonwertowany z obiektu.
     * @throws Exception Rzucany w przypadku wystąpienia błędu podczas konwersji.
     */
    public static String convertClassToJson(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

