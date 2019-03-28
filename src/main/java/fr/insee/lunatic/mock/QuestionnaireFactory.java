package fr.insee.lunatic.mock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.SequenceType;

public class QuestionnaireFactory {

	private static final Logger logger = LogManager.getLogger(QuestionnaireFactory.class);

	public QuestionnaireFactory() {}

	public Questionnaire createQuestionnaire() {

		Questionnaire survey = new Questionnaire();

		String surveyNumber =  String.format("%03d", (int) Math.floor(Math.random() * 1000));

		logger.debug("Creating questionnaire number " + surveyNumber);

		survey.setId("FQ_" + surveyNumber);
		survey.setLabel("Label for fake questionnaire number " + surveyNumber);
		
		logger.debug("Survey added to questionnaire number " + surveyNumber);

		// Create a sequence of level 0 and borrow its children
		SequenceFactory sequenceFactory = new SequenceFactory();
		SequenceType sequence = sequenceFactory.createSequence(1);
		survey.getComponents().addAll(sequence.getComponents());

		return survey;
	}
}
