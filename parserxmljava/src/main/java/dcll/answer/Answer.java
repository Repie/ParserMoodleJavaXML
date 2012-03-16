package dcll.answer;

import dcll.interfaces.Parsable;


public abstract class Answer implements Parsable{
	protected String text;

	public Answer(String text){
		super();
		
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
