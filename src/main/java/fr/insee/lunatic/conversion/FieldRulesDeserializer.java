package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.insee.lunatic.model.flat.FieldRules;

import java.io.IOException;

public class FieldRulesDeserializer extends StdDeserializer<FieldRules> {

    @SuppressWarnings("unused") // default constructor is required by jackson
    public FieldRulesDeserializer() {
        this(null);
    }

    public FieldRulesDeserializer(Class<FieldRules> fieldRulesClass) {
        super(fieldRulesClass);
    }

    @Override
    public FieldRules deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode rulesNode = jsonParser.getCodec().readTree(jsonParser);
        FieldRules fieldRules = new FieldRules();
        if (rulesNode.isTextual())
            fieldRules.setRule(rulesNode.textValue());
        if (rulesNode.isArray()) {
            rulesNode.forEach(patternNode -> fieldRules.addPattern(patternNode.textValue()));
        }
        return fieldRules;
    }

}
