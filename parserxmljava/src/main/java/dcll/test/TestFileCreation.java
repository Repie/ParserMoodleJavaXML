package dcll.test;

import java.io.IOException;

import org.jdom.output.XMLOutputter;

import dcll.quiz.Quiz;

public class TestFileCreation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Quiz qtest = new Quiz();
		XMLOutputter outputter = new XMLOutputter();
		
		try {
			outputter.output(qtest.parse(), System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
