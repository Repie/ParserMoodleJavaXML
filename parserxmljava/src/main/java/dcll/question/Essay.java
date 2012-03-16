package dcll.question;

import java.util.ArrayList;


import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;


public class Essay extends Question {
	
	public Essay(String text, ArrayList<RegularAnswer> answers, String name, QuestionTextFormat format) throws MalformedQuestionException {
		super(text, answers, name, format);
		this.type = QuestionType.ESSAY;
		verify();
	}
	
//	public Essay(String text, RegularAnswer answer, String name, QuestionTextFormat format) throws MalformedQuestionException {
//		super(text, name, format);
//		ArrayList<Answer> temp = new ArrayList<Answer>();
//		temp.add(answer);
//		this.answers = temp;
//		this.type = QuestionType.ESSAY;
//		verify();
//	}

	public Essay(String text, ArrayList<RegularAnswer> answers, String name) throws MalformedQuestionException {
		super(text, answers, name);
		this.type = QuestionType.ESSAY;
		verify();
	}
	
//	public Essay(String text, RegularAnswer answer, String name) throws MalformedQuestionException {
//		super(text, name);
//		ArrayList<Answer> temp = new ArrayList<Answer>();
//		temp.add(answer);
//		this.answers = temp;
//		this.type = QuestionType.ESSAY;
//		verify();
//	}
	
	
	public Essay(String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super( text, name, generalFeedback, answers, format, defaultGrade,
				penalty, hidden);
		this.type = QuestionType.ESSAY;
		verify();
	}

	public void verify() throws MalformedQuestionException{
		RegularAnswer firstAnswer = (RegularAnswer) answers.get(0);
		
		if(answers.size() != 1)
			throw new MalformedQuestionException("Must have only one answer", this);
		else if(!firstAnswer.getText().isEmpty())
			throw new MalformedQuestionException("Answer's text must be empty", this);
		else if(firstAnswer.getFraction() != 0)
			throw new MalformedQuestionException("Answer's fraction must be 0", this);
		else if(!this.hasOnlyOneCorrectAnswer())
			throw new MalformedQuestionException("Can't have more than one correct answer, use ShortAnswer instead", this);
		else if(format.equals(QuestionTextFormat.NONE))
			throw new MalformedQuestionException("Question text format is void, use Cloze question in this case", this);
	}

	
}

//Note: prior to 1.7.2 the fraction was expressed as a value between 0 and 1 in a <fraction> element and the answer value was not enclosed in <text> tags. This format of the essay question type is deprecated but will still be correctly imported if found (for now)