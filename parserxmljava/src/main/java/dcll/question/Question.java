package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;


import dcll.answer.*;
import dcll.enumeration.*;
import dcll.exception.MalformedQuestionException;
import dcll.interfaces.*;



public abstract class Question implements Parsable, Verifier{
	protected QuestionType type;
	protected String text, name, generalFeedback = new String("");
	protected ArrayList<? extends Answer> answers;
	protected QuestionTextFormat format = QuestionTextFormat.HTML;
	protected float defaultGrade = 0;
	protected float penalty = 0;
	protected int hidden = 0;
	
	//ajouter un constructeur pour les nouvelles balises dans les classes filles
	
	public Question(String text, ArrayList<? extends Answer> answers, String name, QuestionTextFormat format) throws MalformedQuestionException  {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
		this.format = format;
	}
	
	public Question(String text, ArrayList<? extends Answer> answers, String name) throws MalformedQuestionException  {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
	}

	
	public Question(String text, String name, QuestionTextFormat format) throws MalformedQuestionException  {
		super();
		this.text = text;
		this.answers = new ArrayList<Answer>();
		this.name = name;
		this.format = format;
	}
	
	public Question(String text, String name) throws MalformedQuestionException  {
		super();
		this.text = text;
		this.answers = new ArrayList<Answer>();
		this.name = name;
	}
	
	public Question(String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super();
		this.text = text;
		this.name = name;
		this.generalFeedback = generalFeedback;
		this.answers = answers;
		this.format = format;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
	}
	
	public Question(String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super();
		this.text = text;
		this.name = name;
		this.generalFeedback = generalFeedback;
		this.answers = answers;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
	}
	
	public Question(String text, String name, ArrayList<? extends Answer> answers, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super();
		this.text = text;
		this.name = name;
		this.generalFeedback = new String("");
		this.answers = answers;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
	}
	

	public QuestionType getType(){
		return type;
	}


	public String toString(){
		return (name);
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
			double fraction = ((RegularAnswer)answers.get(i)).getFraction();
			
			if(fraction == 100){
				if (hundredFraction)
					return false;
				else
					hundredFraction = true;
			}
				
		}
		
		return true;
	}
	
	public static String valueOf(double x){
		int integerX = (int) x;
		
		if(integerX == x)
			return String.valueOf(integerX);
		else
			return String.valueOf(x);
	}
	
	public Element parse(){
		return parse(true);
	}
	
	public Element parse(boolean parseAnswer){
		Element q = new Element("question");
		q.setAttribute("type", type.toString().toLowerCase());
		
		Element e_name = new Element("name");
		Element e_text = new Element("text").setText(name);
		e_name.addContent(e_text);
		q.addContent(e_name);
		
		Element q_text = new Element("questiontext");
		
		if(!format.equals(QuestionTextFormat.NONE))
			q_text.setAttribute("format", format.toString().toLowerCase());
		
		Element e_text2 = new Element("text").setText(text);
		q_text.addContent(e_text2);
		q.addContent(q_text);
		
		Element e_gfeed = new Element("generalfeedback");
		Element e_gfeed_text = new Element("text").setText(generalFeedback);
		e_gfeed.addContent(e_gfeed_text);
		q.addContent(e_gfeed);
		
		Element e_grade = new Element("defaultgrade").setText(valueOf(defaultGrade));
		q.addContent(e_grade);
		
		Element e_penalty = new Element("penalty").setText(valueOf(penalty));
		q.addContent(e_penalty);
		
		Element e_hidden = new Element("hidden").setText(valueOf(hidden));
		q.addContent(e_hidden);
		
		
		
		if(parseAnswer){
			for(Answer a : answers) //normalement il faut au moins une answer pour tout sauf Cloze
				q.addContent(a.parse());
		}
			
		return q;
	}
}
