package dcll.question;

import dcll.enumeration.QuestionType;

public class ShortAnswer extends Question {
	protected boolean usecase;
	
	public ShortAnswer(){
		super();
		type = QuestionType.SHORTANSWER;
	}
}
