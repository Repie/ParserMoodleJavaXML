package dcll.quiz;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;

import dcll.interfaces.Parser;
import dcll.question.Question;


public class Quiz{
	protected ArrayList<Question> questions;
	
	public Document parse(){
		Document doc = new Document();
		doc.setRootElement(new Element("quiz"));
		
		return doc;
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