package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class Cloze extends Question {
	
	public Cloze(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) throws MalformedQuestionException {
		super(text, answers, name, format);
		this.type = QuestionType.CLOZE;
		
		verify();
	}

	public Cloze(String text, ArrayList<Answer> answers, String name) throws MalformedQuestionException {
		super(text, answers, name);
		this.type = QuestionType.CLOZE;
		
		verify();
	}

	public Cloze(QuestionType type, String text, String name,
			ArrayList<? extends Answer> answers, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super(type, text, name, answers, defaultGrade, penalty, hidden);
		this.type = QuestionType.CLOZE;
		
		verify();
	}

	public Cloze(QuestionType type, String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super(type, text, name, generalFeedback, answers, defaultGrade, penalty, hidden);
		this.type = QuestionType.CLOZE;
		
		verify();
	}

	public Cloze(QuestionType type, String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super(type, text, name, generalFeedback, answers, format, defaultGrade,
				penalty, hidden);
		this.type = QuestionType.CLOZE;
		
		verify();
	}

	public Cloze(String text, String name, QuestionTextFormat format)
			throws MalformedQuestionException {
		super(text, name, format);
		this.type = QuestionType.CLOZE;
		
		verify();
		
	}

	public Cloze(String text, String name) throws MalformedQuestionException {
		super(text, name);
		this.type = QuestionType.CLOZE;
		
		verify();
	}

	public void verify() throws MalformedQuestionException {
		if(!format.equals(QuestionTextFormat.NONE))
			throw new MalformedQuestionException("Question text format must be void", this);

	}

}
