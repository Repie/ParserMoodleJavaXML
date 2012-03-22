package dcll.answer;

import org.jdom.Element;

import dcll.interfaces.Parsable;

/**
 * Abstract mother answer class containing what's common to all answers
 */
public abstract class Answer implements Parsable{
	protected String text;

	public Answer(String text){
		super();
		
		this.text = text;
	}

	/**
	 * Parse the balise of Answer type of moodle.
	 * JDOM element's root have to be a answer balise
	 * @param next
	 */
	public Answer(Element next) {
		text = next.getChildText("text");
	}

	public String getText() {
		return text;
	}
}
