package dcll.question;

import dcll.enumeration.QuestionType;



public class Matching extends Question {
	protected boolean suffleAnswer;
	
	public Matching(){
		super();
		type = QuestionType.MULTICHOICE;
	}
}