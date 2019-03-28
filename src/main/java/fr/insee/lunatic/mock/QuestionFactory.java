package fr.insee.lunatic.mock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.lunatic.model.flat.QuestionType;

public class QuestionFactory {

	private static final Logger logger = LogManager.getLogger(QuestionFactory.class);


	public QuestionFactory() {
	}

	public QuestionType createQuestion(int number) {

		if (number < 0) return null;

		QuestionType question = this.createQuestionOnly(number);
		ComponentFactory componentFactory = new ComponentFactory();
		componentFactory.fleshoutComponent(question);

		logger.debug("Question fleshed out");


		return question;
	}

	public QuestionType createQuestionOnly(int number) {

		if (number < 0) return null;

		logger.debug("Creating question number " + number);

		QuestionType question = new QuestionType();

		question.setId("FQU_" + number);
		question.setLabel("Label for question number " + number);

		return question;
	}
}
