package properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class IntegerProperty implements Property {
    private String name;
    private int value;

    public IntegerProperty(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static class Serializer implements JsonSerializer<IntegerProperty> {
        @Override
        public JsonElement serialize(IntegerProperty property, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(property.getValue());
        }
    }
}
