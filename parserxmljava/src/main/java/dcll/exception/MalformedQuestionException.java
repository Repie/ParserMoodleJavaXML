package dcll.exception;

import dcll.question.Question;

public class MalformedQuestionException extends Exception {

	private static final long serialVersionUID = -6082770253553381019L;

	public MalformedQuestionException(String message, Question q) {
		System.err.println("MalformedQuestionException : question \"" + q + "\" doesn't respect the following constraint : " + message);
	}


}
