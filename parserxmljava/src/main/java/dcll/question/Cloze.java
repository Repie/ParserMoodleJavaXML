package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class Cloze extends Question {

	public Cloze(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) {
		super(text, answers, name, format);
		this.type = QuestionType.CLOZE;
	}

	public Cloze(String text, ArrayList<Answer> answers, String name) {
		super(text, answers, name);
		this.type = QuestionType.CLOZE;
	}

	public void verify() throws MalformedQuestionException {
		if(!format.equals(QuestionTextFormat.NONE))
			throw new MalformedQuestionException("Question text format must be void", this);

	}

}
