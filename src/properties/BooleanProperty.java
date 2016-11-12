package properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class BooleanProperty implements Property {
    private String name;
    private boolean value;

    public BooleanProperty(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public static class Serializer implements JsonSerializer<BooleanProperty> {
        @Override
        public JsonElement serialize(BooleanProperty property, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(property.getValue());
        }
    }
}
