package dcll.parser;

import dcll.interfaces.Xmltojava;
import dcll.question.Question;
import dcll.quiz.Quiz;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.filter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Xmltojavaparser implements Xmltojava {

	static org.jdom.Document document;
	static Element racine;

	public Quiz parse(String filename) {

		// Instance of SAXBuilder's creation
		SAXBuilder sxb = new SAXBuilder();
		try {
			// New JDOM documents with the filename of the xml
			document = sxb.build(new File(filename));
		} catch (Exception e) {
		}

		// Initialisation of a new element root with the root element of the
		// documents
		racine = document.getRootElement();

		return parseAll();
	}

	public Quiz parseAll() {

		// Creation of a list of all the children of the root
		List listQuestion = racine.getChildren();
		ArrayList<Question> lquest = new ArrayList<Question>();

		// Creation of an iterator on our list
		Iterator i = listQuestion.iterator();
		while (i.hasNext()) {
			//Parse of the current element to add it on the list of question
			lquest.add(ParseQuestion((Element) i.next()));
		}

		return new Quiz(lquest);
	}

	private Question ParseQuestion(Element e) {
		
		// TODO Auto-generated method stub
		//Looking for the type of question and treat it
		return null;
	}
}
