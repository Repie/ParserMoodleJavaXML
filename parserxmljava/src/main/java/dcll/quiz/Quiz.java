package dcll.quiz;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.interfaces.Parsable;
import dcll.question.Question;

/**
 * Moodle Quiz containing various questions
 */
public class Quiz implements Parsable {
	protected ArrayList<Question> questions;

	public Quiz() {
		questions = new ArrayList<Question>();
	}

	public Quiz(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(Question q) {
		questions.add(q);
	}

	public void addQuestions(ArrayList<Question> questions) {
		this.questions.addAll(questions);
	}

	public Element parse() {
		// Creating the xml document with quiz as the root element
		Element quiz = new Element("quiz");

		for (Question q : questions)
			quiz.addContent(q.parse());

		return quiz;
	}

}
