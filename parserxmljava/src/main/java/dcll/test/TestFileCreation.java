package dcll.test;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;

import dcll.quiz.Quiz;

public class TestFileCreation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Quiz qtest = new Quiz();
		Document doc = qtest.parse();
		
		
		XMLOutputter serializer = new XMLOutputter();
		try {
		  //serializer.setIndent("  "); // use two space indent
		  //serializer.setNewlines(true); 

			serializer.output(doc, System.out);       
		}
		catch (IOException e) {
		  System.err.println(e);
		}

	}

}
