package test;

import java.util.ArrayList;

import junit.framework.TestCase;
import dcll.answer.NumericalAnswer;
import dcll.answer.RegularAnswer;
import dcll.answer.Subquestion;
import dcll.enumeration.AnswerNumberingType;
import dcll.enumeration.QuestionTextFormat;
import dcll.exception.MalformedQuestionException;
import dcll.question.Cloze;
import dcll.question.Description;
import dcll.question.Essay;
import dcll.question.Matching;
import dcll.question.MultipleChoice;
import dcll.question.Numerical;
import dcll.question.ShortAnswer;
import dcll.question.TrueFalse;
import dcll.quiz.QuizWriter;

public class CorrectQuestionCreation extends TestCase {
	
	//TESTER QUE LE RESULTAT EST CELUI ATTENDU
	
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
	
	public void testMultipleChoice() throws MalformedQuestionException{
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("Une architecture N-tiers est uniquement une architecture à base de Web Services", 0, "Une architecture distribuée peut reposer par exemple sur RMI"));
		a.add(new RegularAnswer("Une architecture client serveur est une architecture N-tiers", 33.333, new String("")));
		a.add(new RegularAnswer("Une architecture N-tiers correspond à une architecture d'application distribuée sur plusieurs noeuds physiques", 33.333, new String("")));
		a.add(new RegularAnswer("Une application web est une application reposant sur une architecture N Tiers", 33.333, new String("")));
		
		MultipleChoice m = new MultipleChoice("Cocher les assertions vraies.", "Architectures N tiers", "Rappel N tiers : architectures multi-niveaux pouvant être distribuées sur plusieurs noeuds physique d'un réseau.", 
				a, QuestionTextFormat.MOODLE_AUTO_FORMAT, 1, false, AnswerNumberingType.ABC, "OK", new String(""), "KO", (float) 0.1, 0, 1);
		
		QuizWriter.write(m);
	}
	
	public void testShortAnswer() throws MalformedQuestionException{
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("Model View Controller", 100));
		a.add(new RegularAnswer("Modèle vue contrôleur", 100));
		
		
		ShortAnswer n = new ShortAnswer("MVC", a, "Que signifie MVC ?", 0);
		QuizWriter.write(n);
	}
	
	public void testNumerical() throws MalformedQuestionException{
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new NumericalAnswer("1996", 100));
		Numerical n = new Numerical("En quelle année HTTP devient le premier protocole de l'Internet ?", a, "HTTP 1er protocole de l'Internet");
		
		QuizWriter.write(n);
	}
	
	public void testTrueFalse() throws MalformedQuestionException{
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("true", 0, "Tomcat est un conteneur Web uniquement."));
		a.add(new RegularAnswer("false", 100, "Tomcat est un conteneur Web uniquement."));
		TrueFalse tf = new TrueFalse("Tomcat et JEE", a, "omcat est un conteneur implémentant toutes les spécifications JEE.");
		QuizWriter.write(tf);
	}
	
	
}
