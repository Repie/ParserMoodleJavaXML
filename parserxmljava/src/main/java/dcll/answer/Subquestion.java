package dcll.answer;

import org.jdom.Element;



public class Subquestion extends Answer {
	public Subquestion(String text) {
		super(text);
	}

	protected String answerText;

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
