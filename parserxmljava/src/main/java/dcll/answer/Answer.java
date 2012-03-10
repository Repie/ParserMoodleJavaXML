package dcll.answer;

import dcll.exception.EmptyArgumentException;
import dcll.interfaces.Parser;


public abstract class Answer implements Parser{
	protected String text;

	public Answer(String text) throws EmptyArgumentException {
		super();
		
		/*if(text.isEmpty())
			throw new EmptyArgumentException("The text arg from Answer constructor is empty");*/
		
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
