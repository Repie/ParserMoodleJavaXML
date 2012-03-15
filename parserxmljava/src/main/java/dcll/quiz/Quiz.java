package dcll.quiz;
import java.util.ArrayList;

import org.jdom.Element;

import dcll.interfaces.Parsable;
import dcll.question.Question;



public class Quiz implements Parsable{
	protected ArrayList<Question> questions;
	
	public Quiz(){
		questions = new ArrayList<Question>();
	}
	
	public Quiz(ArrayList<Question> questions){
		this.questions = questions;
	}
	
	public void addQuestions(Question q){
		questions.add(q);
	}
	
	public void addQuestion(ArrayList<Question> questions){
		this.questions.addAll(questions);
	}
	
	public Element parse(){
		//Creating the xml document with quiz as the root element
		Element quiz = new Element("quiz");
		
		for(Question q : questions)
			quiz.addContent(q.parse());
		
		return quiz;
	}
	
}
