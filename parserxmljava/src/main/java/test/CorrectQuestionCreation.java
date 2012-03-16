package test;

import java.util.ArrayList;

import junit.framework.TestCase;
import dcll.answer.RegularAnswer;
import dcll.answer.Subquestion;
import dcll.enumeration.QuestionTextFormat;
import dcll.exception.MalformedQuestionException;
import dcll.question.Cloze;
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
		
		Matching q = new Matching("Serveur d'application / éditeurs", answers, "Relier les serveurs d'applications avec les bons éditeurs",  QuestionTextFormat.MOODLE_AUTO_FORMAT, true);
		QuizWriter.write(q);
	}
	
	public void testCloze() throws MalformedQuestionException{	
		Cloze q = new Cloze("Question \"Cloze\" (composite ?)", "Cette question comporte du texte dans lequel on a directement intégré des réponses à choix multiples {1:MULTICHOICE:Mauvaise réponse#Feedback pour cette mauvaise réponse~Une autre mauvaise réponse#Feedback pour cette autre mauvaise réponse~=Bonne réponse#Feedback pour la bonne réponse~%50%Réponse qui vaut la moitié des points#Feedback pour la question qui vaut la moitié des points} ; vous devez maintenant répondre à une question courte{1:SHORTANSWER:Mauvaise réponse#Feedback pour cette mauvaise réponse~=Bonne réponse#Feedback pour la bonne réponse~%50%Réponse qui vaut la moitié des points#Feedback pour la question qui vaut la moitié des points}. Nous avons finalement une question qui demande une réponse numérique avec point décimal {2:NUMERICAL:=23.8:0.1#Feedback pour la bonne réponse 23.8~%50%23.8:2#Feedback pour la réponse qui donne la moitié des points}.\n\nRemarquez que les adresses URL comme www.moodle.org et les binettes :-) sont correctement interprétées :\na) Est-ce bien? {:MULTICHOICE:=Oui#Bonne réponse~Non#Votre opinion nous indiffère !}\nb) Quelle note désirez-vous? {3:NUMERICAL:=3:2}", QuestionTextFormat.NONE);
		QuizWriter.write(q);
	}
	
}
