package dcll.answer;

import dcll.interfaces.Parsable;


public abstract class Answer implements Parsable{
	protected String text;

	public Answer(String text){
		super();
		
		/*if(text.isEmpty())
			throw new EmptyArgumentException("The text arg from Answer constructor is empty");*/
		
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
