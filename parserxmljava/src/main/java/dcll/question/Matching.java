package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.answer.Subquestion;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;



public class Matching extends Question {

	protected boolean shuffleAnswer;

	public Matching(String text, ArrayList<Subquestion> answers, String name, QuestionTextFormat format, boolean shuffleAnswer) throws MalformedQuestionException {
		super(QuestionType.MATCHING, text, answers, name, format);
		
		this.shuffleAnswer = shuffleAnswer;
		
		verify();
	}

	public Matching(String text, ArrayList<Subquestion> answers, String name, boolean shuffleAnswer) throws MalformedQuestionException {
		super(QuestionType.MATCHING, text, answers, name);
		
		this.shuffleAnswer = shuffleAnswer;
		
		verify();
	}
	
	public Matching(String text, String name, boolean shuffleAnswer,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super(QuestionType.MATCHING,  text, name, generalFeedback, answers, format, defaultGrade,
				penalty, hidden);
		
		this.shuffleAnswer = shuffleAnswer;
		
		verify();
	}
	
	public Element parse(){
		Element e = super.parse(false);
		
		for(int i = 0; i < answers.size(); i++){
			Subquestion a = ((Subquestion)answers.get(i));
			e.addContent(a.parse());
		}
		
		Element e_shuffle = new Element("shuffleanswer");
		
		if(shuffleAnswer)
			e_shuffle.setText("true");
		else
			e_shuffle.setText("false");
		
		e.addContent(e_shuffle);
		
		return e;
	}

	public void verify() throws MalformedQuestionException {
		super.verify();
		
	}

}