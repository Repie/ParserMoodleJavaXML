package dcll.answer;

import org.jdom.Element;

import dcll.interfaces.Parsable;


public abstract class Answer implements Parsable{
	protected String text;

	public Answer(String text){
		super();
		
		this.text = text;
	}

	public Answer(Element next) {
		text = next.getChildText("text");
	}

	public String getText() {
		return text;
	}
}
