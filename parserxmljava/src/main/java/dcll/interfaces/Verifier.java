package dcll.interfaces;

import dcll.exception.MalformedQuestionException;

public interface Verifier {
	/**
	 * Verify that a question is well formed
	 */
	public void verify() throws MalformedQuestionException; 

}