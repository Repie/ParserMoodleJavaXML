package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class Numerical extends Question{

	public Numerical(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) throws MalformedQuestionException {
		super(text, answers, name, format);
		this.type = QuestionType.NUMERICAL;
		verifyIsNumerical();
	}

	public Numerical(String text, ArrayList<Answer> answers, String name) throws MalformedQuestionException {
		super(text, answers, name);
		this.type = QuestionType.NUMERICAL;
		verifyIsNumerical();
	}
	
	public void verifyIsNumerical() throws MalformedQuestionException{
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
