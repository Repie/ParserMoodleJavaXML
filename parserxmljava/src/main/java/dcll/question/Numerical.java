package dcll.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.answer.NumericalAnswer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class Numerical extends Question {

	public Numerical(String text, ArrayList<RegularAnswer> answers,
			String name, QuestionTextFormat format)
			throws MalformedQuestionException {
		super(QuestionType.NUMERICAL, text, answers, name, format);

		verify();
	}

	public Numerical(String text, ArrayList<RegularAnswer> answers, String name)
			throws MalformedQuestionException {
		super(QuestionType.NUMERICAL, text, answers, name);

		verify();
	}

	public Numerical(String text, String name, String generalFeedback,
			ArrayList<? extends Answer> answers, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super(QuestionType.NUMERICAL, text, name, generalFeedback, answers,
				defaultGrade, penalty, hidden);

		verify();
	}

	public Numerical(String text, String name, String generalFeedback,
			ArrayList<? extends Answer> answers, QuestionTextFormat format,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super(QuestionType.NUMERICAL, text, name, generalFeedback, answers,
				format, defaultGrade, penalty, hidden);

		verify();
	}

	public Numerical(Element e) {
		super(e);
		// Handling of the answer balise
		List eAnswer = e.getChildren("answer");
		ArrayList<Answer> lAnswer = new ArrayList<Answer>();
		Iterator i = eAnswer.iterator();
		while (i.hasNext()) {
			// Parse of the current element to add it on the list of answer
			lAnswer.add(new NumericalAnswer((Element) i.next()));
		}
		answers = lAnswer;
	}

	public void verify() throws MalformedQuestionException {
		super.verify();

		if (this.countCorrectAnswers() > 1)
			throw new MalformedQuestionException(
					"Can't have more than one correct answer, use ShortAnswer instead",
					this);

		for (Answer a : answers) {
			try {
				Integer.parseInt(a.getText());
			} catch (NumberFormatException e) {
				throw new MalformedQuestionException(
						"Answers's text must be an integer", this);
			}
		}
	}

}
