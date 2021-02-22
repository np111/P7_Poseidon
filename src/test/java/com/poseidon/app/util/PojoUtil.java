package com.poseidon.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.lang.reflect.Type;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public class PojoUtil {
    private static final ObjectMapper OBJECT_MAPPER;
    private static final PodamFactory PODAM;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("UTC"));
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        PODAM = new PodamFactoryImpl(new AbstractRandomDataProviderStrategy() {
            private final Random rand = new Random();

            {
                setMemoization(false);
            }

            @SuppressWarnings("unchecked")
            @Override
            public <T> T getTypeValue(AttributeMetadata attributeMetadata, Map<String, Type> genericTypesArgumentsMap, Class<T> pojoType) {
                if (ZonedDateTime.class.isAssignableFrom(pojoType)) {
                    return (T) ZonedDateTime.of(
                            2000 + rand.nextInt(10),
                            1 + rand.nextInt(12),
                            1 + rand.nextInt(28),
                            rand.nextInt(24),
                            rand.nextInt(60),
                            rand.nextInt(60),
                            0,
                            ZoneId.of("UTC"));
                }
                return super.getTypeValue(attributeMetadata, genericTypesArgumentsMap, pojoType);
            }
        });
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public static <T> T deepClone(T obj) {
        if (obj == null) {
            return null;
        }
        T copy = (T) OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(obj), obj.getClass());
        assertEquals(obj, copy);
        return copy;
    }

    public static PodamFactory getPodam() {
        return PODAM;
    }
}
