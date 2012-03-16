package test;

import java.util.ArrayList;

import junit.framework.TestCase;
import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.AnswerNumberingType;
import dcll.enumeration.QuestionTextFormat;
import dcll.exception.MalformedQuestionException;
import dcll.question.Cloze;
import dcll.question.Description;
import dcll.question.Essay;
import dcll.question.MultipleChoice;
import dcll.question.Numerical;
import dcll.question.ShortAnswer;
import dcll.question.TrueFalse;

public class IncorrectQuestionCreation extends TestCase {
	
	public void testQuestion(){
		try {
			new Description("text", "name", 0, 0, 2);
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testClozeAnswer(){
		ArrayList<RegularAnswer> oneAnswer = new ArrayList<RegularAnswer>();
		oneAnswer.add(new RegularAnswer("text", 0, "feed"));
		try {
			new Cloze("text", oneAnswer, "name");
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testClozeFormat(){
		try {
			new Cloze("text", new ArrayList<Answer>(), "name", QuestionTextFormat.HTML);
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testDescriptionFormat(){
		try {
			new Description("text", "name", QuestionTextFormat.NONE);
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testEssayAnswer(){
		ArrayList<RegularAnswer> twoAnswer = new ArrayList<RegularAnswer>();
		twoAnswer.add(new RegularAnswer("", 0, "feed"));
		twoAnswer.add(new RegularAnswer("", 0, "feed"));
		
		try {
			new Essay("text", twoAnswer, "name");
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testEssayText(){
		ArrayList<RegularAnswer> oneAnswer = new ArrayList<RegularAnswer>();
		oneAnswer.add(new RegularAnswer("text", 0, "feed"));
		
		try {
			new Essay("text", oneAnswer, "name");
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testEssayFraction(){
		ArrayList<RegularAnswer> oneAnswer = new ArrayList<RegularAnswer>();
		oneAnswer.add(new RegularAnswer("", 1, "feed"));
		
		try {
			new Essay("text", oneAnswer, "name");
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testMultipleChoiceShuffle(){
		try {
			new MultipleChoice("text", "name", new ArrayList<Answer>(), 2, false, AnswerNumberingType.ABC);
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	
	public void testNonShortAnswer(){
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("0", 100));
		a.add(new RegularAnswer("0", 100));
		
		try {
			new Numerical("text", a, "name");
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testNumericalAnswerText(){
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("string", 100));
		
		try {
			new Numerical("text", a, "name");
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testShortAnswerAnswers(){
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("0", 100));
		a.add(new RegularAnswer("0", 0));
		
		try {
			new ShortAnswer("text", a, "name", 0);
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testShortAnswerUsecase(){
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("0", 100));
		a.add(new RegularAnswer("0", 100));
		try {
			new ShortAnswer("text", "ICI", 2);
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testTrueFalseAnswers(){
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("0", 0));
		try {
			new TrueFalse("text", a, "name");
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}
	
	public void testTrueFalseFraction(){
		ArrayList<RegularAnswer> a = new ArrayList<RegularAnswer>();
		a.add(new RegularAnswer("0", 1));
		a.add(new RegularAnswer("0", 0));
		try {
			new TrueFalse("text", a, "name");
		} catch (MalformedQuestionException e) {
			assert(true);
		}
	}

}
