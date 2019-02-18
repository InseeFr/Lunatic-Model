package fr.insee.lunatic.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.insee.lunatic.conversion.JSONSerializer;
import fr.insee.lunatic.mock.QuestionnaireFactory;
import fr.insee.lunatic.model.Questionnaire;

public class JSONSerializerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testQuestionnaire() throws Exception {

		QuestionnaireFactory factory = new QuestionnaireFactory();
		Questionnaire fakeQuestionnaire = factory.createQuestionnaire();

		long startTime = System.currentTimeMillis();

		JSONSerializer serializer = new JSONSerializer();
		String jsonQuestionnaire = serializer.serialize(fakeQuestionnaire);

		long elapsedTime = System.currentTimeMillis() - startTime;

		//FileUtils.writeStringToFile(new File("src/test/resources/questionnaire.json"), jsonQuestionnaire, "UTF-8");
		System.out.println("Serialization time: " + elapsedTime);
	}


}
