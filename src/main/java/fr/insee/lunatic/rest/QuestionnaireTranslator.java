package fr.insee.lunatic.rest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;

@Path("questionnaire")
public class QuestionnaireTranslator {

	private static final Logger logger = LogManager.getLogger(QuestionnaireTranslator.class);

   
    @POST
    @Path("xml-json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Response xmlToJSON(String xmlQuestionnaire, @DefaultValue("false") @QueryParam("monitor") boolean monitor) {

    	if ((xmlQuestionnaire == null) || (xmlQuestionnaire.length() == 0)) {
    		logger.error("Null or empty content received, returning BAD REQUEST response");
    		return Response.status(Status.BAD_REQUEST).build();
    	}
    	if (logger.isDebugEnabled()) {
    		String xmlStart = (xmlQuestionnaire.length() < 50) ? xmlQuestionnaire : xmlQuestionnaire.substring(0, 50);
        	logger.debug("Trying to translate to JSON questionnaire starting with " + xmlStart);
        	if (monitor) logger.debug("Monitoring in on");
    	}
    	String jsonQuestionnaire;
    	try {
    		XMLLunaticToXMLLunaticFlatTranslator translatorStep1 = new XMLLunaticToXMLLunaticFlatTranslator();
    		XMLLunaticFlatToJSONLunaticFlatTranslator translatorStep2 = new XMLLunaticFlatToJSONLunaticFlatTranslator(monitor);
    		InputStream xmlStream = new ByteArrayInputStream(xmlQuestionnaire.getBytes(StandardCharsets.UTF_8));
    		jsonQuestionnaire = translatorStep2.translate(translatorStep1.generate(xmlStream));
    	} catch (Exception e) {
    		logger.error("Error during translation", e);
    		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
    	return Response.status(Status.OK).entity(jsonQuestionnaire).build();	
    }
}
