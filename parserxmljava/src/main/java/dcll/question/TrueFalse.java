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

public class TrueFalse extends Question {

	public TrueFalse(String text, ArrayList<RegularAnswer> answers,
			String name, QuestionTextFormat format)
			throws MalformedQuestionException {
		super(QuestionType.TRUEFALSE, text, answers, name, format);

		verify();
	}

	public TrueFalse(String text, ArrayList<RegularAnswer> answers, String name)
			throws MalformedQuestionException {
		super(QuestionType.TRUEFALSE, text, answers, name);

		verify();
	}

	public TrueFalse(String text, String name, String generalFeedback,
			ArrayList<? extends Answer> answers, QuestionTextFormat format,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super(QuestionType.TRUEFALSE, text, name, generalFeedback, answers,
				format, defaultGrade, penalty, hidden);

		verify();
	}

	public TrueFalse(Element e) {
		super(e);

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

		if (answers.size() != 2)
			throw new MalformedQuestionException("Must have exactly 2 answers",
					this);
		else if (this.countCorrectAnswers() > 1)
			throw new MalformedQuestionException(
					"Can't have more than one correct answer, use ShortAnswer instead",
					this);
		else {
			double firstFraction = ((RegularAnswer) answers.get(0))
					.getFraction();
			double secondFraction = ((RegularAnswer) answers.get(1))
					.getFraction();

			if (!((firstFraction == 0 && secondFraction == 100) || (firstFraction == 100 && secondFraction == 0)))
				throw new MalformedQuestionException(
						"Fraction must be 0 and 100 only", this);

		}
	}

}