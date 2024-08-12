package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.insee.lunatic.model.flat.InputNumber.Unit;
import fr.insee.lunatic.model.flat.LabelType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;

import java.io.IOException;

public class UnitDeserializer extends StdDeserializer<Unit> {

    public UnitDeserializer() {
        super(Unit.class);
    }
    protected UnitDeserializer(Class<Unit> unitClass) {
        super(unitClass);
    }

    @Override
    public Unit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode unitNode = jsonParser.getCodec().readTree(jsonParser);
        if (unitNode.isTextual()) {
            Unit unit = new Unit();
            unit.setValue(unitNode.textValue());
            return unit;
        }
        if (unitNode.isObject()) {
            Unit unit = new Unit();
            LabelType label = new LabelType();
            label.setValue(unitNode.get("value").textValue());
            label.setType(LabelTypeEnum.fromValue(unitNode.get("type").textValue()));
            unit.setLabel(label);
            return unit;
        }
        return null;
    }

}
