package fr.insee.lunatic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.JSONDeserializer;
import fr.insee.lunatic.model.flat.Questionnaire;

public class JSONDeserializerTest {

	@Test
	public void testQuestionnaire() throws Exception {

		long startTime = System.currentTimeMillis();

		JSONDeserializer deserializer = new JSONDeserializer();
		Questionnaire questionnaire = deserializer.deserialize(Constants.RESOURCES_FOLDER_JSONF_2_XMLF_PATH+"/in.json");

		long elapsedTime = System.currentTimeMillis() - startTime;

		assertEquals("i6vwid",questionnaire.getId());
		System.out.println("Serialization time: " + elapsedTime);
	}

}
