package dcll.quiz;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class QuizWriter {	
	protected Quiz quiz;
	
	public QuizWriter(){
		quiz = new Quiz();
	}
	
	public QuizWriter(Quiz q){
		quiz = q;
	}
	
	public void setQuiz(Quiz q){
		quiz = q;
	}
	
	public Document generateDocument(){
		return generateDocument(quiz);
	}
	
	public static Document generateDocument(Quiz q){
		return new Document(q.parse());
	}
	
	public void write(){
		write(generateDocument());
	}
	
	public static void write(Element e){
		write(new Document(e));
	}
	
	public static void write(Document doc){
		XMLOutputter outputter = new XMLOutputter();
		
		try {
			outputter.output(doc, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Ajouter le choix de la sortie

}
