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

public class Description extends Question {
	public Description(String text, String name, QuestionTextFormat format)
			throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION, text, new ArrayList<Answer>(), name,
				format);

		verify();
	}

	public Description(String text, String name)
			throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION, text, new ArrayList<Answer>(), name);

		this.verify();
	}

	public Description(String text, String name, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION, text, name, new ArrayList<Answer>(),
				defaultGrade, penalty, hidden);

		verify();
	}

	public Description(String text, String name, String generalFeedback,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION, text, name, generalFeedback,
				new ArrayList<Answer>(), defaultGrade, penalty, hidden);

		verify();
	}

	public Description(String text, String name, String generalFeedback,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super(QuestionType.DESCRIPTION, text, name, generalFeedback,
				new ArrayList<Answer>(), format, defaultGrade, penalty, hidden);

		verify();
	}

	public Description(Element e) {
		super(e);
		type = QuestionType.DESCRIPTION;
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
			throw new MalformedQuestionException(
					"Description must have no answer", this);
		else if (format.equals(QuestionTextFormat.NONE))
			throw new MalformedQuestionException(
					"Question text format is void, use Cloze question in this case",
					this);
	}

}
