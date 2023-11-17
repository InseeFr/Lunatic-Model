package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.insee.lunatic.model.flat.SymLinksType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SymLinksDeserializer extends StdDeserializer<SymLinksType> {

    public SymLinksDeserializer() {
        this(null);
    }

    public SymLinksDeserializer(Class<SymLinksType> symLinksTypeClass) {
        super(symLinksTypeClass);
    }

    @Override
    public SymLinksType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        SymLinksType symLinks = new SymLinksType();
        //
        JsonNode symLinksNode = jsonParser.getCodec().readTree(jsonParser);
        List<Map.Entry<String, JsonNode>> symLinksEntries = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> symLinksIterator = symLinksNode.fields();
        while (symLinksIterator.hasNext())
            symLinksEntries.add(symLinksIterator.next());
        // The only entry expected has the links variable as key, and the links as value
        if (symLinksEntries.size() != 1)
            throw new JsonParseException("'symLinks' can only have 1 entry, got " + symLinksEntries.size());
        //
        symLinks.setName(symLinksEntries.get(0).getKey());
        //
        JsonNode linksNode = symLinksEntries.get(0).getValue();
        Iterator<Map.Entry<String, JsonNode>> linksIterator = linksNode.fields();
        while (linksIterator.hasNext()) {
            Map.Entry<String, JsonNode> linkEntry = linksIterator.next();
            symLinks.getLink().add(
                    new SymLinksType.LINK(linkEntry.getKey(), linkEntry.getValue().textValue()));
        }
        //
        return symLinks;
    }

}
