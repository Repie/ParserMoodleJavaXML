package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class Numerical extends Question{

	public Numerical(String text, ArrayList<RegularAnswer> answers, String name, QuestionTextFormat format) throws MalformedQuestionException {
		super(text, answers, name, format);
		this.type = QuestionType.NUMERICAL;
		verify();
	}

	public Numerical(String text, ArrayList<RegularAnswer> answers, String name) throws MalformedQuestionException {
		super(text, answers, name);
		this.type = QuestionType.NUMERICAL;
		verify();
	}
	
	public Numerical(String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super( text, name, generalFeedback, answers, defaultGrade, penalty, hidden);
		this.type = QuestionType.NUMERICAL;
		verify();
	}

	public Numerical(String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super( text, name, generalFeedback, answers, format, defaultGrade,
				penalty, hidden);
		this.type = QuestionType.NUMERICAL;
		verify();
	}

	public void verify() throws MalformedQuestionException{
		if(!this.hasOnlyOneCorrectAnswer())
			throw new MalformedQuestionException("Can't have more than one correct answer, use ShortAnswer instead", this);
		
		for(Answer a : answers){
			try{
				Integer.parseInt(a.getText());
			}
			catch(NumberFormatException e){
				throw new MalformedQuestionException("Answers's text must be an integer", this);
			}
		}
	}

}
