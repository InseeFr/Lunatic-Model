package fr.insee.lunatic.mock.h;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.insee.lunatic.mock.QuestionFactory;
import fr.insee.lunatic.model.hierarchical.SequenceType;

public class SequenceFactory {

	public static int ONLY_SEQUENCES_DEPTH = 1;
	public static int ONLY_QUESTIONS_DEPTH = 3;
	public static int MAX_CHILDREN_NUMBER = 5;

	private static final Logger logger = LoggerFactory.getLogger(SequenceFactory.class);

	public SequenceFactory() {}

	/**
	 * Creates a sequence with a given number.
	 * 
	 * @param number The sequence number (will be used to build the id).
	 * @return A <code>SequenceType</code> object.
	 */
	public SequenceType createSequence(int number) {

		if (number <= 0) return null;

		logger.debug("Creating sequence number " + number);

		// The sequence depth is calculated as the length of the number
		int depth = String.valueOf(number).length();
		if (depth > ONLY_QUESTIONS_DEPTH) return null;

		SequenceType sequence = new SequenceType();
		ComponentFactory componentFactory = new ComponentFactory();

		QuestionFactory questionFactory = new QuestionFactory();
		
		sequence.setId("FSQ_" + number);
		sequence.setLabel("Label for sequence number " + number);
		// TODO See http://stackoverflow.com/questions/16280273/jaxb-javatype-customization-on-xsinteger-produces-xmlelement-with-type-string
		componentFactory.fleshoutComponent(sequence);

		int numberOfChildren = (int) Math.floor(Math.random() * (MAX_CHILDREN_NUMBER + 1));
		logger.debug("Sequence "+ sequence.getId() + " fleshed out, number of children to be added: " + numberOfChildren);

		for (int childIndex = 0; childIndex <= numberOfChildren; childIndex++) {
			int childNumber = number * 10 + childIndex;
		}

		return sequence;
	}
}
