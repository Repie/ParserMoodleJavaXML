package dcll.question;

import java.util.ArrayList;

import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;


public class Essay extends Question {

	public Essay(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) throws MalformedQuestionException {
		super(text, answers, name, format);
		this.type = QuestionType.ESSAY;
		verifyIsEssay();
	}

	public Essay(String text, ArrayList<Answer> answers, String name) throws MalformedQuestionException {
		super(text, answers, name);
		this.type = QuestionType.ESSAY;
		verifyIsEssay();
	}
	
	public void verifyIsEssay() throws MalformedQuestionException{
		RegularAnswer firstAnswer = (RegularAnswer) answers.get(0);
		
		if(answers.size() == 1)
			throw new MalformedQuestionException("Must have only one answer", this);
		else if(firstAnswer.getText().isEmpty())
			throw new MalformedQuestionException("Answer's text must be empty", this);
		else if(firstAnswer.getFraction() == 0)
			throw new MalformedQuestionException("Answer's fraction must be 0", this);
		else if(!this.hasOnlyOneCorrectAnswer())
			throw new MalformedQuestionException("Can't have more than one correct answer, use ShortAnswer instead", this);
	}

	
}

//Note: prior to 1.7.2 the fraction was expressed as a value between 0 and 1 in a <fraction> element and the answer value was not enclosed in <text> tags. This format of the essay question type is deprecated but will still be correctly imported if found (for now)