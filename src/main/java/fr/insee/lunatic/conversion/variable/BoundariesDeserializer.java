package fr.insee.lunatic.conversion.variable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.insee.lunatic.model.flat.*;

import java.io.IOException;

public class BoundariesDeserializer extends JsonDeserializer<Boundaries> {

    @Override
    public Boundaries deserialize(JsonParser p, DeserializationContext context) throws IOException {
        ObjectCodec codec = p.getCodec();
        ObjectNode root = codec.readTree(p);

        ComponentTypeEnum componentType = getComponentTypeEnum(p);

        ObjectMapper mapper = (ObjectMapper) codec;
        return switch (componentType) {
            case INPUT_NUMBER -> mapper.treeToValue(root, NumberBoundaries.class);
            case DATEPICKER -> mapper.treeToValue(root, DateBoundaries.class);
            default -> throw new IOException("Unsupported componentType: " + componentType);
        };
    }

    private static ComponentTypeEnum getComponentTypeEnum(JsonParser p) throws IOException {
        JsonStreamContext parsingContext = p.getParsingContext();
        Object parentValue = null;

        while (parsingContext != null && parentValue == null) {
            parentValue = parsingContext.getCurrentValue();
            parsingContext = parsingContext.getParent();
        }

        if (!(parentValue instanceof BodyCell bodyCell)) {
            throw new IOException("Boundaries must be deserialized as part of a BodyCell.");
        }

        ComponentTypeEnum componentType = bodyCell.getComponentType();
        if (componentType == null) {
            throw new IOException("Missing componentType in BodyCell.");
        }
        return componentType;
    }
}


