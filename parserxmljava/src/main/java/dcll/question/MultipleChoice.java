package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.enumeration.AnswerNumberingType;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;



public class MultipleChoice extends Question {
	protected int shuffleAnswer;
	protected boolean single;
	protected AnswerNumberingType answerNumbering;
	//protected String correctAnswer, partialAnswer, incorrectAnswer;
	
	public MultipleChoice(String text, ArrayList<Answer> answers, String name) {
		super(text, answers, name);
		type = QuestionType.MULTICHOICE;
	}
	
	public Element parse(){
		Element e_parent = super.parse();
		
		Element e_shuffle = new Element("shyuffleanswer").setText(String.valueOf(shuffleAnswer));
		Element e_single = new Element("single");
		
		if(single)
			e_single.setText("true");
		else
			e_single.setText("false");
		
		Element e_anb = new Element("answernumbering").setText(answerNumbering.toString());
		
		e_parent.addContent(e_shuffle);
		e_parent.addContent(e_single);
		e_parent.addContent(e_anb);
		
		return e_parent;
	}

	public void verify() throws MalformedQuestionException {
		
	}

}