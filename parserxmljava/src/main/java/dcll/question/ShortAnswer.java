package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;

public class ShortAnswer extends Question {
	protected boolean usecase; //???

	public ShortAnswer(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) {
		super(text, answers, name, format);
		type = QuestionType.SHORTANSWER;
	}

	public ShortAnswer(String text, ArrayList<Answer> answers, String name) {
		super(text, answers, name);
		type = QuestionType.SHORTANSWER;
	}
	

}
