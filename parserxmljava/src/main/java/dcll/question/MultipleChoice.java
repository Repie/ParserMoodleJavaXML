package dcll.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

import dcll.answer.Answer;
import dcll.answer.RegularAnswer;
import dcll.enumeration.AnswerNumberingType;
import dcll.enumeration.QuestionTextFormat;
import dcll.enumeration.QuestionType;
import dcll.exception.MalformedQuestionException;

public class MultipleChoice extends Question {
	protected int shuffleAnswer;
	protected boolean single;
	protected AnswerNumberingType answerNumbering;
	protected String correctAnswer, partialAnswer, incorrectAnswer;

	public MultipleChoice(String text, ArrayList<RegularAnswer> answers,
			String name, QuestionTextFormat format, int shuffleAnswer,
			boolean single, AnswerNumberingType answerNumbering,
			String correctAnswer, String partialAnswer, String incorrectAnswer)
			throws MalformedQuestionException {
		super(QuestionType.MULTICHOICE, text, answers, name, format);
		this.shuffleAnswer = shuffleAnswer;
		this.single = single;
		this.answerNumbering = answerNumbering;
		this.correctAnswer = correctAnswer;
		this.partialAnswer = partialAnswer;
		this.incorrectAnswer = incorrectAnswer;

		verify();
	}

	public MultipleChoice(String text, String name, String generalFeedback,
			ArrayList<? extends Answer> answers, QuestionTextFormat format,
			int shuffleAnswer, boolean single,
			AnswerNumberingType answerNumbering, String correctAnswer,
			String partialAnswer, String incorrectAnswer, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super(QuestionType.MULTICHOICE, text, name, generalFeedback, answers,
				format, defaultGrade, penalty, hidden);

		this.shuffleAnswer = shuffleAnswer;
		this.single = single;
		this.answerNumbering = answerNumbering;
		this.correctAnswer = correctAnswer;
		this.partialAnswer = partialAnswer;
		this.incorrectAnswer = incorrectAnswer;

		verify();
	}

	public MultipleChoice(String text, String name, String generalFeedback,
			ArrayList<? extends Answer> answers, QuestionTextFormat format,
			int shuffleAnswer, boolean single,
			AnswerNumberingType answerNumbering, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super(QuestionType.MULTICHOICE, text, name, generalFeedback, answers,
				format, defaultGrade, penalty, hidden);

		this.shuffleAnswer = shuffleAnswer;
		this.single = single;
		this.answerNumbering = answerNumbering;
		this.correctAnswer = new String("");
		this.partialAnswer = new String("");
		this.incorrectAnswer = new String("");

		verify();
	}

	public MultipleChoice(String text, String name,
			ArrayList<? extends Answer> answers, int shuffleAnswer,
			boolean single, AnswerNumberingType answerNumbering)
			throws MalformedQuestionException {
		super(QuestionType.MULTICHOICE, text, answers, name);

		this.shuffleAnswer = shuffleAnswer;
		this.single = single;
		this.answerNumbering = answerNumbering;
		this.correctAnswer = new String("");
		this.partialAnswer = new String("");
		this.incorrectAnswer = new String("");

		verify();
	}

	public MultipleChoice(Element e) {
		super(e);
		type = QuestionType.MULTICHOICE;
		
		//Handling of the answer balise
		List eAnswer = e.getChildren("answer");
		ArrayList<Answer> lAnswer = new ArrayList<Answer>();
		Iterator i = eAnswer.iterator();
		while (i.hasNext()) {
			//Parse of the current element to add it on the list of answer
			lAnswer.add(new RegularAnswer((Element) i.next()));
		}
		answers=lAnswer;
		
		//Handling of shuffleAnswer
		Element eShuffleAnswer = e.getChild("shuffleanswer");
		if (eShuffleAnswer != null) {
			shuffleAnswer = Integer.valueOf(eShuffleAnswer.getText()).intValue();
		}
		//Handling of single
		Element eSingle = e.getChild("single");
		if (eSingle != null) {
			single = Boolean.valueOf(eSingle.getText()).booleanValue();
		}
		//Handling of AnswerNumberingType answerNumbering
		Element eAnswerNumberingType = e.getChild("answernumbering");
		if(eAnswerNumberingType!=null){
			String temp = eAnswerNumberingType.getText();
			if(temp.equals("none"))
				answerNumbering = AnswerNumberingType.NONE;
			else if(temp.equals("abc"))
				answerNumbering = AnswerNumberingType.ABC;
			else if(temp.equals("abcd"))
				answerNumbering = AnswerNumberingType.ABCD;
			else if(temp.equals("a123"))
				answerNumbering = AnswerNumberingType.A123;
			else
				answerNumbering = AnswerNumberingType.NONE;
		}
		else
			answerNumbering = AnswerNumberingType.NONE;
		
		//Handling of correctAnswer
		Element eCorrectAnswer = e.getChild("correctanswer");
		if(eCorrectAnswer!=null){
			correctAnswer = eCorrectAnswer.getText();
		}
		//Handling of partialAnswer
		Element ePartialAnswer = e.getChild("partialanswer");
		if(ePartialAnswer!=null){
			partialAnswer = ePartialAnswer.getText();
		}
		//Handling of incorrectAnswer
		Element eIncorrectAnswer = e.getChild("incorrectanswer");
		if(eIncorrectAnswer!=null){
			incorrectAnswer = eIncorrectAnswer.getText();
		}
		
		
		
			
			
	}

	public Element parse() {
		Element e = super.parse();

		Element e_shuffle = new Element("shyuffleanswer").setText(super
				.valueOf(shuffleAnswer));
		Element e_single = new Element("single");

		if (single)
			e_single.setText("true");
		else
			e_single.setText("false");

		Element e_anb = new Element("answernumbering").setText(answerNumbering
				.toString());

		e.addContent(e_shuffle);
		e.addContent(e_single);
		e.addContent(e_anb);

		Element e_correct = new Element("correctfeedback");
		Element e_partial = new Element("partialfeedback");
		Element e_incorrect = new Element("incorrectfeedback");

		Element e_textc = new Element("text").setText(correctAnswer);
		Element e_textp = new Element("text").setText(partialAnswer);
		Element e_texti = new Element("text").setText(incorrectAnswer);

		e_correct.addContent(e_textc);
		e_partial.addContent(e_textp);
		e_incorrect.addContent(e_texti);

		e.addContent(e_correct);
		e.addContent(e_partial);
		e.addContent(e_incorrect);

		return e;
	}

	public void verify() throws MalformedQuestionException {
		super.verify();

		if (!(shuffleAnswer == 0 || shuffleAnswer == 1))
			throw new MalformedQuestionException(
					"Shuffle answer must be either 0 or 1", this);
		else if (this.countCorrectAnswers() > 1)
			throw new MalformedQuestionException(
					"Can't have more than one correct answer, use ShortAnswer instead",
					this);

	}

}