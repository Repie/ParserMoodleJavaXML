package dcll.answer;

import org.jdom.Element;



public class Subquestion extends Answer {
	protected String answerText;
	
	public Subquestion(String text, String answerText) {
		super(text);
		this.answerText = answerText;
	}

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
