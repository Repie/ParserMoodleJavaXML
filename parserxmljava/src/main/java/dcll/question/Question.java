package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;


import dcll.answer.*;
import dcll.enumeration.*;
import dcll.interfaces.*;



public abstract class Question implements Parsable, Verifier{
	protected QuestionType type;
	protected String text, name, generalFeedback;
	protected ArrayList<? extends Answer> answers;
	protected QuestionTextFormat format = QuestionTextFormat.HTML;
	protected int defaultGrade, hidden;
	protected float penalty;
	
	//ajouter un constructeur pour les nouvelles balises
	
	public Question(String text, ArrayList<Answer> answers, String name, QuestionTextFormat format) {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
		this.format = format;
		generalFeedback = new String();
		defaultGrade = 1;
		hidden = 0;
		penalty = 0;
	}
	
	public Question(String text, ArrayList<Answer> answers, String name) {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
		generalFeedback = new String();
		defaultGrade = 1;
		hidden = 0;
		penalty = 0;
	}

	public String toString(){
		return (name + " [" + type.toString().toLowerCase() + "]");
	}
	
	public Question(String text, String name, QuestionTextFormat format) {
		super();
		this.text = text;
		this.answers = new ArrayList<Answer>();
		this.name = name;
		this.format = format;
	}
	
	public Question(String text, String name) {
		super();
		this.text = text;
		this.answers = new ArrayList<Answer>();
		this.name = name;
	}
	
	public void addAnswer(Answer a){
		answers.add(a);
	}
	
	public void addAnswers(ArrayList<? extends Answer> answers){
		this.answers.addAll(answers);
	}

	public boolean hasOnlyOneCorrectAnswer(){
		boolean hundredFraction = false;
		
		for (int i = 0; i < answers.size(); i++){
			int fraction = ((RegularAnswer)answers.get(i)).getFraction();
			
			if(fraction == 100){
				if (hundredFraction)
					return false;
				else
					hundredFraction = true;
			}
				
		}
		
		return true;
	}
	public Element parse(){
		return parse(true);
	}
	
	public Element parse(boolean parseAnswer){
		Element q = new Element("question");
		q.setAttribute("type", type.toString().toLowerCase());
		
		if(!name.isEmpty()) {
			Element e_name = new Element("name");
			Element e_text = new Element("text").setText(name);
			e_name.addContent(e_text);
			q.addContent(e_name);
		}
		
		Element q_text = new Element("questiontext");
		
		if(!format.equals(QuestionTextFormat.NONE))
			q_text.setAttribute("format", format.toString().toLowerCase());
		
		Element e_text = new Element("text").setText(text);
		q_text.addContent(e_text);
		q.addContent(q_text);
		
		if(!generalFeedback.isEmpty()){
			Element e_gfeed = new Element("generalfeedback");
			Element e_gfeed_text = new Element("text").setText(generalFeedback);
			e_gfeed.addContent(e_gfeed_text);
			q.addContent(e_gfeed);
		}
		
		Element e_grade = new Element("defaultgrade").setText(String.valueOf(defaultGrade));
		q.addContent(e_grade);
		
		Element e_penalty = new Element("penalty").setText(String.valueOf(penalty));
		q.addContent(e_penalty);
		
		Element e_hidden = new Element("hidden").setText(String.valueOf(penalty));
		q.addContent(e_hidden);
		
		
		
		if(parseAnswer){
			for(Answer a : answers) //normalement il faut au moins une answer (sinon exeption ?)
				q.addContent(a.parse());
		}
			
		return q;
	}
}
