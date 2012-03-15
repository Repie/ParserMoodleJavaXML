package test;

import junit.framework.TestCase;
import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.exception.MalformedQuestionException;
import dcll.question.Description;
import dcll.question.Essay;
import dcll.quiz.QuizWriter;

public class CorrectQuestionCreation extends TestCase {
	
	public void testDescription() throws MalformedQuestionException {

			Description d = new Description("Consigne dispositif électronique", "Ecrire un programme qui affiche Hello world", QuestionTextFormat.MOODLE_AUTO_FORMAT);
			QuizWriter.write(d.parse());
	}
	
	public void testEssay() throws MalformedQuestionException{
		RegularAnswer a = new RegularAnswer(new String(), 0, new String());
		Essay q = new Essay("Question ouverte", a, "Ecrire un programme qui affiche Hello world");
		QuizWriter.write(q);
	}

}
