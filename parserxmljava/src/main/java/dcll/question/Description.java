package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;


public class Description extends Question {
	public Description(String text, String name, QuestionTextFormat format) throws MalformedQuestionException {
		super(text, new ArrayList<Answer>(), name, format);
		this.type = QuestionType.DESCRIPTION;
		verify();
	}

	public Description(String text, String name) throws MalformedQuestionException {
		super(text, new ArrayList<Answer>(), name);
		this.type = QuestionType.DESCRIPTION;
		
		this.verify();
	}

	public Description(String text, String name, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super( text, name, new ArrayList<Answer>(), defaultGrade, penalty, hidden);
		this.type = QuestionType.DESCRIPTION;
		verify();
	}

	public Description(String text, String name,
			String generalFeedback,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super( text, name, generalFeedback, new ArrayList<Answer>(), defaultGrade, penalty, hidden);
		this.type = QuestionType.DESCRIPTION;
		verify();
	}

	public Description(String text, String name,
			String generalFeedback,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super( text, name, generalFeedback, new ArrayList<Answer>(), format, defaultGrade,
				penalty, hidden);
		this.type = QuestionType.DESCRIPTION;
		verify();
	}


	public void verify() throws MalformedQuestionException {
		super.verify();
		
		if(!answers.isEmpty())
			throw new MalformedQuestionException("Description must have no answer", this);
		else if(format.equals(QuestionTextFormat.NONE))
			throw new MalformedQuestionException("Question text format is void, use Cloze question in this case", this);
	}
	
}
