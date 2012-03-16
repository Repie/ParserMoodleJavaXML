package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.AnswerNumberingType;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;



public class MultipleChoice extends Question {
	protected int shuffleAnswer;
	protected boolean single;
	protected AnswerNumberingType answerNumbering;
	protected String correctAnswer, partialAnswer, incorrectAnswer;
	
	public MultipleChoice(String text, ArrayList<RegularAnswer> answers, String name,
			QuestionTextFormat format, int shuffleAnswer, boolean single,
			AnswerNumberingType answerNumbering, String correctAnswer,
			String partialAnswer, String incorrectAnswer) throws MalformedQuestionException {
		super(text, answers, name, format);
		this.shuffleAnswer = shuffleAnswer;
		this.single = single;
		this.answerNumbering = answerNumbering;
		this.correctAnswer = correctAnswer;
		this.partialAnswer = partialAnswer;
		this.incorrectAnswer = incorrectAnswer;
		
		type = QuestionType.MULTICHOICE;
		
		verify();
	}

	public MultipleChoice(QuestionType type, String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, int shuffleAnswer, boolean single,
			AnswerNumberingType answerNumbering, String correctAnswer,
			String partialAnswer, String incorrectAnswer, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super( text, name, generalFeedback, answers, format, defaultGrade,
				penalty, hidden);
		
		this.shuffleAnswer = shuffleAnswer;
		this.single = single;
		this.answerNumbering = answerNumbering;
		this.correctAnswer = correctAnswer;
		this.partialAnswer = partialAnswer;
		this.incorrectAnswer = incorrectAnswer;
		
		type = QuestionType.MULTICHOICE;
		
		verify();
	}
	

	public MultipleChoice(QuestionType type, String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, int shuffleAnswer, boolean single,
			AnswerNumberingType answerNumbering, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super( text, name, generalFeedback, answers, format, defaultGrade,
				penalty, hidden);
		
		this.shuffleAnswer = shuffleAnswer;
		this.single = single;
		this.answerNumbering = answerNumbering;
		this.correctAnswer = new String("");
		this.partialAnswer = new String("");
		this.incorrectAnswer = new String("");
		
		type = QuestionType.MULTICHOICE;
		
		verify();
	}
	


	public Element parse(){
		Element e = super.parse();
		
		Element e_shuffle = new Element("shyuffleanswer").setText(String.valueOf(shuffleAnswer));
		Element e_single = new Element("single");
		
		if(single)
			e_single.setText("true");
		else
			e_single.setText("false");
		
		Element e_anb = new Element("answernumbering").setText(answerNumbering.toString());
		
		e.addContent(e_shuffle);
		e.addContent(e_single);
		e.addContent(e_anb);
		
		Element e_correct= new Element("correctfeedback");
		Element e_partial= new Element("partialfeedback");
		Element e_incorrect= new Element("incorrectfeedback");
		
		Element e_textc = new Element("text").setText(correctAnswer);
		Element e_textp = new Element("text").setText(partialAnswer);
		Element e_texti = new Element("text").setText(incorrectAnswer);
		
		e_correct.addContent(e_textc);
		e_partial.addContent(e_textp);
		e_incorrect.addContent(e_texti);
		
		e.addContent(e_correct);
		e.addContent(e_partial);
		e.addContent(e_incorrect);
		
		return e;
	}

	public void verify() throws MalformedQuestionException {
		
		if(!(shuffleAnswer == 0 || shuffleAnswer == 1))
			throw new MalformedQuestionException("Shuffle answer must be either 0 or 1", this);
		
	}

}