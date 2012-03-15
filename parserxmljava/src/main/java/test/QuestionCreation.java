package test;

import junit.framework.TestCase;
import dcll.enumeration.QuestionTextFormat;
import dcll.exception.MalformedQuestionException;
import dcll.question.Description;
import dcll.quiz.QuizWriter;

public class QuestionCreation extends TestCase {
	
	public void testDescription() throws MalformedQuestionException {

			Description d = new Description("Consigne dispositif électronique", "Ecrire un programme qui affiche Hello world", QuestionTextFormat.MOODLE_AUTO_FORMAT);
			QuizWriter.write(d.parse());
	}

}
