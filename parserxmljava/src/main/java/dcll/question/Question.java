package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;


import dcll.answer.*;
import dcll.enumeration.*;
import dcll.exception.MalformedQuestionException;
import dcll.interfaces.*;



public abstract class Question implements Parsable, Verifier{
	protected QuestionType type;
	protected String text, name, generalFeedback;
	protected ArrayList<? extends Answer> answers;
	protected QuestionTextFormat format = QuestionTextFormat.HTML;
	protected float defaultGrade, penalty;
	protected int hidden;
	
	//ajouter un constructeur pour les nouvelles balises dans les classes filles
	
	public Question(String text, ArrayList<? extends Answer> answers, String name, QuestionTextFormat format) throws MalformedQuestionException  {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
		this.format = format;
		generalFeedback = new String("");
		defaultGrade = 0;
		hidden = 0;
		penalty = 0;
		
		verify();
	}
	
	public Question(String text, ArrayList<? extends Answer> answers, String name) throws MalformedQuestionException  {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
		generalFeedback = new String("");
		defaultGrade = 0;
		hidden = 0;
		penalty = 0;
		
		verify();
	}

	
	public Question(String text, String name, QuestionTextFormat format) throws MalformedQuestionException  {
		super();
		this.text = text;
		this.answers = new ArrayList<Answer>();
		this.name = name;
		this.format = format;
		
		verify();
	}
	
	public Question(String text, String name) throws MalformedQuestionException  {
		super();
		this.text = text;
		this.answers = new ArrayList<Answer>();
		this.name = name;
		
		verify();
	}
	
	public Question(QuestionType type, String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super();
		this.type = type;
		this.text = text;
		this.name = name;
		this.generalFeedback = generalFeedback;
		this.answers = answers;
		this.format = format;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
		
		verify();
	}
	
	public Question(QuestionType type, String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super();
		this.type = type;
		this.text = text;
		this.name = name;
		this.generalFeedback = generalFeedback;
		this.answers = answers;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
		
		verify();
	}
	
	public Question(QuestionType type, String text, String name, ArrayList<? extends Answer> answers, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super();
		this.type = type;
		this.text = text;
		this.name = name;
		this.generalFeedback = new String("");
		this.answers = answers;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
		
		verify();
	}

	public String toString(){
		return (name + " [" + type.toString().toLowerCase() + "]");
	}
	
	public void verify() throws MalformedQuestionException{
		if(!(hidden == 0 || hidden == 1))
			throw new MalformedQuestionException("Hidden must be either 0 or 1", this);
	}
	
	/*public void addAnswer(Answer a){
		answers.add(a);
	}
	
	public void addAnswers(ArrayList<Answer> answers){
		this.answers.addAll(answers);
	}*/

	public boolean hasOnlyOneCorrectAnswer(){
		boolean hundredFraction = false;
		
		for (int i = 0; i < answers.size(); i++){
			float fraction = ((RegularAnswer)answers.get(i)).getFraction();
			
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
		
		Element e_hidden = new Element("hidden").setText(String.valueOf(hidden));
		q.addContent(e_hidden);
		
		
		
		if(parseAnswer){
			for(Answer a : answers) //normalement il faut au moins une answer (sinon exeption ?)
				q.addContent(a.parse());
		}
			
		return q;
	}
}
