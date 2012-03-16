package test;

import java.util.ArrayList;

import junit.framework.TestCase;
import dcll.answer.RegularAnswer;
import dcll.exception.MalformedQuestionException;
import dcll.question.Cloze;
import dcll.question.Description;

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

}
