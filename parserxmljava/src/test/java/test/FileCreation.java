package test;

import junit.framework.TestCase;
import dcll.exception.MalformedQuestionException;
import dcll.parser.JavatoXMLParser;
import dcll.quiz.Quiz;

/**
 * Test the parsing of a quiz into a file
 */
public class FileCreation extends TestCase {
	public void testFileCreation() throws MalformedQuestionException {
		Quiz q = new Quiz();

		new JavatoXMLParser().parse(q, "quiz.xml");
	}

}
