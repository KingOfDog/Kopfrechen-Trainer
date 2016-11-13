package properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Locale;

public class LocaleProperty implements Property {
    private String name;
    private Locale value;

    public LocaleProperty(String name, Locale value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public Locale getValue() {
        return this.value;
    }

    public void setValue(Locale value) {
        this.value = value;
    }

    public static class Serializer implements JsonSerializer<LocaleProperty> {
        @Override
        public JsonElement serialize(LocaleProperty property, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(property.getValue().toString());
        }
    }

}
