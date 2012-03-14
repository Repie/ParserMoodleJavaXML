package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

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

	public void verify() throws MalformedQuestionException {
		if(this.hasOnlyOneCorrectAnswer())
			throw new MalformedQuestionException("Doesn't have multiple correct answers, don't use Shortanswer in this case", this);
		
	}
	

}
