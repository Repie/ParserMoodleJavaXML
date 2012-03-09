package dcll.question;

import dcll.enumeration.AnswerNumberingType;
import dcll.enumeration.QuestionType;



public class MultipleChoice extends Question {
	protected boolean shuffleAnswer;
	protected boolean single;
	protected String correctAnswer;
	protected String partialAnswer;
	protected String incorrectAnswer;
	protected AnswerNumberingType answerNumbering;
	
	public MultipleChoice(){
		super();
		type = QuestionType.MULTICHOICE;
	}
}
