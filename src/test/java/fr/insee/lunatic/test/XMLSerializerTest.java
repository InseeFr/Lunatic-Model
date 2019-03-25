package fr.insee.lunatic.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.insee.lunatic.conversion.XMLSerializer;
import fr.insee.lunatic.mock.QuestionnaireFactory;
import fr.insee.lunatic.mock.SequenceFactory;
import fr.insee.lunatic.model.Questionnaire;
import fr.insee.lunatic.model.SequenceType;

public class XMLSerializerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testQuestionnaire() throws Exception {

		QuestionnaireFactory factory = new QuestionnaireFactory();
		Questionnaire fakeQuestionnaire = factory.createQuestionnaire();

		long startTime = System.currentTimeMillis();

		XMLSerializer serializer = new XMLSerializer();
		String xmlQuestionnaire = serializer.serialize(fakeQuestionnaire);

		long elapsedTime = System.currentTimeMillis() - startTime;

		FileUtils.writeStringToFile(new File("src/test/resources/questionnaire-ser.xml"), xmlQuestionnaire, "UTF-8");
		System.out.println("Serialization time: " + elapsedTime);
	}	
}
