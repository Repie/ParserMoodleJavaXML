package dcll.question;

import java.util.ArrayList;

import org.jdom.Element;

import dcll.answer.*;
import dcll.enumeration.*;
import dcll.exception.MalformedQuestionException;
import dcll.interfaces.*;

/**
 * Abstract question mother class containing what's common to all questions
 */
public abstract class Question implements Parsable, Verifier {
	protected QuestionType type;
	protected String text, name, generalFeedback = new String("");
	protected ArrayList<? extends Answer> answers;
	protected QuestionTextFormat format = QuestionTextFormat.HTML;
	protected float defaultGrade = 0;
	protected float penalty = 0;
	protected int hidden = 0;
	protected String image = new String("");
	protected String image_base64 = new String("");

	// ajouter un constructeur pour les nouvelles balises dans les classes
	// filles

	public Question(QuestionType type, String text,
			ArrayList<? extends Answer> answers, String name,
			QuestionTextFormat format) throws MalformedQuestionException {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
		this.format = format;
		this.type = type;
	}

	public Question(QuestionType type, String text,
			ArrayList<? extends Answer> answers, String name)
			throws MalformedQuestionException {
		super();
		this.text = text;
		this.answers = answers;
		this.name = name;
		this.type = type;
	}

	public Question(QuestionType type, String text, String name,
			QuestionTextFormat format) throws MalformedQuestionException {
		super();
		this.text = text;
		this.answers = new ArrayList<Answer>();
		this.name = name;
		this.format = format;
		this.type = type;
	}

	public Question(QuestionType type, String text, String name)
			throws MalformedQuestionException {
		super();
		this.text = text;
		this.answers = new ArrayList<Answer>();
		this.name = name;
		this.type = type;
	}

	public Question(QuestionType type, String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			QuestionTextFormat format, float defaultGrade, float penalty,
			int hidden) throws MalformedQuestionException {
		super();
		this.text = text;
		this.name = name;
		this.generalFeedback = generalFeedback;
		this.answers = answers;
		this.format = format;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
		this.type = type;
	}

	public Question(QuestionType type, String text, String name,
			String generalFeedback, ArrayList<? extends Answer> answers,
			float defaultGrade, float penalty, int hidden)
			throws MalformedQuestionException {
		super();
		this.text = text;
		this.name = name;
		this.generalFeedback = generalFeedback;
		this.answers = answers;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
		this.type = type;
	}

	public Question(QuestionType type, String text, String name,
			ArrayList<? extends Answer> answers, float defaultGrade,
			float penalty, int hidden) throws MalformedQuestionException {
		super();
		this.text = text;
		this.name = name;
		this.generalFeedback = new String("");
		this.answers = answers;
		this.defaultGrade = defaultGrade;
		this.penalty = penalty;
		this.hidden = hidden;
		this.type = type;
	}

	/**
	 * Parse a JDOM's Element to Question.
	 * This constructor only parse the common balises to all Question type except answer balise.
	 * The Element root have to be a question's balise
	 * @param e
	 */
	public Question(Element e) {
		String formatQuestion;

		Element eName = e.getChild("name");
		// Handling of the name balise
		if (eName != null) {
			name = eName.getChildText("text");
		}

		Element eGeneralFeedback = e.getChild("generalfeedback");
		// Handling of the generalfeedback balise
		if (eGeneralFeedback != null) {
			generalFeedback = eGeneralFeedback.getChildText("text");
		}

		Element eQuestionText = e.getChild("questiontext");
		// Handling of the questiontext balise
		if (eQuestionText != null) {
			text = eQuestionText.getChildText("text");
			if ((formatQuestion = eQuestionText.getAttributeValue("format")) != null) {
				if (formatQuestion.equals("html"))
					format = QuestionTextFormat.HTML;
				else if (formatQuestion.equals("moodle_auto_format"))
					format = QuestionTextFormat.MOODLE_AUTO_FORMAT;
				else if (formatQuestion.equals("plain_text"))
					format = QuestionTextFormat.PLAIN_TEXT;
				else if (formatQuestion.equals("markdown"))
					format = QuestionTextFormat.MARKDOWN;
				else
					format = QuestionTextFormat.NONE;
			}
			else
				format = QuestionTextFormat.NONE;
		}

		// Handling of the defaultgrade balise
		Element eDefaultGrade = e.getChild("defaultgrade");
		if (eDefaultGrade != null) {
			defaultGrade = Float.parseFloat(eDefaultGrade.getText());
		}

		// Handling of the penalty balise
		Element ePenalty = e.getChild("penalty");
		if (ePenalty != null) {
			penalty = Float.parseFloat(ePenalty.getText());
		}

		// Handling of the hidden balise
		Element eHidden = e.getChild("hidden");
		if (eHidden != null) {
			hidden = Integer.parseInt(eHidden.getText());
		}

		// Handling of the image balise
		Element eImage = e.getChild("image");
		if (eImage != null) {
			image = eImage.getText();
		}

		// Handling of the image_base64 balise
		Element eImageBase64 = e.getChild("image_base64");
		if (eImageBase64 != null) {
			image_base64 = eImageBase64.getText();
		}
	}

	public QuestionType getType() {
		return type;
	}

	public String toString() {
		return (type.toString().toLowerCase() + " question \"" + name + "\"");
	}

	public void verify() throws MalformedQuestionException {
		if (!(hidden == 0 || hidden == 1))
			throw new MalformedQuestionException(
					"Hidden must be either 0 or 1", this);
	}

	/**
	 * Returns the number of correct answers (ie. with a fraction value of 100) 
	 */
	public int countCorrectAnswers() {
		int correctAnswers = 0;

		for (int i = 0; i < answers.size(); i++) {
			double fraction = ((RegularAnswer) answers.get(i)).getFraction();

			if (fraction == 100)
				correctAnswers++;
		}

		return correctAnswers;
	}

	public void setImage(String image, String image_base64) {
		this.image = image;
		this.image_base64 = image_base64;
	}

	public boolean hasOnlyOneCorrectAnswer() {
		return countCorrectAnswers() == 1;
	}

	/**
	 * Hides the decimals (ie ".0") if there are none
	 */
	public static String valueOf(float x) {
		int integerX = (int) x;

		if (integerX == x)
			return String.valueOf(integerX);
		else
			return String.valueOf(x);
	}

	public Element parse() {
		return parse(true);
	}

	public Element parse(boolean parseAnswer) {
		Element q = new Element("question");
		q.setAttribute("type", type.toString().toLowerCase());

		Element e_name = new Element("name");
		Element e_text = new Element("text").setText(name);
		e_name.addContent(e_text);
		q.addContent(e_name);

		Element q_text = new Element("questiontext");

		if (!format.equals(QuestionTextFormat.NONE))
			q_text.setAttribute("format", format.toString().toLowerCase());

		Element e_text2 = new Element("text").setText(text);
		q_text.addContent(e_text2);
		q.addContent(q_text);

		Element e_img = new Element("image").setText(image);

		if (!image.isEmpty()) {
			Element e_img64 = new Element("image_base64").setText(image_base64);
			e_img.addContent(e_img64);
		}

		q.addContent(e_img);

		Element e_gfeed = new Element("generalfeedback");
		Element e_gfeed_text = new Element("text").setText(generalFeedback);
		e_gfeed.addContent(e_gfeed_text);
		q.addContent(e_gfeed);

		Element e_grade = new Element("defaultgrade")
				.setText(valueOf(defaultGrade));
		q.addContent(e_grade);

		Element e_penalty = new Element("penalty").setText(valueOf(penalty));
		q.addContent(e_penalty);

		Element e_hidden = new Element("hidden").setText(valueOf(hidden));
		q.addContent(e_hidden);

		if (parseAnswer) {
			for (Answer a : answers)
				// normalement il faut au moins une answer pour tout sauf Cloze
				q.addContent(a.parse());
		}

		return q;
	}
}
