package dcll.quiz;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;

import dcll.interfaces.Parsable;
import dcll.question.Question;


public class Quiz implements Parsable{
	protected ArrayList<Question> questions;
	
	public Element parse(){
		//Creating the xml document with quiz as the root element
		Element quiz = new Element("quiz");
		
		for(Question q : questions)
			quiz.addContent(q.parse());
		
		return quiz;
	}
	
}

/*Pour créer le fichier xml :
XMLOutputter outputter = new XMLOutputter();
try {
  serializer.setIndent("  "); // use two space indent
  serializer.setNewlines(true); 

  outputter.output(doc, System.out);       
}
catch (IOException e) {
  System.err.println(e);
}*/