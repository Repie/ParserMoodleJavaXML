package dcll.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class Cloze extends Question {

	public Cloze(String text, ArrayList<? extends Answer> answers, String name,
			QuestionTextFormat format) throws MalformedQuestionException {
		super(QuestionType.CLOZE, text, answers, name, format);

		verify();
	}

	public Cloze(String text, ArrayList<? extends Answer> answers, String name)
			throws MalformedQuestionException {
		super(QuestionType.CLOZE, text, answers, name);

		this.format = QuestionTextFormat.NONE;

		verify();
	}

	public Cloze(String text, String name, ArrayList<? extends Answer> answers,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super(QuestionType.CLOZE, text, name, answers, defaultGrade, penalty,
				hidden);

		this.format = QuestionTextFormat.NONE;

		verify();
	}

	public Cloze(String text, String name, String generalFeedback,
			ArrayList<? extends Answer> answers, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super(QuestionType.CLOZE, text, name, generalFeedback, answers,
				defaultGrade, penalty, hidden);

		this.format = QuestionTextFormat.NONE;

		verify();
	}

	public Cloze(String text, String name, String generalFeedback,
			ArrayList<? extends Answer> answers, QuestionTextFormat format,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super(QuestionType.CLOZE, text, name, generalFeedback, answers, format,
				defaultGrade, penalty, hidden);

		verify();
	}

	public Cloze(String text, String name, QuestionTextFormat format)
			throws MalformedQuestionException {
		super(QuestionType.CLOZE, text, name, format);

		verify();

	}

	public Cloze(String text, String name) throws MalformedQuestionException {
		super(QuestionType.CLOZE, text, name);

		this.format = QuestionTextFormat.NONE;
		verify();
	}

	/**
	 * Parse the specific balise of Cloze question type of moodle.
	 * JDOM element's root have to be a question balise
	 * @param e
	 */
	public Cloze(Element e) {
		super(e);
		type = QuestionType.CLOZE;
		// Handling of the answer balise
		List eAnswer = e.getChildren("answer");
		ArrayList<Answer> lAnswer = new ArrayList<Answer>();
		Iterator i = eAnswer.iterator();
		while (i.hasNext()) {
			// Parse of the current element to add it on the list of answer
			lAnswer.add(new RegularAnswer((Element) i.next()));
		}
		answers = lAnswer;
	}

	public void verify() throws MalformedQuestionException {
		super.verify();

		if (!answers.isEmpty())
			throw new MalformedQuestionException("Cloze must have no answer",
					this);
		else if (!format.equals(QuestionTextFormat.NONE))
			throw new MalformedQuestionException(
					"Question text format must be void", this);

	}

}
