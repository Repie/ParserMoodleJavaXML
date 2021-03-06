package dcll.parser;

import dcll.interfaces.Xmltojava;
import dcll.question.*;
import dcll.quiz.Quiz;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.filter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Implementation of the Xmltojava interface
 * 
 * @author Julien
 * 
 */
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
			System.err.println("couldn't open file");
		}

		// Initialisation of a new element root with the root element of the
		// documents
		racine = document.getRootElement();

		return parseAll();
	}

	/**
	 * Function handle the xml file
	 * 
	 * @return Quiz object
	 */
	private Quiz parseAll() {

		// Creation of a list of all the children of the root
		List listQuestion = racine.getChildren("question");
		ArrayList<Question> lquest = new ArrayList<Question>();
		Question temp;
		// Creation of an iterator on our list
		Iterator i = listQuestion.iterator();
		while (i.hasNext()) {
			// Parse of the current element to add it on the list of question
			temp = ParseQuestion((Element) i.next());
			if (temp != null)
				lquest.add(temp);
		}

		return new Quiz(lquest);
	}

	/**
	 * Parse JDOM's Element to Question. The element root must be a question's
	 * balise
	 * 
	 * @param e
	 * @return Question parsed
	 */
	private Question ParseQuestion(Element e) {

		Question question = null;
		// Looking for the type of question and treat it
		String type = new String(e.getAttributeValue("type"));
		if (type.equals("cloze")) {

			question = new Cloze(e);

		} else if (type.equals("description")) {

			question = new Description(e);

		} else if (type.equals("essay")) {

			question = new Essay(e);

		} else if (type.equals("matching")) {

			question = new Matching(e);

		} else if (type.equals("multichoice")) {

			question = new MultipleChoice(e);

		} else if (type.equals("numerical")) {

			question = new Numerical(e);

		} else if (type.equals("shortanswer")) {

			question = new ShortAnswer(e);

		} else if (type.equals("truefalse")) {

			question = new TrueFalse(e);

		} else {
			// Type of question unknown
			System.out.println(type
					+ " : Type de question pas encore pris en charge");

		}

		return question;
	}
}
