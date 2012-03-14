package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.answer.Subquestion;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;



public class Matching extends Question {
	protected boolean shuffleAnswer;

	public Matching(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) {
		super(text, answers, name, format);
		type = QuestionType.MULTICHOICE;
	}

	public Matching(String text, ArrayList<Answer> answers, String name) {
		super(text, answers, name);
		type = QuestionType.MULTICHOICE;
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
		
	}

}