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
		super(text, answers, name, format);
		type = QuestionType.SHORTANSWER;
		this.usecase = usecase;
		
		verify();
	}

	public ShortAnswer(String text, ArrayList<RegularAnswer> answers, String name, int usecase) throws MalformedQuestionException {
		super(text, answers, name);
		type = QuestionType.SHORTANSWER;
		this.usecase = usecase;
		
		verify();
	}
	
	public ShortAnswer(String text, String name, int usecase,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super( text, name, generalFeedback, answers, format, defaultGrade,
				penalty, hidden);
		type = QuestionType.SHORTANSWER;
		this.usecase = usecase;
		
		verify();
	}



	public void verify() throws MalformedQuestionException {
		super.verify();
		
		if(this.hasOnlyOneCorrectAnswer())
			throw new MalformedQuestionException("Doesn't have multiple correct answers, don't use Shortanswer in this case", this);
		
	}
	
	public Element parse(){
		Element e = super.parse();
		
		Element usecase = new Element("usecase").setText(super.valueOf(this.usecase));
		e.addContent(usecase);
		
		return e;
	}
	

}
