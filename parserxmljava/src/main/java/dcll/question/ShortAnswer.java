package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class ShortAnswer extends Question {
	
	protected int usecase;

	public ShortAnswer(String text, ArrayList<RegularAnswer> answers, String name, QuestionTextFormat format, int usecase) throws MalformedQuestionException {
		super(QuestionType.SHORTANSWER, text, answers, name, format);
		
		this.usecase = usecase;
		
		verify();
	}

	public ShortAnswer(String text, ArrayList<RegularAnswer> answers, String name, int usecase) throws MalformedQuestionException {
		super(QuestionType.SHORTANSWER, text, answers, name);
		
		this.usecase = usecase;
		
		verify();
	}
	
	public ShortAnswer(String text, String name, int usecase) throws MalformedQuestionException {
		super(QuestionType.SHORTANSWER, text, new  ArrayList<Answer>(), name);
		
		this.usecase = usecase;
		
		verify();
	}
	
	
	public ShortAnswer(String text, String name, int usecase,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super(QuestionType.SHORTANSWER,  text, name, generalFeedback, answers, format, defaultGrade,
				penalty, hidden);
		
		this.usecase = usecase;
		
		verify();
	}



	public ShortAnswer(Element e) {
		// TODO Auto-generated constructor stub
	}

	public void verify() throws MalformedQuestionException {
		super.verify();
		
		if(this.hasOnlyOneCorrectAnswer())
			throw new MalformedQuestionException("Doesn't have multiple correct answers, don't use Shortanswer in this case", this);
		else if(!(usecase == 0 || usecase == 1))
			throw new MalformedQuestionException("Usecase must be either 0 or 1", this); 
		
	}
	
	public Element parse(){
		Element e = super.parse();
		
		Element usecase = new Element("usecase").setText(super.valueOf(this.usecase));
		e.addContent(usecase);
		
		return e;
	}
	

}
