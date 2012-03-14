package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class ShortAnswer extends Question {
	protected int usecase;

	public ShortAnswer(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format, int usecase) throws MalformedQuestionException {
		super(text, answers, name, format);
		type = QuestionType.SHORTANSWER;
		this.usecase = usecase;
		
		verify();
	}

	public ShortAnswer(String text, ArrayList<Answer> answers, String name, int usecase) throws MalformedQuestionException {
		super(text, answers, name);
		type = QuestionType.SHORTANSWER;
		this.usecase = usecase;
		
		verify();
	}

	public void verify() throws MalformedQuestionException {
		if(this.hasOnlyOneCorrectAnswer())
			throw new MalformedQuestionException("Doesn't have multiple correct answers, don't use Shortanswer in this case", this);
		
	}
	
	public Element parse(){
		Element e = super.parse();
		
		Element usecase = new Element("usecase").setText(String.valueOf(this.usecase));
		e.addContent(usecase);
		
		return e;
	}
	

}
