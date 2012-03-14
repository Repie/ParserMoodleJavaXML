package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;


public class Description extends Question {

	public Description(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) {
		super(text, answers, name, format);
		this.type = QuestionType.DESCRIPTIION;
	}

	public Description(String text, ArrayList<Answer> answers, String name) {
		super(text, answers, name);
		this.type = QuestionType.DESCRIPTIION;
	}

	public void verify() throws MalformedQuestionException {
		
	}
	
}
