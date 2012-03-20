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

public class Essay extends Question {

	public Essay(String text, ArrayList<RegularAnswer> answers, String name,
			QuestionTextFormat format) throws MalformedQuestionException {
		super(QuestionType.ESSAY, text, answers, name, format);

		verify();
	}

	// public Essay(String text, RegularAnswer answer, String name,
	// QuestionTextFormat format) throws MalformedQuestionException {
	// super(QuestionType.ESSAY, text, name, format);
	// ArrayList<Answer> temp = new ArrayList<Answer>();
	// temp.add(answer);
	// this.answers = temp;
	//
	// verify();
	// }

	public Essay(String text, ArrayList<RegularAnswer> answers, String name)
			throws MalformedQuestionException {
		super(QuestionType.ESSAY, text, answers, name);

		verify();
	}

	// public Essay(String text, RegularAnswer answer, String name) throws
	// MalformedQuestionException {
	// super(QuestionType.ESSAY, text, name);
	// ArrayList<Answer> temp = new ArrayList<Answer>();
	// temp.add(answer);
	// this.answers = temp;
	//
	// verify();
	// }

	public Essay(String text, String name, String generalFeedback,
			ArrayList<? extends Answer> answers, QuestionTextFormat format,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super(QuestionType.ESSAY, text, name, generalFeedback, answers, format,
				defaultGrade, penalty, hidden);

		verify();
	}

	public Essay(Element e) {
		super(e);
		type = QuestionType.ESSAY;
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

		RegularAnswer firstAnswer = (RegularAnswer) answers.get(0);

		if (answers.size() != 1)
			throw new MalformedQuestionException("Must have only one answer",
					this);
		else if (!firstAnswer.getText().isEmpty())
			throw new MalformedQuestionException("Answer's text must be empty",
					this);
		else if (firstAnswer.getFraction() != 0)
			throw new MalformedQuestionException("Answer's fraction must be 0",
					this);
		else if (format.equals(QuestionTextFormat.NONE))
			throw new MalformedQuestionException(
					"Question text format is void, use Cloze question in this case",
					this);
	}

}

// Note: prior to 1.7.2 the fraction was expressed as a value between 0 and 1 in
// a <fraction> element and the answer value was not enclosed in <text> tags.
// This format of the essay question type is deprecated but will still be
// correctly imported if found (for now)