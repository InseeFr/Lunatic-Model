package fr.insee.lunatic.utils;

import java.io.File;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import fr.insee.lunatic.Constants;

public class SchemaValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaValidator.class);

    private Schema schema;
    private Validator validator;

    public SchemaValidator(Modele modele) {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            switch (modele){
                case FLAT:
                    schema = sf.newSchema(Constants.MODEL_FLAT_XSD);;
                    break;
                case HIERARCHICAL:
                    schema = sf.newSchema(Constants.MODEL_HIERARCHICAL_XSD);;
                    break;
            }
        } catch (SAXException e) {
            e.printStackTrace();
        }
        validator = schema.newValidator();
    }

    public boolean validateFile(File file)  {
        boolean result = true;
        validator.reset();
        Source source = new StreamSource(file);
        try {
            source = toDOMSource(source);
            validator.validate(source);
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    public boolean validateIS(InputStream inputStream) {
        boolean result = true;
        validator.reset();
        Source source = new StreamSource(inputStream);
        try {
            source = toDOMSource(source);
            validator.validate(source);
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    public DOMSource toDOMSource(Source source) throws Exception {
        if (source instanceof DOMSource) {
            return (DOMSource) source;
        }
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        DOMResult result = new DOMResult();
        trans.transform(source, result);
        return new DOMSource(result.getNode());
    }
}
