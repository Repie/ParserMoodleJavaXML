package test;

import java.util.ArrayList;

import junit.framework.TestCase;
import dcll.answer.RegularAnswer;
import dcll.answer.Subquestion;
import dcll.enumeration.QuestionTextFormat;
import dcll.exception.MalformedQuestionException;
import dcll.question.Description;
import dcll.question.Essay;
import dcll.question.Matching;
import dcll.quiz.QuizWriter;

public class CorrectQuestionCreation extends TestCase {
	
	public void testDescription() throws MalformedQuestionException {

			Description d = new Description("Consigne dispositif électronique", "Ecrire un programme qui affiche Hello world", QuestionTextFormat.MOODLE_AUTO_FORMAT);
			QuizWriter.write(d.parse());
	}
	
	public void testEssay() throws MalformedQuestionException{
		RegularAnswer a = new RegularAnswer(new String(""), 0, new String(""));
		ArrayList<RegularAnswer> answers = new ArrayList<RegularAnswer>();
		answers.add(a);
		Essay q = new Essay("Question ouverte", answers, "Ecrire un programme qui affiche Hello world");
		QuizWriter.write(q);
	}
	
	public void testMatching() throws MalformedQuestionException{
		ArrayList<Subquestion> answers = new ArrayList<Subquestion>();
		answers.add(new Subquestion("JBOSS", "Redhat"));
		answers.add(new Subquestion("Websphere", "IBM"));
		answers.add(new Subquestion("GlassFish", "Oracle"));
		answers.add(new Subquestion("Tomcat", "Fondation Apache"));
		
		
		Matching q = new Matching("Serveur d'application / éditeurs", answers, "Relier les serveurs d'applications avec les bons éditeurs",  QuestionTextFormat.MOODLE_AUTO_FORMAT, 1, 0.1, 0);
	}

}
