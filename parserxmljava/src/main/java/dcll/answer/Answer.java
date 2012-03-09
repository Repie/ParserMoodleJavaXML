package dcll.answer;

import dcll.interfaces.Parser;


public abstract class Answer implements Parser{
	protected String text;

	public Answer(String text) {
		super();
		this.text = text;
	}
}
