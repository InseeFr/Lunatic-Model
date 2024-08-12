package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.insee.lunatic.model.flat.InputNumber.Unit;

import java.io.IOException;

public class UnitSerializer extends StdSerializer<Unit> {

    public UnitSerializer() {
        super(Unit.class);
    }
    protected UnitSerializer(Class<Unit> unitClass) {
        super(unitClass);
    }

    @Override
    public void serialize(Unit unit, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (unit.getLabel() != null){
            serializeLabel(unit, jsonGenerator);
            return;
        }
        if (unit.getValue() != null) {
            serializeString(unit, jsonGenerator);
            return;
        }
        jsonGenerator.writeNull();
    }

    private void serializeString(Unit unit, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeString(unit.getValue());
    }

    private void serializeLabel(Unit unit, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeString(unit.getLabel().getValue());
        jsonGenerator.writeFieldName("type");
        jsonGenerator.writeString(unit.getLabel().getType().value());
        jsonGenerator.writeEndObject();
    }

}
