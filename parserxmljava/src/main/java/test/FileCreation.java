package test;

import java.util.ArrayList;

import dcll.answer.RegularAnswer;
import dcll.answer.Subquestion;
import dcll.enumeration.QuestionTextFormat;
import dcll.exception.MalformedQuestionException;
import dcll.parser.JavatoXMLParser;
import dcll.question.Description;
import dcll.question.Essay;
import dcll.question.Matching;
import dcll.question.Question;
import dcll.quiz.Quiz;
import junit.framework.TestCase;

public class FileCreation extends TestCase {
	public void testFileCreation() throws MalformedQuestionException{
		ArrayList<Subquestion> answers = new ArrayList<Subquestion>();
		answers.add(new Subquestion("JBOSS", "Redhat"));
		answers.add(new Subquestion("Websphere", "IBM"));
		answers.add(new Subquestion("GlassFish", "Oracle"));
		answers.add(new Subquestion("Tomcat", "Fondation Apache"));
		
		Matching qm = new Matching("Serveur d'application / éditeurs", answers, "Relier les serveurs d'applications avec les bons éditeurs",  QuestionTextFormat.MOODLE_AUTO_FORMAT, true);
		RegularAnswer a = new RegularAnswer(new String(""), 0, new String(""));
		ArrayList<RegularAnswer> answers2 = new ArrayList<RegularAnswer>();
		answers2.add(a);
		Essay qe = new Essay("Question ouverte", answers2, "Ecrire un programme qui affiche Hello world");
		Description d = new Description("Consigne dispositif électronique", "Ecrire un programme qui affiche Hello world", QuestionTextFormat.MOODLE_AUTO_FORMAT);
		
		Quiz q = new Quiz();
		q.addQuestion(qm);
		q.addQuestion(qe);
		q.addQuestion(d);
		
		new JavatoXMLParser().parse(q, "H:\\quiz.xml");
	}

}

