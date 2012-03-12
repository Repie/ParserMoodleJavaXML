package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;


public class TrueFalse extends Question {
	
	//feedback opt

	public TrueFalse(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) throws MalformedQuestionException {
		super(text, answers, name, format);
		type = QuestionType.TRUEFALSE;
		verifIsTrueFalse();
	}

	public TrueFalse(String text, ArrayList<Answer> answers, String name) throws MalformedQuestionException {
		super(text, answers, name);
		type = QuestionType.TRUEFALSE;
		verifIsTrueFalse();
	}
	
	public void verifIsTrueFalse() throws MalformedQuestionException{
		if(answers.size() != 2)
			throw new MalformedQuestionException("Must have exactly 2 answers", this);
		else if(!this.hasOnlyOneCorrectAnswer())
			throw new MalformedQuestionException("Can't have more than one correct answer, use ShortAnswer instead", this);
		else{
			int firstFraction = ((RegularAnswer)answers.get(0)).getFraction();
			int secondFraction = ((RegularAnswer)answers.get(1)).getFraction();
			
			if ((firstFraction == 0 && secondFraction == 100) || (firstFraction == 100 && secondFraction == 0))
				throw new MalformedQuestionException("Fraction must be 0 and 100 only", this);
			
		}
	}
	
}