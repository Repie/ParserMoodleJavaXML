package dcll.quiz;

import java.io.IOException;
import java.io.Writer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import dcll.interfaces.Parsable;

/**
 * Write a quiz into a file or on screen
 */
public class QuizWriter {
	protected Quiz quiz;

	public QuizWriter() {
		quiz = new Quiz();
	}

	public QuizWriter(Quiz q) {
		quiz = q;
	}

	public void setQuiz(Quiz q) {
		quiz = q;
	}

	public Document generateDocument() {
		return generateDocument(quiz);
	}

	/**
	 * Returns a JDom Document containing the quiz
	 */
	public static Document generateDocument(Quiz q) {
		return new Document(q.parse());
	}

	public void write() {
		write(generateDocument());
	}

	public static void write(Parsable p) {
		write(p.parse());
	}

	public static void write(Element e) {
		write(new Document(e));
	}

	/**
	 * Print a document on the screen
	 */
	public static void write(Document doc) {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

		try {
			outputter.output(doc, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print a document in a file
	 */
	public static void write(Document doc, Writer out) {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

		try {
			outputter.output(doc, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Ajouter le choix de la sortie

}
