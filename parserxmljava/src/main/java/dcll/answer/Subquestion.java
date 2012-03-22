package dcll.answer;

import org.jdom.Element;


/**
 * Answers used by the Matching questions
 */
public class Subquestion extends Answer {
	protected String answerText;
	
	public Subquestion(String text, String answerText) {
		super(text);
		this.answerText = answerText;
	}

	public Subquestion(Element next) {
		super(next);
		Element eAnswerText = next.getChild("answer");
		if(eAnswerText!=null)
			answerText = eAnswerText.getChildText("text");
	}

	/**
	 * Parse the balise of Subquestion type of moodle.
	 * JDOM element's root have to be a subquestion balise
	 * @param next
	 */
	public Element parse() {
		Element e = new Element("subquestion");
		
		Element e_text = new Element("text").setText(text);
		Element e_answer = new Element("answer");
		Element e_atext = new Element("text").setText(answerText);
		e_answer.addContent(e_atext);
		
		e.addContent(e_text);
		e.addContent(e_answer);
		
		return e;
	}
}
