package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;


public class Description extends Question {
	public Description(String text, String name, QuestionTextFormat format) throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION, text, new ArrayList<Answer>(), name, format);
		
		verify();
	}

	public Description(String text, String name) throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION, text, new ArrayList<Answer>(), name);
		
		
		this.verify();
	}

	public Description(String text, String name, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION,  text, name, new ArrayList<Answer>(), defaultGrade, penalty, hidden);
		
		verify();
	}

	public Description(String text, String name,
			String generalFeedback,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION,  text, name, generalFeedback, new ArrayList<Answer>(), defaultGrade, penalty, hidden);
		
		verify();
	}

	public Description(String text, String name,
			String generalFeedback,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION,  text, name, generalFeedback, new ArrayList<Answer>(), format, defaultGrade,
				penalty, hidden);
		
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
