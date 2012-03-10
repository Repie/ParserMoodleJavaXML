package dcll.exception;

import dcll.question.Question;

public class MalformedQuestionException extends Exception {

	private static final long serialVersionUID = 1L;

	public MalformedQuestionException(String message, Question q) {
		super(message);
		System.err.println("MalformedQuestionException : question " + q + " doesn't respect the following constraint : " + "message");
	}


}
