package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.interfaces.Parser;



public abstract class Question implements Parser{
	protected QuestionType type;
	protected String text;
	protected ArrayList<Answer> answers;
	protected String name;
	protected QuestionTextFormat format = QuestionTextFormat.HTML;
	
	public Question(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
		this.format = format;
	}
	
	public Question(String text, ArrayList<Answer> answers) {
		super();
		this.text = text;
		this.answers = answers;
		this.name = new String();
	}

	
	
	public Element parse(){
		Element q = new Element("question");
		q.setAttribute("type", type.toString().toLowerCase());
		
		if(!name.isEmpty()) {
			Element e_name = new Element("name");
			Element e_text = new Element("text").setText(name);
			e_name.addContent(e_text);
			q.addContent(e_name);
		}
		
		Element q_text = new Element("questiontext");
		q_text.setAttribute("format", format.toString().toLowerCase());
		
		Element e_text = new Element("text").setText(text);
		q_text.addContent(e_text);
		q.addContent(q_text);
		
		for(Answer a : answers)
			q.addContent(a.parse());
			
		return q;
	}
}
