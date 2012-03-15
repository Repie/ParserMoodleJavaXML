package test;

import java.util.ArrayList;

import junit.framework.TestCase;

import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.exception.MalformedQuestionException;
import dcll.question.Description;
import dcll.question.Essay;
import dcll.question.Question;
import dcll.quiz.Quiz;
import dcll.quiz.QuizWriter;

public class Parse  extends TestCase {
	
	public void testParseQuiz() throws MalformedQuestionException{
		ArrayList<Question> questions = new ArrayList<Question>();
		questions.add(new Description("Consigne dispositif électronique", "Ecrire un programme qui affiche Hello world", QuestionTextFormat.MOODLE_AUTO_FORMAT));
		RegularAnswer a = new RegularAnswer(new String(), 0, new String());
		Essay e = new Essay("Question ouverte", a, "Ecrire un programme qui affiche Hello world");
		questions.add(e);
		Quiz q = new Quiz();
		QuizWriter.write(q);
	}

}
